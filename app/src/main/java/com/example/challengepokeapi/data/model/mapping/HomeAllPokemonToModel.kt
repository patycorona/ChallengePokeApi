package com.example.challengepokeapi.data.model.mapping

import com.example.challengepokeapi.data.model.response.AllPokemonResponse
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.URL_IMAGE
import com.example.challengepokeapi.domain.model.PokemonModel
import com.example.challengepokeapi.ui.component.removeUrl

internal fun AllPokemonResponse.toModel() : MutableList<PokemonModel>{
    var list: MutableList<PokemonModel> = mutableListOf()

    pokemon.map{ PK ->
        list.add(
            PokemonModel(
                name = PK.name,
                url = PK.url,
                idPokemon = PK.url.removeUrl().toString(),
                urlImage = URL_IMAGE
            )
        )
    }
    return list
}

