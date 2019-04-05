package com.asofttz.places

class Country(name: String) : Place(name) {
    var regions = arrayOf<Region>()
}