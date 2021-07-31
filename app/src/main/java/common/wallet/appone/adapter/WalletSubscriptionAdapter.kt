package common.wallet.appone.adapter

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import common.wallet.appone.*
import common.wallet.appone.dto.WalletSubscriptionDto
import common.wallet.appone.enum.WalletSubscriberType
import common.wallet.appone.view.WalletView
import kotlinx.android.synthetic.main.wallet_layout.view.*

const val WALLET_INFO = "WALLET_INFO"
const val USER_STATUS = "USER_STATUS"

class WalletSubscriptionAdapter(private val context: Context, private val subsList: MutableList<WalletSubscriptionDto>):
    RecyclerView.Adapter<WalletSubscriptionAdapter.ViewHolder>(), View.OnClickListener, View.OnCreateContextMenuListener{

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.walletTitleField
        val desc = itemView.walletDescField
        fun bind(listItem: WalletSubscriptionDto) {
            itemView.setOnClickListener {
                val walletInfoIntent = Intent(itemView.context, WalletRecords::class.java).apply {
                    putExtra(WALLET_INFO, listItem.uuid)
                }
                walletInfoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
                startActivity(itemView.context, walletInfoIntent, null)
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
        holder.desc.text = subsList[position].description
    }

    override fun onClick(v: View?) {
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.add(0, v?.id!!, 0, "Add user")
        menu?.add(0, v?.id!!, 1, "Add admin")
        menu?.add(0, v?.id!!, 2, "Unsubscribe")
        menu?.getItem(0)?.setOnMenuItemClickListener {
            val subscribeIntent = Intent(v?.context, InviteCode::class.java).apply {
                putExtra(USER_STATUS, WalletSubscriberType.USER)
            }
            startActivity(v!!.context, subscribeIntent, null)
            Toast.makeText(v!!.context, "Get invite code for user", Toast.LENGTH_LONG).show()
            return@setOnMenuItemClickListener true
        }

        menu?.getItem(1)?.setOnMenuItemClickListener {
            val subscribeIntent = Intent(v?.context, InviteCode::class.java).apply {
                putExtra(USER_STATUS, WalletSubscriberType.ADMIN)
            }
            startActivity(v!!.context, subscribeIntent, null)
            Toast.makeText(v!!.context, "Get invite code for admin", Toast.LENGTH_LONG).show()
            return@setOnMenuItemClickListener true
        }

        menu?.getItem(2)?.setOnMenuItemClickListener {
            Toast.makeText(v!!.context, "Unsubscribe", Toast.LENGTH_LONG).show()
            return@setOnMenuItemClickListener true
        }
    }

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
}