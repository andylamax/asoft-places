package com.asofttz.places

class District(name: String) : Place(name) {
    var wards = arrayOf<Ward>()
}