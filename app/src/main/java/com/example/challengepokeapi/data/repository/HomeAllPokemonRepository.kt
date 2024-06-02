package com.example.challengepokeapi.data.repository

import com.example.challengepokeapi.domain.model.PokeModel
import io.reactivex.Single

interface AllPokeRepository {

    fun getAllPokemon(): Single<MutableList<PokeModel>>
}