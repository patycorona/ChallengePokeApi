package com.example.challengepokeapi.iu.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challengepokeapi.data.model.response.AllPokemonResponse
import com.example.challengepokeapi.data.model.response.Pokemon
import com.example.challengepokeapi.domain.model.PokemonModel
import com.example.challengepokeapi.domain.model.PokemonResult
import com.example.challengepokeapi.domain.model.ResultModel
import com.example.challengepokeapi.domain.usecase.HomeAllPokemonUseCase
import com.example.challengepokeapi.ui.home.viewmodel.HomeAllPokemonViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeAllPokemonViewModelTest {
    private lateinit var homeAllPokemonViewModel : HomeAllPokemonViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeAllPokemonUseCase: HomeAllPokemonUseCase

    @Mock
    private lateinit var observer : androidx.lifecycle.Observer<PokemonResult>

    private var itemShow = 0

    private var allPokemonResponse = AllPokemonResponse()

    private var listPokemon =  mutableListOf(
        Pokemon(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/",
            idPokemon = "1",
            urlImage = "https://pokeapi.co/api/v2/pokemon/1.png"
        ),
        Pokemon(
            name = "charmander",
            url = "https://pokeapi.co/api/v2/pokemon/",
            idPokemon = "2",
            urlImage = "https://pokeapi.co/api/v2/pokemon/2.png"
        )
    )

    private var pokemonModel =  mutableListOf(
        PokemonModel(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/",
            idPokemon = "1",
            urlImage = "https://pokeapi.co/api/v2/pokemon/1.png"
        ),
        PokemonModel(
            name = "charmander",
            url = "https://pokeapi.co/api/v2/pokemon/",
            idPokemon = "2",
            urlImage = "https://pokeapi.co/api/v2/pokemon/2.png"
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeViewModel()
        initObserver()
    }

    private fun initObjectMock() {

        allPokemonResponse = AllPokemonResponse(
            pokemon = listPokemon
        )
    }

    private fun initObserver()
    {
        homeAllPokemonViewModel.listPokeR.observeForever(observer)
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(homeAllPokemonUseCase.getAllPokemon(itemShow)).thenReturn(
            Single.just(
                pokemonModel
            )
        )
    }

    private fun initializeViewModel() {
        homeAllPokemonViewModel = HomeAllPokemonViewModel(
            homeAllPokemonUseCase
        )
    }

    @Test
    fun `When call allPets then execute one time allPets `() {
        homeAllPokemonViewModel.getAllPokemon(itemShow)
        verify(homeAllPokemonUseCase, times(1)).getAllPokemon(itemShow)
    }

    @Test
    fun `When call allPets then return sucessfull response `() {
        homeAllPokemonViewModel.getAllPokemon(itemShow)
        Assert.assertEquals(homeAllPokemonViewModel.listPokeR.value?.pokemon?.size, 2)
    }
}