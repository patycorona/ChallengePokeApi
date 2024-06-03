package com.example.challengepokeapi.data.repository

import com.example.challengepokeapi.data.model.mapping.toModel
import com.example.challengepokeapi.data.network.CoreHomeApi
import com.example.challengepokeapi.domain.model.DetailPokemonModel
import io.reactivex.Single
import javax.inject.Inject

class DetailPokemonRepositoryImpl @Inject constructor(
    private var apiService: CoreHomeApi
    ):DetailPokemonRepository
{

    override fun getDetailPokemon(name:String): Single<DetailPokemonModel> =
        apiService.getDetailPokemon(name)

            .map { detailPokemonModel ->
                detailPokemonModel.toModel()
            }
}
