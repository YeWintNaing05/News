package com.ywn.domain.model

import io.reactivex.annotations.Nullable

data class Source(
    @Nullable
    val id: String?,
    val name: String
)