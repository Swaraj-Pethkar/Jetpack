package com.example.jetpack.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.databinding.ActivityMainPageBinding

class MainPage : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
        mUserViewModel = ViewModelProvider(this@MainPage).get(UserViewModel::class.java)
            btnRegister.setOnClickListener{
                val editName = name.text.toString()
                val editEmail = email.text.toString()
                val editPhoneNumber = phoneNumber.text.toString()
                val editAddress = address.text.toString()
                if(inputCheck(editName, editEmail, editPhoneNumber, editAddress)){
                    val user = User(0,editName,editEmail,editPhoneNumber,editAddress)
                    mUserViewModel.addUser(user)
                    toast("Data is saved")
                    name.text.clear()
                    email.text.clear()
                    phoneNumber.text.clear()
                    address.text.clear()
                }
                else{
                    toast("All Fields Mandatory")
                }
            }

            btnView.setOnClickListener{
                val intent = Intent(this@MainPage,UserList::class.java)
                startActivity(intent)
            }
        }


    }

    private fun inputCheck(name: String, email: String, phoneNumber: String, address: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(address))
    }

    private fun toast(msg:String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}