package com.asofttz.places.region

import com.asofttz.places.disctrict.District
import kotlinx.serialization.Serializable

class Region(
        var id: Int = 0,
        var name: String = "",
        var countryName: String = "",
        var districts: Array<District> = arrayOf()
)