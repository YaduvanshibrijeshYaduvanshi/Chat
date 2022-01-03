package com.example.chat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UserAddapter(val context: Context, val userList: ArrayList<User>):
    RecyclerView.Adapter<UserAddapter.UserviewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserviewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return UserviewHolder(view)

    }

    override fun onBindViewHolder(holder: UserviewHolder, position: Int) {
        val curentUser = userList[position]

        holder.textName.text = curentUser.name

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ChatActivity::class.java)

            //intent.putExtra("name", currentUser.name)
            intent.putExtra("name",curentUser.name)
            intent.putExtra("uid",curentUser.uid)

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return userList.size

    }


    class UserviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textName = itemView.findViewById<TextView>(R.id.users_text)

    }
}