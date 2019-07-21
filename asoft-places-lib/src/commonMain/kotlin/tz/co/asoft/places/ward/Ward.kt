package tz.co.asoft.places.ward

import tz.co.asoft.places.street.Street

class Ward(var name: String = "",
           var districtName: String = "",
           var regionName: String = "",
           var countryName: String = "",
           var streets: Array<Street> = arrayOf()
)