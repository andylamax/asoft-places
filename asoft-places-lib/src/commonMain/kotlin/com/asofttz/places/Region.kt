package com.asofttz.places

class Region(name: String) : Place(name) {
    var districts = arrayOf<District>()
}