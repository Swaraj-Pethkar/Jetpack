package com.example.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpack.databinding.ActivityMainBinding
import com.example.jetpack.room.MainPage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.api.setOnClickListener{
            val intent = Intent(this,Api::class.java)
            startActivity(intent)
        }

        viewBinding.dataBinding.setOnClickListener{
            val intent = Intent(this,DataBinding::class.java)
            startActivity(intent)
        }

        viewBinding.sharedPref.setOnClickListener{
            val intent = Intent(this,SharedPreference::class.java)
            startActivity(intent)
        }

        viewBinding.room.setOnClickListener{
            val intent = Intent(this,MainPage::class.java)
            startActivity(intent)
        }

    }
}