package com.example.challengepokeapi.domain.model

import android.os.Parcelable
import com.example.challengepokeapi.data.model.response.Ability
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemAbilityModel(
    val ability: AbilityModel = AbilityModel()
): Parcelable

