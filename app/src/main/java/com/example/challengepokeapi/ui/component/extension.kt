package com.example.challengepokeapi.ui.component

import android.view.View
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.EMPTY
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.SLASH
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.URL_ID_POKEMON

fun String.removeUrl() =
    this.replace(URL_ID_POKEMON, EMPTY)
        .replace(SLASH, EMPTY).toInt()

fun View.setLayoutHeight(height: Float) {
    val layoutParams = this.layoutParams
    layoutParams.height = height.toInt()
    this.layoutParams = layoutParams
}