package com.ywn.data.exception

enum class Issue(private val shouldRetry: Boolean) {
    NETWORK(true), SERVER(false), API(false);

    fun shouldRetry(): Boolean {
        return shouldRetry
    }
}