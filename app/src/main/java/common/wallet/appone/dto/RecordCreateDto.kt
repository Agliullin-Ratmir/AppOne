package common.wallet.appone.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RecordCreateDto(
    @JsonProperty("userUuid") var userUuid: String,
    @JsonProperty("title") var title: String,
    @JsonProperty("sum") var sum: Double,
    @JsonProperty("details") var details: String,
    @JsonProperty("walletUuid") var walletUuid: String
)