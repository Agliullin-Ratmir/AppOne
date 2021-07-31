package common.wallet.appone.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserCreateDto(
    @JsonProperty("firstName") var firstName: String,
    @JsonProperty("lastName") var lastName: String
)