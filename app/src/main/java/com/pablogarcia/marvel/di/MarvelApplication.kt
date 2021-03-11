package com.pablogarcia.marvel.di

import android.app.Application
import com.pablogarcia.marvel.di.components.ApplicationComponent
import com.pablogarcia.marvel.di.components.DaggerApplicationComponent
import com.pablogarcia.marvel.di.modules.ApplicationModule

class MarvelApplication: Application() {

    val component: ApplicationComponent by lazy {

        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {

        super.onCreate()
        component.inject(this)
    }
}
