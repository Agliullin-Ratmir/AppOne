package common.wallet.appone.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import common.wallet.appone.R

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class RecordView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.record_layout, this, true)
        orientation = VERTICAL

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.record_attributes, 0, 0)
            val title = resources.getText(typedArray
                .getResourceId(R.styleable.record_attributes_record_component_title, R.string.app_name))

                        typedArray.recycle()
        }
    }

    fun setTitle(title: String) {
        var recordTitle: TextView = findViewById<TextView>(R.id.walletTitleField)
        recordTitle.text = title
    }
}