package com.example.challengepokeapi.platform.di.module

import com.example.challengepokeapi.data.repository.DetailPokemonRepositoryImpl
import com.example.challengepokeapi.data.repository.HomeAllPokemonRepositoryImpl
import com.example.challengepokeapi.data.repository.FbAuthRepositoryImpl
import com.example.challengepokeapi.domain.usecase.DetailPokemonUseCase
import com.example.challengepokeapi.domain.usecase.HomeAllPokemonUseCase
import com.example.challengepokeapi.domain.usecase.FbAuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun fbAuthUseCaseProvider(fbAuthRepositoryImpl: FbAuthRepositoryImpl) =
        FbAuthUseCase(fbAuthRepositoryImpl)

    @Provides
    fun homeAllPokeUseCaseProvider(allPokeRepositoryImpl : HomeAllPokemonRepositoryImpl) =
        HomeAllPokemonUseCase(allPokeRepositoryImpl)

    @Provides
    fun detailPokemonUseCaseProvider(detailPokemonRepositoryImpl : DetailPokemonRepositoryImpl) =
        DetailPokemonUseCase(detailPokemonRepositoryImpl)

}