package common.wallet.appone

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.awaitResponse
import com.github.kittinunf.fuel.httpGet
import common.wallet.appone.adapter.RECORD_INFO
import common.wallet.appone.adapter.RecordDtoAdapter
import common.wallet.appone.adapter.WALLET_INFO
import common.wallet.appone.adapter.WalletSubscriptionAdapter
import common.wallet.appone.config.Common
import common.wallet.appone.dto.RecordDto
import common.wallet.appone.dto.UserDto
import common.wallet.appone.dto.WalletSubscriptionDto
import common.wallet.appone.service.RetrofitService
import common.wallet.appone.view.RecordView
import kotlinx.android.synthetic.main.activity_wallet_records.*
import kotlinx.android.synthetic.main.activity_wallets.*
import kotlinx.android.synthetic.main.activity_wallets.recyclerSubsList
import org.jetbrains.anko.doAsync
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val CREATE_NEW_RECORD = "CREATE_NEW_RECORD"
const val EDITED_RECORD = "EDITED_RECORD"

inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}

class WalletRecords : AppCompatActivity() {
    lateinit var mService: RetrofitService
    lateinit var adapter: RecordDtoAdapter
    lateinit var layoutManager: LinearLayoutManager
    @SuppressLint("WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_records)
        var newRecordBtn : Button = findViewById(R.id.btnNewRecord)
//        val headers = HttpHeaders()
//        val mapper = ObjectMapper()
//        headers.set("Content-Type", "application/json")
        mService = Common.retrofitService
        val sharedPreference = getSharedPreferences(USER_UUID_KEY, Context.MODE_PRIVATE)
        val userUuid = sharedPreference.getString(USER_UUID_KEY, "")
        layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true;
        recyclerRecordsList.layoutManager = layoutManager
        var walletUuid = intent.getStringExtra(WALLET_INFO)
        newRecordBtn.setOnClickListener {
//            val rest = RestTemplate()
//            var name: String? = null
    //        doAsync {
//                val resp : ResponseEntity<UserDto> =
//                    rest.getForObject(" http://45.33.71.199:31000/user/find?firstName=Killian", ResultDTO<T>().javaClass)
                // convert resp to response entity
                //Toast.makeText(applicationContext, resp.toString(), Toast.LENGTH_LONG).show()
//                val result: ResponseEntity<UserDto>? = rest.exchange(
//                    "http://45.33.71.199:31000/user/find?firstName=Killian",
//                    HttpMethod.GET,
//                    HttpEntity("parameters", headers),
//                    typeRef<UserDto>())
//                name = mapper.readValue<UserDto>(result?.body)
//                name = Fuel.get("http://45.33.71.199:31000/user/find?firstName=Killian")
//                    .()
         //   }
//            "http://45.33.71.199:31000/user/find?firstName=Killian".httpGet()
//                .appendHeader("Content-Type", "text/plain")
//                .response() {
//                    request, response, result ->
//                    //name = mapper.readTree(response.body().toByteArray()).asText()
//                    name = String(response.body().toByteArray())
//            }
//            val (request, response, result) = "http://45.33.71.199:31000/user/find?firstName=Killian"
//                .httpGet().appendHeader("Content-Type", "text/plain").response()
            //name = String(response.body().toByteArray())
            val createRecordIntent = Intent(this, CreateRecord::class.java).apply {
                putExtra(CREATE_NEW_RECORD, walletUuid)
            }
            createRecordIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            startActivity(createRecordIntent)
        }

//        var removeWalletBtn : Button = findViewById(R.id.btnRemoveWallet)
//        removeWalletBtn.setOnClickListener {
//            // remove this wallet
//            val walletsRecordIntent = Intent(this, Wallets::class.java)
//            startActivity(walletsRecordIntent)
//        }
//        var walletId = intent.getStringExtra(GET_RECORDS)
//        var walletTitle : TextView = findViewById(R.id.walletTitle)
//        walletTitle.text = walletId
//        var param : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
//            RelativeLayout.LayoutParams.MATCH_PARENT,
//            RelativeLayout.LayoutParams.WRAP_CONTENT)
//
//        var layout = findViewById<LinearLayout>(R.id.scrollRecordsLinear)
//        for(i in 1..20) {
//            var record = RecordView(this, null)
//            record.setTitle("Title " + i)
//            record.setOnClickListener {
//                val editRecordIntent = Intent(this, EditRecord::class.java).apply {
//                    putExtra(EDITED_RECORD, i.toString())
//                }
//                startActivity(editRecordIntent)
//            }
//            param.addRule(RelativeLayout.BELOW, i)
//            registerForContextMenu(record)
//
//            record.setOnLongClickListener {
//                openContextMenu(record)
//                true
//            }
//            layout.addView(record)
//        }
        getAllRecords(walletUuid!!, userUuid!!)
    }

    private fun getAllRecords(walletUuid: String, userUuid: String) {
        mService.getRecordsByWalletUuid(walletUuid, userUuid).enqueue(object :
            Callback<MutableList<RecordDto>> {
            override fun onFailure(call: Call<MutableList<RecordDto>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<MutableList<RecordDto>>,
                response: Response<MutableList<RecordDto>>
            ) {
                adapter = RecordDtoAdapter(baseContext, response.body() as MutableList<RecordDto>)
                adapter.notifyDataSetChanged()
                recyclerRecordsList.adapter = adapter
            }
        })
    }
}

//    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//        menu?.add(0, v?.id!!, 0, "Remove record")
//    }
//
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//
//        when {
//            item?.title.contains("Remove record") -> {
//                // remove record
//                // update this activity
//                Toast.makeText(applicationContext, item?.title, Toast.LENGTH_LONG).show()
//                return true
//            }
//            else -> return super.onContextItemSelected(item)
//        }
//    }
//}
