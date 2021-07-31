package common.wallet.appone.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import common.wallet.appone.enum.WalletSubscriberType
import kotlinx.serialization.Contextual
import java.util.*
import kotlinx.serialization.Serializable

@Serializable
data class WalletSubscriptionDto (
    //@SerializedName("id") var id: Object[],
    @SerializedName("uuid") var uuid: String,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("totalSum") var totalSum: Double,
    @SerializedName("subsType") var subsType: WalletSubscriberType
)