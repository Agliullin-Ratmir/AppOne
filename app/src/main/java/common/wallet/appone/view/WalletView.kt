package common.wallet.appone.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import common.wallet.appone.R
import common.wallet.appone.Wallets
import org.jetbrains.anko.AnkoAsyncContext

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class WalletView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
    walletId: String,
    uuid: String
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    val uuid: String = uuid
    val walletId: String = walletId

    init {
        LayoutInflater.from(context).inflate(R.layout.wallet_layout, this, true)
        orientation = VERTICAL

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.wallet_attributes, 0, 0)
            val title = resources.getText(typedArray
                .getResourceId(R.styleable.wallet_attributes_wallet_component_title, R.string.app_name))
            typedArray.recycle()
        }
    }

    fun setTitle(title: String) {
        var walletTitle: TextView = findViewById<TextView>(R.id.walletTitleField)
        walletTitle.text = title
    }

    fun setDesc(desc: String) {
        var walletDesc: TextView = findViewById<TextView>(R.id.walletDescField)
        walletDesc.text = desc
    }
}