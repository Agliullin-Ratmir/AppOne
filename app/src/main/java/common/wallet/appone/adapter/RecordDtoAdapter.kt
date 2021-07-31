package common.wallet.appone.adapter

import android.content.Context
import android.content.Intent
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import common.wallet.appone.InviteCode
import common.wallet.appone.R
import common.wallet.appone.WalletRecords
import common.wallet.appone.dto.RecordDto
import common.wallet.appone.view.RecordView
import kotlinx.android.synthetic.main.wallet_layout.view.*

const val RECORD_INFO = "RECORD_INFO"

class RecordDtoAdapter(private val context: Context, private val subsList: MutableList<RecordDto>):
    RecyclerView.Adapter<RecordDtoAdapter.ViewHolder>(), View.OnClickListener, View.OnCreateContextMenuListener{

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.walletTitleField
        val desc = itemView.walletDescField
        fun bind(listItem: RecordDto) {
            itemView.setOnClickListener {
                val recordInfoIntent = Intent(itemView.context, RecordView::class.java).apply {
                    putExtra(RECORD_INFO, listItem.uuid)
                }
                recordInfoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
                ContextCompat.startActivity(itemView.context, recordInfoIntent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.wallet_layout, parent, false)
        itemView.setOnClickListener(this)
        itemView.setOnCreateContextMenuListener(this)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = subsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = subsList[position]
        holder.bind(listItem)

        holder.title.text = subsList[position].title
        holder.desc.text = subsList[position].details
    }

    override fun onClick(v: View?) {
//        val walletInfoIntent = Intent(context, WalletRecords::class.java).apply {
//            putExtra(WALLET_INFO, "")
//        }
//        walletInfoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
//        startActivity(context, walletInfoIntent, null)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.add(0, v?.id!!, 0, "Get invite code")
        menu?.add(0, v?.id!!, 1, "Unsubscribe")
        menu?.getItem(0)?.setOnMenuItemClickListener {
            val subscribeIntent = Intent(v?.context, InviteCode::class.java)
            ContextCompat.startActivity(v!!.context, subscribeIntent, null)
            Toast.makeText(v!!.context, "Get invite code", Toast.LENGTH_LONG).show()
            return@setOnMenuItemClickListener true
        }

        menu?.getItem(1)?.setOnMenuItemClickListener {
            Toast.makeText(v!!.context, "Unsubscribe", Toast.LENGTH_LONG).show()
            return@setOnMenuItemClickListener true
        }
    }
}