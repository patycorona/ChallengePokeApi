package com.example.challengepokeapi.data.repository

import com.example.challengepokeapi.data.model.mapping.toModel
import com.example.challengepokeapi.data.network.CoreHomeApi
import com.example.challengepokeapi.domain.model.PokemonModel
import io.reactivex.Single
import javax.inject.Inject

class HomeAllPokemonRepositoryImpl @Inject constructor(
    private var apiService: CoreHomeApi
    ): HomeAllPokemonRepository {

    override fun getAllPokemon(itemsShow:Int): Single<MutableList<PokemonModel>> =
        apiService.getAllPokemon(itemsShow)

            .map { allPokemonResponse ->
                allPokemonResponse.toModel()
            }


}