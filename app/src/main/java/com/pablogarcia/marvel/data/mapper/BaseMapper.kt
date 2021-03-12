package com.pablogarcia.marvel.data.mapper


abstract class BaseMapper<T, R> {

    abstract fun map(data: T?) : R
}
