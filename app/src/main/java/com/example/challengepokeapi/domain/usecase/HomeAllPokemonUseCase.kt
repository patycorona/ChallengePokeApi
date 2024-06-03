package com.example.challengepokeapi.domain.usecase

import com.example.challengepokeapi.data.repository.HomeAllPokemonRepository
import com.example.challengepokeapi.domain.model.PokemonModel
import io.reactivex.Single
import javax.inject.Inject

class HomeAllPokemonUseCase @Inject constructor(
    private var homeAllPokemonRepository: HomeAllPokemonRepository
    ) {

    fun getAllPokemon(itemsShow:Int): Single<MutableList<PokemonModel>> =
        homeAllPokemonRepository.getAllPokemon(itemsShow)

}
