package com.example.challengepokeapi.domain.usecase

import com.example.challengepokeapi.data.repository.AllPokeRepository
import com.example.challengepokeapi.domain.model.PokeModel
import io.reactivex.Single
import javax.inject.Inject

class AllPokeUseCase @Inject constructor(private var allPokeRepository: AllPokeRepository) {

    fun getAllPokemon(): Single<MutableList<PokeModel>> =
        allPokeRepository.getAllPokemon()

}
