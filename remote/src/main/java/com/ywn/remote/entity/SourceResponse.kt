package com.ywn.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.ywn.remote.network.NullToEmptyString


@JsonClass(generateAdapter = true)
data class SourceResponse(
    @Json(name = "id")
    @NullToEmptyString
    val id: String?,
    @Json(name = "name")
    val name: String
)