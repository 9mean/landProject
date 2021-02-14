package com.example.landproject.repositories.retrofits

import com.example.landproject.repositories.retrofits.MapApiConst.CLIENT_ID
import com.example.landproject.repositories.retrofits.MapApiConst.CLIENT_SECRET
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MapRetrofitService {
    //헤더에 앱키싸서 보내기
    @Headers("X-NCP-APIGW-API-KEY-ID: ${CLIENT_ID}","X-NCP-APIGW-API-KEY: ${CLIENT_SECRET}")
    //좌표로 주소 반환
    @GET("map-reversegeocode/v2/gc")
    fun searchAddress(@Query("coords") searchTerm:String, @Query("output") type:String, @Query("orders") adr:String): Call<JsonElement>
    //주소로 좌표,행정구역 반환
    @Headers("X-NCP-APIGW-API-KEY-ID: ${CLIENT_ID}","X-NCP-APIGW-API-KEY: ${CLIENT_SECRET}","Accept: application/json")
    @GET("map-geocode/v2/geocode")
    fun searchLatlng(@Query("query") searchTerm:String): Call<JsonElement>
}