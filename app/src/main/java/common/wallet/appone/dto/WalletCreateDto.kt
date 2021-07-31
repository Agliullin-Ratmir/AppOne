package common.wallet.appone.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class WalletCreateDto(
    @JsonProperty("ownerUuid") var ownerUuid: String,
    @JsonProperty("title") var title: String,
    @JsonProperty("description") var description: String
)