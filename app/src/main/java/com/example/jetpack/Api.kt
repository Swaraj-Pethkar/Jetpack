package com.example.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpack.databinding.ActivityApiBinding
import com.example.jetpack.databinding.ActivityMainBinding

class Api : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.volley.setOnClickListener{
            val intent = Intent(this, Volley::class.java)
            startActivity(intent)
        }

        viewBinding.retrofit.setOnClickListener{
            val intent = Intent(this, Retrofit::class.java)
            startActivity(intent)
        }

        viewBinding.okHttp.setOnClickListener{
            val intent = Intent(this, Okhttp::class.java)
            startActivity(intent)
        }


    }
}