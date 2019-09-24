package tz.co.asoft.places.data.dao

import tz.co.asoft.persist.dao.Dao
import tz.co.asoft.places.country.Country
import tz.co.asoft.places.disctrict.District
import tz.co.asoft.places.region.Region
import tz.co.asoft.places.street.Street
import tz.co.asoft.places.ward.Ward

abstract class PlacesAbstractDao : Dao<Country>() {
    abstract suspend fun loadCountryByCode(code: String): Country?
    abstract suspend fun loadRegionsInCountryWithCode(code: String): Array<Region>
    abstract suspend fun loadDistrictsIn(countryCode: String, regionName: String): Array<District>
    abstract suspend fun loadWardsIn(countryCode: String, regionName: String, districtName: String): Array<Ward>
    abstract suspend fun loadStreetsIn(countryCode: String, regionName: String, districtName: String, wardName: String): Array<Street>
}