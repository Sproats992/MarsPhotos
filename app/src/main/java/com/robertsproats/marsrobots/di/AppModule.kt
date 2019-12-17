package com.robertsproats.marsrobots.di

import com.robertsproats.marsrobots.repository.database.NasaDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    // instantiate databases with app context here.
    @Provides
    @Singleton
    fun providesNasaDatabase():
            NasaDatabase = NasaDatabase.getNasaDataBase(MarsRobotsApplication.getAppContext()!!)!!

}