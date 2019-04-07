package com.asofttz.places.data.repo.region

import com.asofttz.persist.Repo
import com.asofttz.places.data.dao.country.CountryAbstractDao
import com.asofttz.places.data.dao.region.RegionAbstractDao
import com.asofttz.places.region.Region

abstract class RegionAbstractRepo(
        private val countryDao: CountryAbstractDao,
        private val regionDao: RegionAbstractDao) : Repo<Region>(regionDao) {

    open suspend fun createRegionInCountryWith(code: String, region: Region) = regionDao.createRegionInCountryWith(code, region)

    open suspend fun loadRegionsInCountryWithCode(code: String): Array<Region> {
        val regions = regionDao.loadRegionsInCountryWithCode(code)
        countryDao.loadCountryByCode(code)?.regions = regions
        return regions
    }
}