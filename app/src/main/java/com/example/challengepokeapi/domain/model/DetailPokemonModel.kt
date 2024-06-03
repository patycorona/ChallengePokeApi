package com.example.challengepokeapi.domain.model

data class DetailPokemonModel(
    val idPokemon: Int = 0,
    val name: String = "",
    val urlImage: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val itemAbility: MutableList<ItemAbilityModel> = mutableListOf()
)
