package common.wallet.appone.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import common.wallet.appone.dto.WalletSubscriptionDto
import common.wallet.appone.typeRef
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

const val url = "http://45.33.71.199:31000"
class RestTemplateService {
    companion object {
  val gson = Gson()
    val rest = RestTemplate()

        fun getSubscribersOfUserByUuid(uuid: String): List<WalletSubscriptionDto> {
            val itemType = object : TypeToken<List<WalletSubscriptionDto>>() {}.type
            val headers = HttpHeaders()
            headers.set("Content-Type", "application/json")
            val result: ResponseEntity<String>? = rest.exchange(
                url + "/user/subscriptions?uuid=" + uuid,
                HttpMethod.GET,
                HttpEntity("parameters", headers),
                typeRef<String>()
            )
            return gson.fromJson<List<WalletSubscriptionDto>>(result!!.body!!, itemType)
        }
   }
}