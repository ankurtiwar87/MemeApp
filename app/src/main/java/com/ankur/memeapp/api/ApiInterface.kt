package com.ankur.memeapp.api

import com.ankur.memeapp.Model.MemeModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface{

    @GET("gimme")
    suspend fun getMeme():Response<MemeModel>
}