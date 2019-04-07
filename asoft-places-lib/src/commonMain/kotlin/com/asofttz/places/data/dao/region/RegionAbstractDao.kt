package com.asofttz.places.data.dao.region

import com.asofttz.persist.Dao
import com.asofttz.places.country.Country
import com.asofttz.places.data.dao.country.CountryAbstractDao
import com.asofttz.places.region.Region
import com.asofttz.rx.ObservableList

abstract class RegionAbstractDao : Dao<Region>() {
    abstract suspend fun loadRegionsInCountryWithCode(code: String): Array<Region>

    abstract suspend fun createRegionInCountryWith(code: String,region: Region) : Boolean
    @Deprecated("Do no use this method", ReplaceWith("loadRegionsFromCountry(countryId: Int)"))
    final override suspend fun loadAll(): ObservableList<Region> = cached
}