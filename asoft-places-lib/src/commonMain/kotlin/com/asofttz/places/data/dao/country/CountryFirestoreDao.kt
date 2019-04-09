package com.asofttz.places.data.dao.country

import com.asofttz.firebase.firestore.*
import com.asofttz.persist.lock
import com.asofttz.places.country.Country
import com.asofttz.places.region.Region
import com.asofttz.rx.ObservableList

class CountryFirestoreDao private constructor(private val firestore: Firestore) : CountryAbstractDao() {
    companion object {
        private var instance: CountryAbstractDao? = null
        fun getInstance(firestore: Firestore) = instance ?: CountryFirestoreDao(firestore).also {
            instance = it
        }
    }

    override suspend fun loadCountryByCode(code: String): Country? {
        var country = cached.value.firstOrNull {
            it.alpha2Code == code || it.alpha3Code == code
        }

        if (country == null) {
            country = firestore.collection("countries").whereEqualTo("alpha2Code", code.toUpperCase()).get().await().docs.firstOrNull()?.toObject(Country())
        }

        if (country != null) {
            cached.add(country)
        }
        return country
    }

    override suspend fun loadAll(): ObservableList<Country> = lock {
        if (cached.size == 0) {
            firestore.collection("countries").get().await().docs.forEach {
                cached.value.add(it.toObject(Country()))
            }
        }
        cached
    }
}