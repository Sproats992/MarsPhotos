package com.robertsproats.marsrobots.di

import com.robertsproats.marsrobots.presentation.di.PresentationModule
import com.robertsproats.marsrobots.repository.di.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectBuilder::class,
    AppModule::class,
    PresentationModule::class,
    DomainModule::class,
    RepositoryModule::class
])
@Singleton
interface AppComponent : AndroidInjector<MarsRobotsApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<MarsRobotsApplication>

}