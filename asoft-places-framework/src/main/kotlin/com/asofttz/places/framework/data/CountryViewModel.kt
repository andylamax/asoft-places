package com.asofttz.places.framework.data

import com.asofttz.places.data.repo.country.CountryAbstractRepo

class CountryViewModel(private val countryRepo: CountryAbstractRepo) {
    suspend fun loadAllCountries() = countryRepo.loadAll()
}