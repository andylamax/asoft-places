package tz.co.asoft.places.country

import tz.co.asoft.places.region.Region
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class Country(
        var alpha2Code: String? = null,
        var alpha3Code: String? = null,
        var altSpellings: MutableList<String> = mutableListOf(),
        var area: Double? = null,
        var borders: MutableList<String> = mutableListOf(),
        var callingCodes: MutableList<String> = mutableListOf(),
        var capital: String? = null,
        var cioc: String? = null,
        var currencies: MutableList<Currency> = mutableListOf(),
        var demonym: String? = null,
        var flag: String? = null,
        var gini: Double? = null,
        var languages: MutableList<Language> = mutableListOf(),
        var latlng: MutableList<Double> = mutableListOf(),
        var name: String? = null,
        var nativeName: String? = null,
        var numericCode: String? = null,
        var population: Int? = null,
        var region: String = "",
        var regionalBlocs: MutableList<RegionalBloc> = mutableListOf(),
        var subregion: String? = null,
        var timezones: MutableList<String> = mutableListOf(),
        var topLevelDomain: MutableList<String> = mutableListOf(),
        var translations: Translations = Translations(),
        @Optional var regions: MutableList<Region> = mutableListOf()
)