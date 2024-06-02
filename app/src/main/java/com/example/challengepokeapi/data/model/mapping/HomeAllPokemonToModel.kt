package com.example.challengepokeapi.data.model.mapping

import com.example.challengepokeapi.data.model.response.AllPokemonResponse
import com.example.challengepokeapi.domain.model.PokemonModel

internal fun AllPokemonResponse.toModel() : MutableList<PokemonModel>{
    val list: MutableList<PokemonModel> = mutableListOf()

    list_poke.map{ PK ->
        list.add(
            PokemonModel(
                name = PK.name,
                url = PK.url
            )
        )
    }
    return list
}