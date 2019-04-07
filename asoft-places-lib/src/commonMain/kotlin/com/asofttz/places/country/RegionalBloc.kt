package com.asofttz.places.country

import kotlinx.serialization.Serializable

@Suppress("ArrayInDataClass")
@Serializable
data class RegionalBloc(
        var acronym: String? = null,
        var name: String? = null,
        var otherAcronyms: Array<String> = arrayOf(),
        var otherNames: Array<String> = arrayOf()
)