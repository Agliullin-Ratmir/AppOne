package common.wallet.appone

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import common.wallet.appone.adapter.USER_STATUS
import common.wallet.appone.enum.WalletSubscriberType


class InviteCode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_code)
        val userStatus = intent.getSerializableExtra(USER_STATUS) as WalletSubscriberType
        var inviteCode: TextView = findViewById(R.id.inviteCodeField)
        var btnCopy: Button = findViewById(R.id.copyInviteCodeBtn)
        btnCopy.setOnClickListener {
            copyCodeToClipboard(inviteCode.text as String, clipboard, userStatus)
        }
    }

    fun copyCodeToClipboard(code: String, clipboard: ClipboardManager,
                            userStatus: WalletSubscriberType) {
        val clip = ClipData.newPlainText("inviteCode", code)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(applicationContext, userStatus.name, Toast.LENGTH_LONG).show()
    }
}