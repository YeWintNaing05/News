package com.ywn.remote

interface Mapper<R, E> {

    fun map(item: R): E
}