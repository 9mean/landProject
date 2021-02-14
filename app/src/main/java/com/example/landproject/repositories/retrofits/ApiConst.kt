package com.example.landproject.repositories.retrofits

object MapApiConst {
    const val BASE_URL="https://naveropenapi.apigw.ntruss.com/"
    const val CLIENT_ID="xv759515gr"
    const val CLIENT_SECRET="hDzZlbyMAaebxbP3ki3Z98QlJ0R6o4SfBCZhjVRk"
}
object LandApiConst {
    const val BASE_URL="http://apis.data.go.kr/"
    const val SERVICE_KEY="%2BPN0lvXeYV29abkawELPzbjBI21IFqQPoU9870zk5vzrKI8LV5k5AhOpxaIjLLNV0XlWdtMAsUTW8vKmri9H6w%3D%3D"
}
enum class RESPONSE_STATE{
    OK,FAIL
}