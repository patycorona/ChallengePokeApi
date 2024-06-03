package com.example.challengepokeapi.domain.usecase

import com.example.challengepokeapi.data.repository.DetailPokemonRepository
import com.example.challengepokeapi.domain.model.DetailPokemonModel
import io.reactivex.Single
import javax.inject.Inject

class DetailPokemonUseCase @Inject constructor(
    private var detailPokemonRepository: DetailPokemonRepository
) {

    fun getDetailPokemon(name:String): Single<DetailPokemonModel> =
        detailPokemonRepository.getDetailPokemon(name)
}