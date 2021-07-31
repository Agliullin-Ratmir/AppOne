package common.wallet.appone.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class RecordDto(
    //@JsonProperty("id") var id: String,
    @JsonProperty("uuid") var uuid: UUID,
    @JsonProperty("user") var userId: String,
    @JsonProperty("title") var title: String,
    @JsonProperty("sum") var sum: Double,
    @JsonProperty("details") var details: String,
    @JsonProperty("wallet") var walletId: String
)