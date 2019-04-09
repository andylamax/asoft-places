package com.asofttz.places.disctrict

import com.asofttz.places.ward.Ward
import kotlinx.serialization.Serializable

class District(
        var name: String = "",
        var regionName: String = "",
        var countryName: String = "",
        var wards: Array<Ward> = arrayOf()
)