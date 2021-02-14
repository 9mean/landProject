package com.example.landproject.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.landproject.R
import com.example.landproject.databinding.ActivityLandBinding
import com.example.landproject.repositories.retrofits.LandRetrofitManager
import com.example.landproject.repositories.retrofits.RESPONSE_STATE

class LandActivity : AppCompatActivity() {
    lateinit var binding : ActivityLandBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLandBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //인증요청하기
        binding.sendAuthLandBtn.setOnClickListener {
            LandRetrofitManager.instance.searchLand(binding.editTextLandPnu.text.toString(),
                completion = { responseState, responseBody ->
                    when (responseState) {
                        RESPONSE_STATE.OK -> {
                            Log.d("TAG", "api호출 성공 : $responseBody")
                        }
                        RESPONSE_STATE.FAIL -> {
                            Toast.makeText(this, "API호출 에러입니다.", Toast.LENGTH_SHORT).show()
                            Log.d("TAG", "api호출 실패 : $responseBody")
                        }
                    }
                })
        }
    }
}