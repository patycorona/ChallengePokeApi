package com.example.challengepokeapi.data.model.response.homeresponse

import com.google.gson.annotations.SerializedName

data class AllPokemonResponse(
    @SerializedName("count") val count: Int = 0,
    @SerializedName("next") val next: String = "",
    @SerializedName("previous") val previous: String = "",
    @SerializedName("results") val pokemon: MutableList<Pokemon> = mutableListOf()
)
