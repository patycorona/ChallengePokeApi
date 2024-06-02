package com.example.challengepokeapi.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengepokeapi.domain.model.PokeResult
import com.example.challengepokeapi.domain.usecase.AllPokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AllPokeViewModel @Inject constructor(
    private var allPokeUseCase : AllPokeUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val listPokeR: MutableLiveData<PokeResult> by lazy {
        MutableLiveData<PokeResult>()
    }

    fun getAllPoke(){
        compositeDisposable += allPokeUseCase.getAllPokemon()
            .subscribeOn(Schedulers.io())
            .subscribe({ list_poke ->
                listPokeR.postValue(
                    PokeResult(
                        sussess = true,
                        list_poke = list_poke
                    )
                )
            }, {
                listPokeR.postValue(
                    PokeResult(
                        sussess = false
                    )
                )
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}