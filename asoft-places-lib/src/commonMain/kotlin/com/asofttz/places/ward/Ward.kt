package com.asofttz.places.ward

import com.asofttz.places.street.Street

class Ward(var name: String = "",
           var districtName: String = "",
           var regionName: String = "",
           var countryName: String = "",
           var streets: Array<Street> = arrayOf()
)