package com.robertsproats.marsrobots.presentation.di

import com.robertsproats.marsrobots.presentation.features.detail.MarsRobotsDetailFragment
import com.robertsproats.marsrobots.presentation.features.home.MarsRobotsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PresentationFragmentModule {

    @ContributesAndroidInjector
    abstract fun provideMarsRobotsFragment(): MarsRobotsFragment

    @ContributesAndroidInjector
    abstract fun provideMarsRobotsHomeFragment(): MarsRobotsDetailFragment


}