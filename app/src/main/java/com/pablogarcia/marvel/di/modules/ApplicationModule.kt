package com.pablogarcia.marvel.di.modules

import android.content.Context
import com.pablogarcia.marvel.di.MarvelApplication
import com.pablogarcia.marvel.data.repository.CloudRepository
import com.pablogarcia.marvel.usecase.ObtainCharactersUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MarvelApplication) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    fun provideAppContext(): Context = application

    @Provides
    fun provideRepository(): CloudRepository = CloudRepository()

}
