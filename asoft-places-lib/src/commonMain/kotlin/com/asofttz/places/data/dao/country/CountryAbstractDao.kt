package com.asofttz.places.data.dao.country

import com.asofttz.persist.Dao
import com.asofttz.places.country.Country

abstract class CountryAbstractDao : Dao<Country>() {
    abstract suspend fun loadCountryByCode(code: String): Country?
}