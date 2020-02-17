package com.robertsproats.marsrobots.di

import com.robertsproats.marsrobots.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidInjectBuilder {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}