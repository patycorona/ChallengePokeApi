package com.example.challengepokeapi.domain.model


class PokemonResult(
    var success:Boolean = false,
    val count: Int = 0,
    val next: String = "",
    val previous: String = "",
    var pokemon: MutableList<PokemonModel> = mutableListOf()
) {
}