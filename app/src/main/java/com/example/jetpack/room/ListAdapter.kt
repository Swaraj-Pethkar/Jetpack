package com.example.jetpack.room

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R


class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_layout,parent,false)
        return MyViewHolder(view);
    }



    override fun getItemCount(): Int {
        return userList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder?.bindItems(userList[position])

    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: User) {

            val name = itemView.findViewById<TextView>(R.id.name)
            val email = itemView.findViewById<TextView>(R.id.email)
            val phoneNumber = itemView.findViewById<TextView>(R.id.phoneNumber)
            val address = itemView.findViewById<TextView>(R.id.address)
//            val update = itemView.findViewById<Button>(R.id.update)
//            val delete = itemView.findViewById<Button>(R.id.delete)
            name.text = user.name
            email.text = user.email
            phoneNumber.text = user.phoneNumber
            address.text = user.address


            itemView.setOnClickListener{
                val intent = Intent(itemView.context, Update::class.java)
                intent.putExtra("id",user.id)
                intent.putExtra("name",user.name)
                intent.putExtra("email",user.email)
                intent.putExtra("phoneNumber",user.phoneNumber)
                intent.putExtra("address",user.address)
                itemView.context.startActivity(intent)
            }

//            delete.setOnClickListener{
//
//                val builder = AlertDialog.Builder(itemView.context)
//                builder.setPositiveButton("Yes"){_,_->
//                }
//                builder.setNegativeButton("No"){_,_->
//
//                }
//                builder.setTitle("Delete ${user.name}?")
//                builder.setMessage("Are You Sure You Want To Delete ${user.name}?")
//                builder.create().show()
//            }
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}