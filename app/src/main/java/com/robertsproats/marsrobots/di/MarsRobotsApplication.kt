package com.robertsproats.marsrobots.di

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MarsRobotsApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    override fun applicationInjector():
            AndroidInjector<MarsRobotsApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    companion object {
        private var context: Context? = null

        fun getAppContext(): Context? {
            return context
        }
    }

}