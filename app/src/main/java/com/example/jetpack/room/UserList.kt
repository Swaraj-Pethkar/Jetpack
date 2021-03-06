package com.example.jetpack.room

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_list_layout.*
import kotlinx.android.synthetic.main.user_list_layout.view.*

class UserList : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)


                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                val adapter = ListAdapter()
                recyclerView.adapter = adapter

                recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)

                mUserViewModel = ViewModelProvider(this@UserList).get(UserViewModel::class.java)
                mUserViewModel.readAllData.observe(this@UserList, Observer {user->
                    adapter.setData(user)

                })
    }

}