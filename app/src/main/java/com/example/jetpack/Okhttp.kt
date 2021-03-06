package com.example.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.databinding.ActivityOkhttpBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class Okhttp : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOkhttpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val client = OkHttpClient()
        val url = "https://api.androidhive.info/contacts/"
        val request: Request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response) {
                val text: String = response.body()!!.string()
                this@Okhttp.runOnUiThread(Runnable {
                    val data:ArrayList<Contact> = ArrayList<Contact>()
                    val res = JSONObject(text)
                    val jsonArray = res.getJSONArray("contacts")
                    for (i in 0 until jsonArray.length()) {
                        val jsonData = jsonArray.getJSONObject(i)
                        val jsonPhoneData = jsonData.getJSONObject("phone")
                        val contact = Contact(id = jsonData.getString("id"), name = jsonData.getString("name"), email = jsonData.getString("email"), address = jsonData.getString("address"), gender = jsonData.getString("gender"), mobile = jsonPhoneData.getString("mobile"), home = jsonPhoneData.getString("home"), office = jsonPhoneData.getString("office"))
                        data.add(contact);
                        val adapter = CustomAdapter(data)
                        binding.recyclerView.adapter = adapter
                    }

                })

            }

            override fun onFailure(call: Call, e: IOException) {


                e.printStackTrace()
            }


        })
    }
}