package com.watasolutions.week7_t7.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.watasolutions.week7_t7.adapter.UserAdapter.UserViewHolder
import com.watasolutions.week7_t7.model.Users
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.watasolutions.week7_t7.R
import android.widget.TextView

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private var mListUser: List<Users>? = null
    fun setData(list: List<Users>?) {
        this.mListUser = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users = mListUser!![position] ?: return
        Log.d("iii", mListUser.toString())
        holder.tvUsername!!.text = users.userName
        holder.tvPassword!!.text = users.passWord
//        holder.tvPassword!!.text = users.passWord
//        Log.d("iii", holder.tvPassword.toString())
    }

    override fun getItemCount(): Int {
        return if (mListUser != null) {
            mListUser!!.size
        } else 0
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsername: TextView? = null
        var tvPassword: TextView? = null

        init {
            tvUsername = itemView.findViewById(R.id.userName)
            tvPassword = itemView.findViewById(R.id.passWord)
        }
    }
}