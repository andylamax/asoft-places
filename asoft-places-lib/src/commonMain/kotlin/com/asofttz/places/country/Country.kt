package com.asofttz.places.country

import com.asofttz.places.region.Region
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Suppress("ArrayInDataClass")
data class Country(
        var alpha2Code: String? = null,
        var alpha3Code: String? = null,
        var altSpellings: Array<String> = arrayOf(),
        var area: Double? = null,
        var borders: Array<String> = arrayOf(),
        var callingCodes: Array<String> = arrayOf(),
        var capital: String? = null,
        var cioc: String? = null,
        var currencies: Array<Currency> = arrayOf(),
        var demonym: String? = null,
        var flag: String? = null,
        var gini: Double? = null,
        var languages: Array<Language> = arrayOf(),
        var latlng: Array<Double> = arrayOf(),
        var name: String? = null,
        var nativeName: String? = null,
        var numericCode: String? = null,
        var population: Int? = null,
        var region: String = "",
        var regionalBlocs: Array<RegionalBloc> = arrayOf(),
        var subregion: String? = null,
        var timezones: Array<String> = arrayOf(),
        var topLevelDomain: Array<String> = arrayOf(),
        var translations: Translations = Translations(),
        @Optional var regions: Array<Region> = arrayOf()
)