package com.robertsproats.marsrobots.di

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MarsRobotsApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        var appComponent = DaggerAppComponent.create()
        appComponent.inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    companion object {
        private var context : Context? = null

        fun getAppContext(): Context? {
            return context
        }
    }
}