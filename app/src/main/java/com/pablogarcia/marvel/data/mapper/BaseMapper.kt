package com.pablogarcia.marvel.data.mapper


abstract class BaseMapper<T, R> {

    abstract fun map(response: T?) : R
}
