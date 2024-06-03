package com.example.challengepokeapi.domain.model


class AllPokemonModelResult(
    val count: Int = 0,
    val next: String = "",
    val previous: String = "",
    val pokemon: MutableList<PokemonModel> = mutableListOf()
)