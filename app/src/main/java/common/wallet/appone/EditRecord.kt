package common.wallet.appone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditRecord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_record)
        var saveEditedRecord : Button = findViewById(R.id.saveEditedRecordBtn)
        saveEditedRecord.setOnClickListener {
            val walletRecordsIntent = Intent(this, WalletRecords::class.java).apply {
            }
            startActivity(walletRecordsIntent)
        }
        var editedRecordId = intent.getStringExtra(EDITED_RECORD)
        val titleView = findViewById<EditText>(R.id.recordTitleField).apply {
            text = SpannableStringBuilder(editedRecordId)
        }
    }
}