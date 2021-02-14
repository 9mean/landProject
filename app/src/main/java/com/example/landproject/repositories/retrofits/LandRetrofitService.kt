package com.example.landproject.repositories.retrofits

import com.example.landproject.repositories.models.Land
import com.google.gson.JsonElement
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.xml.bind.annotation.XmlElement

interface LandRetrofitService {
    @GET("1611000/nsdi/eios/LadfrlService/ladfrlList.xml")
    fun searchLand(@Query("pnu") pnu:String): Call<Land>
    //주소로 좌표,행정구역 반환
}