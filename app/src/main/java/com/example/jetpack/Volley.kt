package com.example.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.jetpack.databinding.ActivityVolleyBinding
import org.json.JSONException
import org.json.JSONObject


class Volley : AppCompatActivity() {



    private var requestQueue: RequestQueue? = null
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val viewBinding = ActivityVolleyBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        requestQueue = Volley.newRequestQueue(this)

        val url = "https://api.androidhive.info/contacts/"


        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response ->try {
            val data:ArrayList<Contact> = ArrayList<Contact>()
            val jsonArray = response.getJSONArray("contacts")
            for (i in 0 until jsonArray.length()) {
                val jsonData = jsonArray.getJSONObject(i)
                val jsonPhoneData = jsonData.getJSONObject("phone")
                val contact = Contact(id = jsonData.getString("id"), name = jsonData.getString("name"), email = jsonData.getString("email"), address = jsonData.getString("address"), gender = jsonData.getString("gender"), mobile = jsonPhoneData.getString("mobile"), home = jsonPhoneData.getString("home"), office = jsonPhoneData.getString("office"))
                data.add(contact);
                val adapter = CustomAdapter(data)
                viewBinding.recyclerView.adapter = adapter
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}