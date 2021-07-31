package common.wallet.appone

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.httpGet
import com.google.gson.reflect.TypeToken
import common.wallet.appone.adapter.WalletSubscriptionAdapter
import common.wallet.appone.config.Common
import common.wallet.appone.dto.WalletSubscriptionDto
import common.wallet.appone.service.RestTemplateService
import common.wallet.appone.service.RetrofitService
import common.wallet.appone.view.WalletView
import kotlinx.android.synthetic.main.activity_wallets.*
import org.jetbrains.anko.adapterViewFlipper
import org.jetbrains.anko.doAsync
import java.util.*
import kotlin.collections.ArrayList

const val USER_UUID_KEY = "USER_UUID_KEY"
const val GET_RECORDS = "getRecords"
const val USER_UUID = "06e2097f-d5b9-407e-aec8-cc2f335708cb"

class Wallets : AppCompatActivity() {
    lateinit var mService: RetrofitService
    lateinit var adapter: WalletSubscriptionAdapter
    lateinit var layoutManager: LinearLayoutManager
    @SuppressLint("WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallets)
        mService = Common.retrofitService
        val sharedPreference = getSharedPreferences(USER_UUID_KEY, Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString(USER_UUID_KEY, USER_UUID)
        editor.commit()
        var btnSubscribe: Button = findViewById(R.id.subscribeBtn)
        layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true;
        recyclerSubsList.layoutManager = layoutManager
        btnSubscribe.setOnClickListener {
            val subscribeIntent = Intent(this, InviteCode::class.java)
            startActivity(subscribeIntent)
        }
        var btnNewWallet: Button = findViewById(R.id.newWalletBtn)
        btnNewWallet.setOnClickListener {
            val createWalletIntent = Intent(this, CreateWallet::class.java).apply {
                putExtra(USER_UUID_KEY, USER_UUID)
            }
            startActivity(createWalletIntent)
        }
        var param: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )

        var layout = findViewById<LinearLayout>(R.id.scrollLinearLayout)
        getAllSubs()
    }

    private fun getAllSubs() {
        mService.getSubsList(USER_UUID).enqueue(object : Callback<MutableList<WalletSubscriptionDto>> {
            override fun onFailure(call: Call<MutableList<WalletSubscriptionDto>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<MutableList<WalletSubscriptionDto>>,
                response: Response<MutableList<WalletSubscriptionDto>>
            ) {
                adapter = WalletSubscriptionAdapter(baseContext, response.body() as MutableList<WalletSubscriptionDto>)
                adapter.notifyDataSetChanged()
                recyclerSubsList.adapter = adapter
            }
        })
    }
}

//    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//        menu?.add(0, v?.id!!, 0, "Get invite code")
//        menu?.add(0, v?.id!!, 1, "Unsubscribe")
//    }
//
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//
//        when {
//            item?.title.contains("Get invite code") -> {
//                val subscribeIntent = Intent(this, InviteCode::class.java)
//                startActivity(subscribeIntent)
//                Toast.makeText(applicationContext, item?.title, Toast.LENGTH_LONG).show()
//                return true
//            }
//            item?.title.contains("Unsubscribe") -> {
//                Toast.makeText(applicationContext, item?.title, Toast.LENGTH_LONG).show()
//                return true
//            }
//            else -> return super.onContextItemSelected(item)
//        }
//    }
//}