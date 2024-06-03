package com.example.challengepokeapi.data.repository

import com.example.challengepokeapi.domain.model.PokemonModel
import io.reactivex.Single

interface HomeAllPokemonRepository {
    fun getAllPokemon(itemsShow:Int): Single<MutableList<PokemonModel>>

}