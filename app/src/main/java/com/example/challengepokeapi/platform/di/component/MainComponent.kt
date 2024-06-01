package com.example.challengepokeapi.platform.di.component

import com.example.challengepokeapi.platform.di.module.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class
    ]
)
interface MainComponent {
}