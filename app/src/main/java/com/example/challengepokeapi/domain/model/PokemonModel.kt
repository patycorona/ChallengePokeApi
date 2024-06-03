package com.example.challengepokeapi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel(
    val idPokemon: String = "",
    val name: String = "",
    val url: String = "",
    val urlImage:String = ""
): Parcelable

