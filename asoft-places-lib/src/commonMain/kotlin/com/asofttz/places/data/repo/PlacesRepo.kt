package com.asofttz.places.data.repo

import com.asofttz.places.data.dao.PlacesAbstractDao

class PlacesRepo private constructor(dao: PlacesAbstractDao) : PlacesAbstractRepo(dao) {
    companion object {
        private var instance : PlacesAbstractRepo? = null
        fun getInstance(dao: PlacesAbstractDao) = instance ?: PlacesRepo(dao).also {
            instance = it
        }
    }
}