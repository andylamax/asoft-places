package com.asofttz.places.region

import com.asofttz.places.disctrict.District
import kotlinx.serialization.Serializable

@Serializable
class Region(
        var id: Int = 0,
        var name: String = "",
        var districts: Array<District> = arrayOf()
)