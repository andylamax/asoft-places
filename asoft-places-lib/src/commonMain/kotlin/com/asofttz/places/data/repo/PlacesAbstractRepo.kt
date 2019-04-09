package com.asofttz.places.data.repo

import com.asofttz.persist.Repo
import com.asofttz.places.country.Country
import com.asofttz.places.data.dao.PlacesAbstractDao

abstract class PlacesAbstractRepo(private val dao: PlacesAbstractDao) : Repo<Country>(dao) {
    open suspend fun loadCountryByCode(code: String) = dao.loadCountryByCode(code)
    open suspend fun loadRegionsInCountryWithCode(code: String) = dao.loadRegionsInCountryWithCode(code)
    open suspend fun loadDistrictsIn(countryCode: String, regionName: String)= dao.loadDistrictsIn(countryCode, regionName)
    open suspend fun loadWardsIn(countryCode: String, regionName: String, districtName: String) = dao.loadWardsIn(countryCode, regionName, districtName)
    open suspend fun loadStreetsIn(countryCode: String, regionName: String, districtName: String, wardName: String) = dao.loadStreetsIn(countryCode, regionName, districtName, wardName)
}