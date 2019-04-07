package com.asofttz.places.disctrict

import com.asofttz.places.ward.Ward
import kotlinx.serialization.Serializable

@Serializable
class District(
        var name: String = "",
        var wards: Array<Ward> = arrayOf()
)