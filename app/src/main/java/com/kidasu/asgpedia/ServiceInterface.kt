package com.kidasu.asgpedia

import retrofit2.Call
import retrofit2.http.*

interface ServiceInterface {
    @GET("Airsoft")
    fun getData(): Call<List<AirsoftData>>

    @POST("Airsoft")
    fun postKontak(@Body AirsoftData: AirsoftData): Call<AirsoftData>

    @FormUrlEncoded
    @HTTP(method="DELETE", path="Airsoft", hasBody = true)
    fun deleteAirsoft(@Field("id") id: Int): Call<AirsoftData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Airsoft", hasBody = true)
    fun updateKontak(
        @Field("id") id: Int,
        @Field("nama") nama: String,
        @Field("brand") brand: String,
        @Field("jenis") jenis: String,
        @Field("bahan") bahan: String,
        @Field("fps") fps: String,
        @Field("harga") harga: String, ): Call<AirsoftData>

}
