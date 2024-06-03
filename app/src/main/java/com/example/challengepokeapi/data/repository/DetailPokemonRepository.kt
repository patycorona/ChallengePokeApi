package com.example.challengepokeapi.data.repository


import com.example.challengepokeapi.domain.model.DetailPokemonModel
import io.reactivex.Single

interface DetailPokemonRepository {

    fun getDetailPokemon(name:String): Single<DetailPokemonModel>
}