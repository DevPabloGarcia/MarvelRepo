package com.pablogarcia.marvel.usecase

import com.pablogarcia.marvel.data.repository.CloudRepository
import javax.inject.Inject

abstract class BaseUseCase {

    @Inject
    lateinit var repository: CloudRepository
}
