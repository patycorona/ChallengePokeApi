package com.example.challengepokeapi.domain.model

class DetailPokemonResult (
    var success:Boolean = false,
    val idPokemon: Int = 0,
    val name: String = "",
    val urlImage: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val itemAbility: MutableList<ItemAbilityModel> = mutableListOf()
)

