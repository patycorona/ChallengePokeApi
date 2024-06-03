package com.example.challengepokeapi.data.model.mapping

import com.example.challengepokeapi.data.model.response.DetailPokemonResponse
import com.example.challengepokeapi.domain.model.AbilityModel
import com.example.challengepokeapi.domain.model.ConstantGeneral
import com.example.challengepokeapi.domain.model.DetailPokemonModel
import com.example.challengepokeapi.domain.model.ItemAbilityModel

internal fun DetailPokemonResponse.toModel(): DetailPokemonModel{

    var list: MutableList<ItemAbilityModel> = mutableListOf()

     itemAbility.map{ PK ->
         list.add(
             ItemAbilityModel(
                 ability = AbilityModel(name = PK.ability.name)
             )
         )
     }

     return DetailPokemonModel(
        idPokemon = idPokemon,
        name = name,
        urlImage = ConstantGeneral.URL_IMAGE,
        height = height,
        weight = weight,
        itemAbility = list
    )
}