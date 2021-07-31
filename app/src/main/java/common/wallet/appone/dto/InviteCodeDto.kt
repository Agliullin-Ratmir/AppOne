package common.wallet.appone.dto

import com.fasterxml.jackson.annotation.JsonProperty
import common.wallet.appone.enum.WalletSubscriberType

data class InviteCodeDto(
    @JsonProperty("user_status") var userStatus: WalletSubscriberType,
    @JsonProperty("content") var content: String
)