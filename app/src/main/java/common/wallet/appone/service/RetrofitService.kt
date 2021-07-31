package common.wallet.appone.service

import common.wallet.appone.dto.*
import retrofit2.Call
import retrofit2.http.*


interface RetrofitService {
    @GET("user/subscriptions")
    fun getSubsList(@Query("uuid") uuid: String): Call<MutableList<WalletSubscriptionDto>>

    // get all records of the wallet
    @POST("wallet/recordsOfWallet")
    fun getRecordsByWalletUuid(@Query("walletUuid") walletUuid: String, @Body userUuid: String): Call<MutableList<RecordDto>>

    // create new wallet
    @Headers("Content-Type: application/json")
    @POST("wallet/new")
    open fun createNewWallet(@Body wallet: WalletCreateDto?): Call<WalletDto?>?

    // create new record
    @Headers("Content-Type: application/json")
    @POST("record/new")
    open fun createNewRecord(@Body record: RecordCreateDto?): Call<RecordDto?>?
}