package tz.co.asoft.places.disctrict

import tz.co.asoft.places.ward.Ward

class District(
        var name: String = "",
        var regionName: String = "",
        var countryName: String = "",
        var wards: Array<Ward> = arrayOf()
)