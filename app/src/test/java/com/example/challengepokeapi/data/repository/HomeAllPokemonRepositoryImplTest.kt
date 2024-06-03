package com.example.challengepokeapi.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challengepokeapi.data.model.response.AllPokemonResponse
import com.example.challengepokeapi.data.model.response.Pokemon
import com.example.challengepokeapi.data.network.CoreHomeApi
import com.example.challengepokeapi.domain.model.PokemonModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import com.nhaarman.mockitokotlin2.verify
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

class HomeAllPokemonRepositoryImplTest {

    private lateinit var homeAllPokemonRepositoryImpl: HomeAllPokemonRepositoryImpl

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  apiService: CoreHomeApi


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
        initializeRepositoryImpl()
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
        whenever(apiService.getAllPokemon(itemShow)).thenReturn(
            Single.just(
                allPokemonResponse
            )
        )
    }

    private fun initializeRepositoryImpl() {
        homeAllPokemonRepositoryImpl = HomeAllPokemonRepositoryImpl(
            apiService
        )
    }

    @Test
    fun `When call userRegister then execute one time userAccess `() {
        homeAllPokemonRepositoryImpl.getAllPokemon(itemShow)
        verify(apiService, times(1)).getAllPokemon()
    }

    @Test
    fun `When call userRegister then return sucessfull response `() {
        homeAllPokemonRepositoryImpl.getAllPokemon(itemShow)
        Assert.assertEquals(pokemonModel.size, 2)
    }

}