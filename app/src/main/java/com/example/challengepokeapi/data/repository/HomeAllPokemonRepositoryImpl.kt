package com.example.challengepokeapi.data.repository

import com.example.challengepokeapi.data.model.mapping.toModel
import com.example.challengepokeapi.data.network.CoreHomeApi
import com.example.challengepokeapi.domain.model.PokeModel
import io.reactivex.Single
import javax.inject.Inject

class AllPokeRepositoryImpl @Inject constructor(var apiService: CoreHomeApi): AllPokeRepository {

    override fun getAllPokemon(): Single<MutableList<PokeModel>> =
        apiService.getAllPokemon()

            .map { allPokemonResponse ->
                allPokemonResponse.toModel()
            }
}