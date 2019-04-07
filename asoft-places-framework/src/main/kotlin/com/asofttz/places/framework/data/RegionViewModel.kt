package com.asofttz.places.framework.data

import com.asofttz.places.country.Country
import com.asofttz.places.data.repo.region.RegionAbstractRepo
import com.asofttz.places.region.Region

class RegionViewModel(private val regionRepo: RegionAbstractRepo) {
    suspend fun loadAllRegionsIn(country: Country) = regionRepo.loadRegionsInCountryWithCode(country.alpha2Code ?: "TZ")
    suspend fun addRegionInCountryWithCode(code:String,r: Region) = regionRepo.createRegionInCountryWith(code,r)
}