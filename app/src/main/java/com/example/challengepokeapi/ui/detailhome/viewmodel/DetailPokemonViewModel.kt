package com.example.challengepokeapi.ui.detailhome.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengepokeapi.domain.model.DetailPokemonResult
import com.example.challengepokeapi.domain.usecase.DetailPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private var detailPokemonUseCase: DetailPokemonUseCase
): ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    val detPokemonResult : MutableLiveData<DetailPokemonResult> by lazy {
        MutableLiveData<DetailPokemonResult>()
    }

    fun getDetailPokemon(name:String){
        compositeDisposable += detailPokemonUseCase.getDetailPokemon(name)
            .subscribeOn(Schedulers.io())
            .subscribe({ detail ->
                detPokemonResult.postValue(
                    DetailPokemonResult(
                        success = true,
                        idPokemon = detail.idPokemon,
                        name = detail.name,
                        urlImage= detail.urlImage,
                        height = detail.height,
                        weight = detail.weight,
                        itemAbility = detail.itemAbility
                    )
                )
            }, {
                detPokemonResult.postValue(
                    DetailPokemonResult(
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