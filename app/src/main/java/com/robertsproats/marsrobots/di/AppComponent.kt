package com.robertsproats.marsrobots.di

import com.robertsproats.marsrobots.MainActivity
import com.robertsproats.marsrobots.presentation.di.PresentationModule
import com.robertsproats.marsrobots.repository.di.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    PresentationModule::class,
    DomainModule::class,
    RepositoryModule::class
])
@Singleton
interface AppComponent {

    fun inject(application: MarsRobotsApplication)

    fun inject(mainActivity: MainActivity)

}