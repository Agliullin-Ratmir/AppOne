package common.wallet.appone.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import java.util.*

data class WalletDto(
    @JsonProperty("id") var id: String,
    @JsonProperty("uuid") var uuid: UUID,
    @JsonProperty("title") var title: String,
    @JsonProperty("description") var description: String,
    @JsonProperty("owner") var ownerId: String,
    @JsonProperty("admins") var adminsId: MutableList<String>,
    @JsonProperty("users") var usersId: MutableList<String>,
    @JsonProperty("records") var recordsId: MutableList<String>
)

class WalletDeserializer : ResponseDeserializable<WalletDto> {
    override fun deserialize(content: String) =
        Gson().fromJson(content, WalletDto::class.java)
}