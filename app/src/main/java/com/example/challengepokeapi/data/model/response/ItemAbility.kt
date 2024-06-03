package com.example.challengepokeapi.data.model.response

import com.google.gson.annotations.SerializedName

data class ItemAbility(

    @SerializedName("ability") val ability: Ability = Ability()

)
