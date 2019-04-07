package com.asofttz.places.data.repo.district

import com.asofttz.persist.Repo
import com.asofttz.places.data.dao.district.DistrictAbstractDao
import com.asofttz.places.data.dao.region.RegionAbstractDao
import com.asofttz.places.disctrict.District

abstract class DistrictAbstractRepo(
        private val regionDao: RegionAbstractDao,
        private val districtDao: DistrictAbstractDao
) : Repo<District>(districtDao) {

    open suspend fun loadDistrictsFromRegionWithId(id: Long) : Array<District> {
        val districts = districtDao.loadDistrictFromRegionWithId(id)
        regionDao.load(id.toInt())?.districts = districts
        return districts
    }
}