package com.asofttz.places

import com.asofttz.places.country.Country

class Continent(var name: String = "") {
    var countries = arrayOf<Country>()
}