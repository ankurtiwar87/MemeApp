package com.ankur.memeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import androidx.lifecycle.lifecycleScope
import com.ankur.memeapp.api.ApiInterface
import com.ankur.memeapp.api.ApiUtilities
import com.ankur.memeapp.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonMeme.setOnClickListener {
            getMemeFromApi();
        }
    }

    private fun getMemeFromApi() {


        lifecycleScope.launch(Dispatchers.IO) {
            val apiResult = ApiUtilities().getInstance().create(ApiInterface::class.java).getMeme()

            withContext(Dispatchers.Main) {
                Log.d("AJA",apiResult.body()!!.url)
                binding.memeTitle.text=apiResult.body()!!.title
                binding.LikeCount.text=apiResult.body()!!.ups.toString()
                Glide.with(applicationContext).load(apiResult.body()!!.url).thumbnail(Glide.with(applicationContext).load(R.drawable.spinner)).into(binding.memeImg)
            }
        }
    }
}