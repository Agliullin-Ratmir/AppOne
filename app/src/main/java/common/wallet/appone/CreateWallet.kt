package common.wallet.appone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import common.wallet.appone.config.Common
import common.wallet.appone.dto.WalletCreateDto
import common.wallet.appone.dto.WalletDto
import common.wallet.appone.service.RetrofitService
import kotlinx.android.synthetic.main.activity_create_wallet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreateWallet : AppCompatActivity() {
    lateinit var mService: RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)
        var saveWallet : Button = findViewById(R.id.saveWalletBtn)
        mService = Common.retrofitService
        saveWallet.setOnClickListener {
            val title = walletTitleField.text
            val desc = walletDescField.text
            val userUuid = intent.getStringExtra(USER_UUID_KEY)
            var newWallet = WalletCreateDto(userUuid!!,
            title.toString(), desc.toString())
            createNewWallet(this, mService, newWallet)
            val walletsIntent = Intent(this, Wallets::class.java).apply {
            }
            startActivity(walletsIntent)
        }
    }

    private fun createNewWallet(context: Context, mService: RetrofitService, newWallet: WalletCreateDto) {
        val call: Call<WalletDto?>? = mService.createNewWallet(newWallet)
        call?.enqueue(object : Callback<WalletDto?> {
            override fun onResponse(call: Call<WalletDto?>?, response: Response<WalletDto?>) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "New wallet created", Toast.LENGTH_LONG).show()
                } else {
                }
            }

            override fun onFailure(call: Call<WalletDto?>?, t: Throwable?) {}
        })
    }
}