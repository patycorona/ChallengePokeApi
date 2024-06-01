package com.example.challengepokeapi.platform.di.module

import com.example.challengepokeapi.data.repository.FbAuthRepositoryImpl
import com.example.challengepokeapi.domain.usecase.FbAuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun FbAuthUseCaseProvider(fbAuthRepositoryImpl: FbAuthRepositoryImpl) =
        FbAuthUseCase(fbAuthRepositoryImpl)


}