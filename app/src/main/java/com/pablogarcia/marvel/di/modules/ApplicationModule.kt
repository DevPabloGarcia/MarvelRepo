package com.pablogarcia.marvel.di.modules

import android.content.Context
import com.pablogarcia.marvel.data.mapper.CharactersMapper
import com.pablogarcia.marvel.data.mapper.ComicsMapper
import com.pablogarcia.marvel.framework.room.mapper.CharacterToRoomMapper
import com.pablogarcia.marvel.framework.room.mapper.RoomToCharacterMapper
import com.pablogarcia.marvel.di.MarvelApplication
import com.pablogarcia.marvel.data.repository.cloud.CloudRepository
import com.pablogarcia.marvel.data.repository.local.LocalRepository
import com.pablogarcia.marvel.data.repository.Repository
import com.pablogarcia.marvel.data.repository.cloud.CloudRepositoryInterface
import com.pablogarcia.marvel.data.repository.local.LocalRepositoryInterface
import com.pablogarcia.marvel.framework.retrofit.RetrofitRepository
import com.pablogarcia.marvel.framework.room.RoomDatabase
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
    fun provideRepository(cloudRepository: CloudRepository,
                          localRepository: LocalRepository
    ): Repository = Repository(cloudRepository, localRepository)

    @Provides
    fun provideCloudRepository(charactersMapper: CharactersMapper,
                               comicsMapper: ComicsMapper
    ) : CloudRepositoryInterface {
        return RetrofitRepository(charactersMapper, comicsMapper)
    }

    @Provides
    fun provideLocalRepository(characterToRoomMapper: CharacterToRoomMapper,
                               roomToCharacterMapper: RoomToCharacterMapper
    ) : LocalRepositoryInterface = RoomDatabase(
        application,
        characterToRoomMapper,
        roomToCharacterMapper
    )
}
