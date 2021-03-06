package com.example.jetpack

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpack.databinding.ActivitySharedPreferenceBinding


class SharedPreference : AppCompatActivity() {
    private lateinit var binding : ActivitySharedPreferenceBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = DataBindingUtil.setContentView(this, R.layout.activity_shared_preference);
        binding.apply {
            val shared =
                getSharedPreferences("Swaraj", Context.MODE_PRIVATE)
            val username = shared.getString("username", editUsername.text.toString())
            val password = shared.getString("password", editPassword.text.toString())

            check!!.isChecked = !(TextUtils.isEmpty(username) && TextUtils.isEmpty(password))
            val sharedPref = User(username,password)
            binding.sharedPref = sharedPref

            btnSave.setOnClickListener{

                if(isEmpty(editUsername)){
                    editUsername.error = "Please Enter Username"
                    editUsername.requestFocus()
                    return@setOnClickListener
                }
                if(isEmpty(editPassword)){
                    editPassword.error = "Please Enter Password"
                    editPassword.requestFocus();
                    return@setOnClickListener
                }

                val sharedPreferences: SharedPreferences = getSharedPreferences("Swaraj", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("username", editUsername.text.toString())
                editor.putString("password", editPassword.text.toString())



                if(check.isChecked){
                    editor.apply();
                    finish();
                    toast("Data is Saved")
                } else if(!check.isChecked){
                    getSharedPreferences("Swaraj", 0).edit().clear().apply();
                    finish();
                    toast("Data is Clear")
                }
            }

        }
    }

    private fun isEmpty(editText: EditText): Boolean {
        return editText.text.toString().isEmpty()
    }

    private fun toast(msg:String){
        return Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
    }
}