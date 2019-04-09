package com.asofttz.places.country

@Suppress("ArrayInDataClass")
data class RegionalBloc(
        var acronym: String? = null,
        var name: String? = null,
        var otherAcronyms: Array<String> = arrayOf(),
        var otherNames: Array<String> = arrayOf()
)