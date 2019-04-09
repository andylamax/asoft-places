package com.asofttz.places.data.dao

import com.asofttz.firebase.firestore.*
import com.asofttz.persist.lock
import com.asofttz.places.country.Country
import com.asofttz.places.disctrict.District
import com.asofttz.places.region.Region
import com.asofttz.places.street.Street
import com.asofttz.places.ward.Ward
import com.asofttz.rx.ObservableList

class PlacesFirebaseDao private constructor(private val firestore: Firestore) : PlacesAbstractDao() {
    companion object {
        private var instance: PlacesAbstractDao? = null
        fun getInstance(firestore: Firestore) = instance ?: PlacesFirebaseDao(firestore).also {
            instance = it
        }
    }

    private suspend fun getDocs(path: String) = firestore.collection(path).get().await().docs

    private suspend fun loadCountry(query: CollectionReference.() -> Query): Country? {
        return firestore.collection("countries").query().get().await().docs.firstOrNull()?.toObject(Country())
    }

    override suspend fun loadCountryByCode(code: String): Country? {
        var country = cached.value.firstOrNull {
            it.alpha2Code == code || it.alpha3Code == code
        }

        if (country == null) {
            country = loadCountry { whereEqualTo("alpha2Code", code.toUpperCase()) }
        }

        if (country != null) {
            cached.add(country)
        }
        return country
    }

    override suspend fun loadRegionsInCountryWithCode(code: String): Array<Region> {
        var regions = arrayOf<Region>()
        val country = loadCountryByCode(code)
        if (country != null) {
            if (country.regions.isNotEmpty()) {
                return country.regions
            }
            getDocs("countries/${country.name}/regions").forEach {
                regions += it.toObject(Region()).apply {
                    countryName = country.name!!
                }
            }
            country.regions = regions
        }
        return regions
    }

    override suspend fun loadDistrictsIn(countryCode: String, regionName: String): Array<District> {
        var districts = arrayOf<District>()
        val region = loadRegionsInCountryWithCode(countryCode).firstOrNull {
            it.name == regionName
        }
        if (region != null) {
            if (region.districts.isNotEmpty()) {
                return region.districts
            }
            val path = "countries/${region.countryName}/regions/${region.name}/districts"
            getDocs(path).forEach {
                districts += it.toObject(District()).also { d ->
                    d.regionName = region.name
                    d.countryName = region.countryName
                }
            }
            region.districts = districts
        }
        return districts
    }

    override suspend fun loadWardsIn(countryCode: String, regionName: String, districtName: String): Array<Ward> {
        var wards = arrayOf<Ward>()
        val district = loadDistrictsIn(countryCode, regionName).firstOrNull {
            it.name == districtName
        }
        if (district != null) {
            val path = "countries/${district.countryName}/regions/${district.regionName}/districts/${district.name}/wards"
            getDocs(path).forEach {
                wards += it.toObject(Ward()).also {w->
                    w.countryName = district.countryName
                    w.regionName = district.regionName
                    w.districtName = district.name
                }
            }
            district.wards = wards
        }
        return wards
    }

    override suspend fun loadStreetsIn(countryCode: String, regionName: String, districtName: String, wardName: String): Array<Street> {
        var streets = arrayOf<Street>()
        val ward = loadWardsIn(countryCode, regionName, districtName).firstOrNull {
            it.name == wardName
        }

        if (ward != null) {
            val path = "countries/${ward.countryName}/regions/${ward.regionName}/districts/${ward.districtName}/wards/${ward.name}/streets"
            getDocs(path).forEach {
                streets += it.toObject(Street())
            }
            ward.streets = streets
        }
        return streets
    }

    override suspend fun loadAll(): ObservableList<Country> = lock {
        if (cached.size == 0) {
            getDocs("countries").forEach {
                cached.value.add(it.toObject(Country()))
            }
        }
        cached
    }
}