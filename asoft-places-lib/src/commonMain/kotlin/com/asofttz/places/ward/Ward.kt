package com.asofttz.places.ward

import com.asofttz.places.street.Street
import kotlinx.serialization.Serializable

@Serializable
class Ward(var name: String = "",
           var streets: Array<Street> = arrayOf()
)