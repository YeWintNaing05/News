package com.ywn.data

abstract class Mapper<D, E> {
    abstract fun map(entity: E): D
}