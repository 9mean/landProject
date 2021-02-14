package com.example.landproject.repositories.retrofits

import android.util.Log
import com.example.landproject.repositories.models.Land
import com.google.gson.JsonElement
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class LandRetrofitManager {
    companion object {
        val instance = LandRetrofitManager()
    }

    //레트로핏 인터페이스 가져오기
    private val retrofitService: LandRetrofitService? = RetrofitClient.getClient(LandApiConst.BASE_URL)?.create(LandRetrofitService::class.java)
    //좌표로주소검색 api 호출
    fun searchLand(mpnu:String, completion: (RESPONSE_STATE, Land?) -> Unit) {
        Log.d("TAG", "searchAddress")
        val pnu = mpnu.let {
            it
        }
        val call = retrofitService?.searchLand(pnu=pnu)
        Log.d("TAG", "manager from $pnu")
        call?.enqueue(object : retrofit2.Callback<Land> {
            //응답실패시
            override fun onFailure(call: Call<Land>, t: Throwable) {
                Log.d("TAG", "onFailure: 응답이 실패했습니다 .t:$t")
                completion(RESPONSE_STATE.FAIL, null)
            }
            //응답성공시
            override fun onResponse(call: Call<Land>, response: Response<Land>) {
                Log.d("TAG", "onResponse: 응답을 성공했습니다 response:${response.body()}")
                when (response.code()) {
                    200 -> {
                        response.body()?.let {
                            var parsedAddressArray: Land?
                                parsedAddressArray =Land(
                                        pnu=it.pnu,
                                        ldCodeNm =it.ldCodeNm,
                                        posesnSeCodeNm =it.posesnSeCodeNm
                                )
                            completion(RESPONSE_STATE.OK, parsedAddressArray)
                            }
                        }

                    }
            }
        })
    }
}