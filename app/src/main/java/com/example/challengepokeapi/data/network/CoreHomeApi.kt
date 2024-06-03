package com.example.challengepokeapi.data.network

import com.example.challengepokeapi.data.model.response.AllPokemonResponse
import com.example.challengepokeapi.data.model.response.DetailPokemonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CoreHomeApi {

    @GET("api/v2/pokemon/?limit=20")
    @Headers("content-Type: application/json ")
    fun getAllPokemon(@Query("offset") itemsShow:Int = 0): Single<AllPokemonResponse>

    @GET("api/v2/pokemon/{name}")
    @Headers("content-Type: application/json ")
    fun getDetailPokemon(@Path("name") name:String): Single<DetailPokemonResponse>

}