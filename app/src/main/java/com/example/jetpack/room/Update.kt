package com.example.jetpack.room

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.databinding.ActivityUpdateBinding
import kotlinx.android.synthetic.main.activity_update.*

class Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle : Bundle? = intent.extras

        val id:Int = bundle!!.getInt("id")
        val name = bundle!!.getString("name")
        val email = bundle!!.getString("email")
        val phoneNumber = bundle!!.getString("phoneNumber")
        val address = bundle!!.getString("address")

        val updateData = Users(name,email,phoneNumber,address)


        var mUserViewModel: UserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.updateData = updateData
        btnUpdate.setOnClickListener{
            if(inputCheck(updateName.text.toString(), updateEmail.text.toString(), updatePhoneNumber.text.toString(), updateAddress.text.toString())){
                val upUser = User(id,updateName.text.toString(),updateEmail.text.toString(),updatePhoneNumber.text.toString(),updateAddress.text.toString())

                mUserViewModel.updateUser(upUser)
                toast("Data Updated Successfully")
                finish()
            }
            else{
                toast("All Fields Mandatory")
            }
        }

        btnDelete.setOnClickListener{

            val delUser = User(id,name.toString(),email.toString(),phoneNumber.toString(),address.toString())

            mUserViewModel.deleteUser(delUser)
            toast("Data Deleted Successfully")
            finish()
        }
    }
    private fun inputCheck(name: String?, email: String?, phoneNumber: String?, address: String?): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(address))
    }

    private fun backToPreviousPage(msg: String){
        toast(msg)
        val intent = Intent(this,UserList::class.java)
        startActivity(intent)
    }

    private fun toast(msg:String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}