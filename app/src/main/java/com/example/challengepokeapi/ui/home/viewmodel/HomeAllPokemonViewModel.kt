package com.example.challengepokeapi.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengepokeapi.domain.model.PokemonResult
import com.example.challengepokeapi.domain.usecase.HomeAllPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeAllPokemonViewModel @Inject constructor(
    private var homeAllPokemonUseCase : HomeAllPokemonUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val listPokeR: MutableLiveData<PokemonResult> by lazy {
        MutableLiveData<PokemonResult>()
    }

    fun getAllPokemon(itemsShow:Int){
        compositeDisposable += homeAllPokemonUseCase.getAllPokemon(itemsShow)
            .subscribeOn(Schedulers.io())
            .subscribe({ pokemon ->
                listPokeR.postValue(
                    PokemonResult(
                        success = true,
                        pokemon = pokemon
                    )
                )
            }, {
                listPokeR.postValue(
                    PokemonResult(
                        success = false
                    )
                )
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}