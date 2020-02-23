package tz.co.asoft.places.data.repo

import tz.co.asoft.places.data.dao.IPlacesDao

class PlacesRepo(private val dao: IPlacesDao) : IPlacesRepo {
    override suspend fun loadCountryByCode(code: String) = dao.loadCountryByCode(code)
    override suspend fun loadRegionsInCountryWithCode(code: String) = dao.loadRegionsInCountryWithCode(code)
    override suspend fun loadDistrictsIn(countryCode: String, regionName: String) = dao.loadDistrictsIn(countryCode, regionName)
    override suspend fun loadWardsIn(countryCode: String, regionName: String, districtName: String) = dao.loadWardsIn(countryCode, regionName, districtName)
    override suspend fun loadStreetsIn(countryCode: String, regionName: String, districtName: String, wardName: String) = dao.loadStreetsIn(countryCode, regionName, districtName, wardName)
}