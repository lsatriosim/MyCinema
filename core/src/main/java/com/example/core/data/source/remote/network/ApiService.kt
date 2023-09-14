package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.Response
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/now_playing")
    suspend fun getList(): Response
}