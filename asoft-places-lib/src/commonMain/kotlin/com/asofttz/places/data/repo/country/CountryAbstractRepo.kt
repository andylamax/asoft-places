package com.asofttz.places.data.repo.country

import com.asofttz.persist.Repo
import com.asofttz.places.country.Country
import com.asofttz.places.data.dao.country.CountryAbstractDao

abstract class CountryAbstractRepo(private val dao: CountryAbstractDao) : Repo<Country>(dao) {
    suspend fun loadCountryByCode(code: String): Country? = dao.loadCountryByCode(code)
}