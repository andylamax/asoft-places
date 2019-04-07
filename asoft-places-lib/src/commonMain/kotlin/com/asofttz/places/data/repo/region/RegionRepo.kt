package com.asofttz.places.data.repo.region

import com.asofttz.places.data.dao.country.CountryAbstractDao
import com.asofttz.places.data.dao.region.RegionAbstractDao

class RegionRepo private constructor(
        private val countryDao: CountryAbstractDao,
        private val regionDao: RegionAbstractDao) : RegionAbstractRepo(countryDao, regionDao) {

    companion object {
        private var instance: RegionAbstractRepo? = null
        fun getInstance(countryDao: CountryAbstractDao, regionDao: RegionAbstractDao) = instance
                ?: RegionRepo(countryDao, regionDao).also {
                    instance = it
                }
    }
}