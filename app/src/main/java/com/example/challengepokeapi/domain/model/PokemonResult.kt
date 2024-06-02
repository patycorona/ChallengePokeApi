package com.example.challengepokeapi.domain.model.homemodel

class PokemonResult(
    var success:Boolean = false,
    var pokemon: MutableList<PokemonModel> = mutableListOf()
) {
}