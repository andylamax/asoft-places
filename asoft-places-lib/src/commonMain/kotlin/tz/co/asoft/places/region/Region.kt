package tz.co.asoft.places.region

import tz.co.asoft.places.disctrict.District

class Region(
        var id: Int = 0,
        var name: String = "",
        var countryName: String = "",
        var districts: Array<District> = arrayOf()
)