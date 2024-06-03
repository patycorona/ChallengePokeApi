package com.example.challengepokeapi.data.model.response

import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("name") val name: String = ""
)
