package common.wallet.appone.config

import common.wallet.appone.service.RetrofitService

object Common {
    private const val BASE_URL = "http://45.33.71.199:31000/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}