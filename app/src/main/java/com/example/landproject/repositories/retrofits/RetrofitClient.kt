package com.example.landproject.repositories.retrofits

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import java.util.concurrent.TimeUnit

//싱글턴
object RetrofitClient {
    //레트로핏클라이언트선언
    private var retrofitClient: Retrofit? = null
    //레트로핏클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {
        Log.d("TAG", "getClient: called")

        //okhttp인스턴스생성
        val client = OkHttpClient.Builder()
        //로그찍기위해
        //로깅 인터셉터 설정
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("TAG", "log called msg:$message")
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        //위에서 설정한 로깅 인터셉터를 okhttp클라이언트에 추가한다
        client.addInterceptor(loggingInterceptor)


        //기본 파라미터 인터셉터 추가
        val baseParameterInterceptor: Interceptor = (object :Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                //오리지널 리퀘스트
                val originalRequest = chain.request()
                //?client_id=adssd
                //쿼리파라미터추가하기
                val addUrl=originalRequest.url.newBuilder().addEncodedQueryParameter("ServiceKey",LandApiConst.SERVICE_KEY).build()
                val finalRequest=originalRequest.newBuilder().url(addUrl).method(originalRequest.method,originalRequest.body).build()
                return chain.proceed(finalRequest)
            }
            
        })
        //위에서 설정한 기본파라미터 인터셉터를 okhttp클라이언트에 추가한다
        client.addInterceptor(baseParameterInterceptor)
        //커넥션 타임아웃
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)

        if (retrofitClient == null) {
            //레트로핏 빌더(생성패턴)를통해 인스턴스생성
            retrofitClient = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(JaxbConverterFactory.create())
                    //.addConverterFactory(GsonConverterFactory.create())
                    //위에서 설정한 클라이언트로 레트로핏클라이언트설정
                    .client(client.build())
                    .build()
            Log.d("TAG", "getClient: 빌드성공")
        }
        return retrofitClient
    }
}