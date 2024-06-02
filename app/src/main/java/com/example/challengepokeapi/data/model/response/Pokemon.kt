package com.example.challengepokeapi.data.model.response.homeresponse

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val name: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("idPokemon") val idPokemon: String = "",
    @SerializedName("urlImage") val urlImage: String = ""
)
