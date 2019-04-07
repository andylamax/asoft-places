package com.asofttz.places.data.repo.district

import com.asofttz.places.data.dao.district.DistrictAbstractDao
import com.asofttz.places.data.dao.region.RegionAbstractDao

class DistrictRepo private constructor(
        private val regionDao: RegionAbstractDao,
        private val districtDao: DistrictAbstractDao
) : DistrictAbstractRepo(regionDao,districtDao) {
    companion object {
        private var instance: DistrictAbstractRepo? = null
        fun getInstance(regionDao: RegionAbstractDao,districtDao: DistrictAbstractDao) = instance
                ?: DistrictRepo(regionDao,districtDao).also {
                    instance = it
                }
    }
}