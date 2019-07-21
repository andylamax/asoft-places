package tz.co.asoft.places

import tz.co.asoft.places.country.Country

class Continent(var name: String = "") {
    var countries = arrayOf<Country>()
}