package com.pablogarcia.marvel.di.modules

import android.content.Context
import com.pablogarcia.marvel.data.mapper.CharactersMapper
import com.pablogarcia.marvel.data.mapper.ComicsMapper
import com.pablogarcia.marvel.data.repository.DataRepository
import com.pablogarcia.marvel.di.MarvelApplication
import com.pablogarcia.marvel.data.repository.DataRepositoryImpl
import com.pablogarcia.marvel.data.repository.cloud.CloudRepository
import com.pablogarcia.marvel.data.repository.local.LocalRepository
import com.pablogarcia.marvel.framework.retrofit.RetrofitRepository
import com.pablogarcia.marvel.framework.room.RoomDatabase
import com.pablogarcia.marvel.framework.room.mapper.CharacterToRoomMapper
import com.pablogarcia.marvel.framework.room.mapper.RoomToCharacterMapper
import com.pablogarcia.marvel.usecase.*
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
    ): DataRepository = DataRepositoryImpl(cloudRepository, localRepository)

    @Provides
    fun provideCloudRepository() : CloudRepository = RetrofitRepository()

    @Provides
    fun provideLocalRepository() : LocalRepository = RoomDatabase(application)

    @Provides
    fun provideObtainCharactersUseCase(repository: DataRepository,
                                       charactersMapper: CharactersMapper,
                                       roomToCharacterMapper: RoomToCharacterMapper,
                                       charactersToRoomMapper: CharacterToRoomMapper
    ) : ObtainCharactersUseCase {
        return ObtainCharactersUseCaseImpl(
            repository,
            charactersMapper,
            roomToCharacterMapper,
            charactersToRoomMapper
        )
    }

    @Provides
    fun provideUpdateCharacterLikeUseCase(
        repository: DataRepository,
        mapper: CharacterToRoomMapper
    ) : UpdateCharacterLikeUseCase = UpdateCharacterLikeUseCaseImpl(repository, mapper)

    @Provides
    fun provideObtainComicsUseCase(repository: DataRepository,
                                   comicsMapper: ComicsMapper
    ): ObtainComicsUseCase = ObtainComicsUseCaseImpl(repository, comicsMapper)
}
