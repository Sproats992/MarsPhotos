package com.robertsproats.marsrobots.presentation.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [PresentationFragmentModule::class, PresentationViewModelModule::class])
class PresentationModule {

    @Provides
    @Singleton
    fun providesDefaultCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.Default

}