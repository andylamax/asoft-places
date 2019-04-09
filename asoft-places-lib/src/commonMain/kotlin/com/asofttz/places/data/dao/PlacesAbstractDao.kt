package com.asofttz.places.data.dao

import com.asofttz.persist.Dao
import com.asofttz.places.country.Country
import com.asofttz.places.disctrict.District
import com.asofttz.places.region.Region
import com.asofttz.places.street.Street
import com.asofttz.places.ward.Ward

abstract class PlacesAbstractDao : Dao<Country>() {
    abstract suspend fun loadCountryByCode(code: String): Country?
    abstract suspend fun loadRegionsInCountryWithCode(code: String): Array<Region>
    abstract suspend fun loadDistrictsIn(countryCode: String, regionName: String): Array<District>
    abstract suspend fun loadWardsIn(countryCode: String, regionName: String, districtName: String): Array<Ward>
    abstract suspend fun loadStreetsIn(countryCode: String, regionName: String, districtName: String, wardName: String): Array<Street>
}