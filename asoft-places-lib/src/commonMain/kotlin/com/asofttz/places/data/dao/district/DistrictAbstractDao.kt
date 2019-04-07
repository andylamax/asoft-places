package com.asofttz.places.data.dao.district

import com.asofttz.persist.Dao
import com.asofttz.places.disctrict.District

abstract class DistrictAbstractDao : Dao<District>() {
    abstract suspend fun loadDistrictFromRegionWithId(id: Long) : Array<District>
}