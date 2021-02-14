package com.example.landproject.views.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.landproject.GlobalApplication
import com.example.landproject.R
import com.example.landproject.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient


class LoginActivity : AppCompatActivity() {
    companion object{
        val TAG="TAG"
    }
    var myToken:String?=null
    private lateinit var binding: ActivityLoginBinding
    override fun onStart() {
        super.onStart()

    }
    private fun tokenLogin() {
        // 토큰 정보 보기
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.e(TAG, "토큰 정보 보기 실패", error)
            }
            else if (tokenInfo != null) {
                Log.i(TAG, "토큰 정보 보기 성공" +
                        "\n회원번호: ${tokenInfo.id}" +
                        "\n만료시간: ${tokenInfo.expiresIn} 초")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //토큰 존재시 메인액티비티로 이동하기
        tokenLogin()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.activity = this@LoginActivity
        GlobalApplication()
        //getHashKey()
//        binding.loginBtn.setOnClickListener {
//            login()
//        }
    }
     fun login(){
         Log.d(TAG, "로그인 시도")
         // 로그인 조합 예제
// 로그인 공통 callback 구성
         val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
             if (error != null) {
                 Log.e(TAG, "로그인 실패", error)
             }
             else if (token != null) {
                 Log.d(TAG, "로그인 성공 ${token.accessToken}")
                 startActivity(Intent(this, MainActivity::class.java))
                 finish()
             }
         }
// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
         if (LoginClient.instance.isKakaoTalkLoginAvailable(this)) {
             LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
         } else {
             LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
         }
    }
    //개발용 해시키얻기
//    private fun getHashKey() {
//        var packageInfo: PackageInfo? = null
//        try {
//            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
//        } catch (e: PackageManager.NameNotFoundException) {
//            e.printStackTrace()
//        }
//        if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
//        for (signature in packageInfo!!.signatures) {
//            try {
//                val md: MessageDigest = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
//            } catch (e: NoSuchAlgorithmException) {
//                Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
//            }
//        }
//    }//끝
}//onCreate