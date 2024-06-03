package com.example.challengepokeapi.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challengepokeapi.data.model.response.AllPokemonResponse
import com.example.challengepokeapi.data.model.response.Pokemon
import com.example.challengepokeapi.data.repository.HomeAllPokemonRepository
import com.example.challengepokeapi.domain.model.PokemonModel
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

class HomeAllPokemonUseCaseTest {

    private lateinit var homeAllPokemonUseCase: HomeAllPokemonUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeAllPokemonRepository: HomeAllPokemonRepository

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
        initializeUseCase()
    }

    private fun initObjectMock() {

        allPokemonResponse = AllPokemonResponse(
            pokemon = listPokemon
        )
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(homeAllPokemonRepository.getAllPokemon(itemShow)).thenReturn(
            Single.just(
                pokemonModel
            )
        )
    }

    private fun initializeUseCase() {
        homeAllPokemonUseCase = HomeAllPokemonUseCase(
            homeAllPokemonRepository
        )
    }

    @Test
    fun `When call allPet then execute one time allPet `() {
        homeAllPokemonUseCase.getAllPokemon(itemShow)
        verify(homeAllPokemonRepository, times(1)).getAllPokemon(itemShow)
    }

    @Test
    fun `When call allPet then return sucessfull response `() {
        homeAllPokemonUseCase.getAllPokemon(itemShow)
        Assert.assertEquals(pokemonModel.size, 2)
    }



}