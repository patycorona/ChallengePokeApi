package com.example.challengepokeapi.platform.di.component

import com.example.challengepokeapi.platform.di.module.RepositoryModule
import com.example.challengepokeapi.platform.di.module.RetrofitModule
import com.example.challengepokeapi.platform.di.module.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface MainComponent {
}