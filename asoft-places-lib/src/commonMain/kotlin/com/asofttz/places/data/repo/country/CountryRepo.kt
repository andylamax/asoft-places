package com.asofttz.places.data.repo.country

import com.asofttz.places.data.dao.country.CountryAbstractDao

class CountryRepo private constructor(private val dao: CountryAbstractDao) : CountryAbstractRepo(dao) {

    companion object {
        private var instance: CountryAbstractRepo? = null

        fun getInstance(dao: CountryAbstractDao) = instance ?: CountryRepo(dao).also {
            instance = it
        }
    }
}