package com.example.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack.databinding.ActivityRetrofitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retrofit : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        val viewBinding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val rf = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val API = rf.create(RetrofitInterface::class.java)
        val call = API.posts
        call?.enqueue(object:Callback<List<PostModel?>?>{
            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable?) {
                Toast.makeText(applicationContext, "No", Toast.LENGTH_SHORT).show()
            }



            override fun onResponse(
                call: Call<List<PostModel?>?>?,
                response: Response<List<PostModel?>?>?
            ) {
                val postList : List<PostModel>? = response?.body() as List<PostModel>
                val post = ArrayList<String>()

                for (i in postList!!.indices){
                    val body = postList!![i]!!.body
                    val userid = postList!![i]!!.userId
                    post.add(userid.toString()+"\n"+body);
                    val adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_dropdown_item_1line,post)
                    viewBinding.listView.adapter = adapter
            }


            }
        })
    }
}
