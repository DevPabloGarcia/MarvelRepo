package com.pablogarcia.marvel.usecase

import com.pablogarcia.marvel.data.repository.Repository
import javax.inject.Inject

abstract class BaseUseCase {

    @Inject
    lateinit var repository: Repository

}
