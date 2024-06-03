package com.example.challengepokeapi.data.model.response


import com.google.gson.annotations.SerializedName

data class DetailPokemonResponse(
    @SerializedName("id") val idPokemon: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("urlImage") val urlImage: String = "",
    @SerializedName("height") val height: Int = 0,
    @SerializedName("weight") val weight: Int = 0,
    @SerializedName("abilities") val itemAbility: MutableList<ItemAbility> = mutableListOf()
)
