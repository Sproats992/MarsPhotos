package com.robertsproats.marsrobots.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robertsproats.marsrobots.presentation.util.ViewModelFactory
import com.robertsproats.marsrobots.presentation.util.ViewModelKey
import com.robertsproats.marsrobots.presentation.features.detail.MarsRobotsDetailViewModel
import com.robertsproats.marsrobots.presentation.features.home.MarsRobotsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MarsRobotsViewModel::class)
    internal abstract fun postMarsRobotsViewModel(viewModel: MarsRobotsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarsRobotsDetailViewModel::class)
    internal abstract fun postMarsRobotsDetailViewModel(viewModel: MarsRobotsDetailViewModel): ViewModel

}