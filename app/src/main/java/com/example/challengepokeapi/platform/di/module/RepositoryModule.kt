package com.example.challengepokeapi.platform.di.module

import com.example.challengepokeapi.data.database.FirebaseAction
import com.example.challengepokeapi.data.network.CoreHomeApi
import com.example.challengepokeapi.data.repository.DetailPokemonRepositoryImpl
import com.example.challengepokeapi.data.repository.HomeAllPokemonRepositoryImpl
import com.example.challengepokeapi.data.repository.FbAuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun fbAuthRepositoryProvider(fbActions: FirebaseAction):
            FbAuthRepositoryImpl = FbAuthRepositoryImpl(fbActions)
    @Provides
    fun homeAllPokeRepositoryProvider(apiService: CoreHomeApi):
            HomeAllPokemonRepositoryImpl = HomeAllPokemonRepositoryImpl(apiService)

    @Provides
    fun detailPokemonRepositoryProvider(apiService: CoreHomeApi):
            DetailPokemonRepositoryImpl = DetailPokemonRepositoryImpl(apiService)

}