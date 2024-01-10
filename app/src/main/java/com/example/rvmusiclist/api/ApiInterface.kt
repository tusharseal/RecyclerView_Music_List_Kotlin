package com.example.rvmusiclist.api

import com.example.rvmusiclist.model.MusicModel
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @POST("/api/getChooseMeditation")
    fun getData(
        @Query("customerId") customerId: String,
        @Query("key") key: String
    ): Call<MusicModel>


}
