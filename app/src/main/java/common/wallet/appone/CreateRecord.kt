package common.wallet.appone

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import common.wallet.appone.adapter.WALLET_INFO
import common.wallet.appone.config.Common
import common.wallet.appone.dto.*

import common.wallet.appone.service.RestTemplateService
import common.wallet.appone.service.RestTemplateService.Companion.getSubscribersOfUserByUuid
import common.wallet.appone.service.RetrofitService
import kotlinx.android.synthetic.main.activity_create_record.*
import kotlinx.coroutines.runBlocking

import org.jetbrains.anko.doAsync
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateRecord : AppCompatActivity() {
    lateinit var mService: RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_record)
        var saveRecord: Button = findViewById(R.id.saveRecordBtn)
        mService = Common.retrofitService
        saveRecord.setOnClickListener {
            val userUuid = "e7e9a9ff-efd4-4ec1-9d91-e88ad1a98d87"
            val walletUuid = intent.getStringExtra(CREATE_NEW_RECORD)!!
            val details = recordDescField.text.toString()
            val title = recordTitleField.text.toString()
            val sum = recordSumField.text.toString().toDouble()
            val newRecord = RecordCreateDto(userUuid, title, sum, details, walletUuid)
            createNewRecord(this, newRecord)
            val walletInfoIntent = Intent(this, WalletRecords::class.java).apply {
                putExtra(WALLET_INFO, walletUuid)
            }
            walletInfoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            startActivity(walletInfoIntent)
        }
//        doAsync {
//
//            val desc = getSubscribersOfUserByUuid("e7e9a9ff-efd4-4ec1-9d91-e88ad1a98d87")
//            val titleView = findViewById<EditText>(R.id.recordDescField).apply {
//                text = SpannableStringBuilder(desc[0].description)
//            }
//        }
    }

    private fun createNewRecord(context: Context, newRecord: RecordCreateDto) {
        val call: Call<RecordDto?>? = mService.createNewRecord(newRecord)
        call?.enqueue(object : Callback<RecordDto?> {
            override fun onResponse(call: Call<RecordDto?>?, response: Response<RecordDto?>) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "New record created", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RecordDto?>?, t: Throwable?) {}
        })
    }
}
