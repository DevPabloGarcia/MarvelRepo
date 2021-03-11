package com.pablogarcia.marvel.di.components

import android.content.Context
import com.pablogarcia.marvel.di.MarvelApplication
import com.pablogarcia.marvel.di.modules.ApplicationModule
import com.pablogarcia.marvel.data.repository.CloudRepository
import com.pablogarcia.marvel.ui.characters.detail.CharacterDetailFragment
import com.pablogarcia.marvel.ui.characters.list.CharacterListFragment
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun getContext(): Context

    fun getRepository(): CloudRepository

    fun inject(app: MarvelApplication)

    fun inject(characterListActivity: CharacterListFragment)

    fun inject(characterDetailFragment: CharacterDetailFragment)

}
