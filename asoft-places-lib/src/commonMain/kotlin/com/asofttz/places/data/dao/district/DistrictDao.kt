package com.asofttz.places.data.dao.district

import com.asofttz.persist.DataSourceConfig
import com.asofttz.places.disctrict.District

class DistrictDao private constructor(private val config: DataSourceConfig) : DistrictAbstractDao() {
    private val dsmDistricts = arrayOf("Ilala", "Kigamboni", "Kinondoni", "Temeke", "Ubungo").map {
        District(name = it)
    }.toTypedArray()

    private val cachedRegionDistrictMap = mutableMapOf<Long, Array<District>>()

    init {
        cachedRegionDistrictMap[2L] = dsmDistricts
    }

    companion object {
        private var instance: DistrictAbstractDao? = null
        fun getInstance(config: DataSourceConfig) = instance ?: DistrictDao(config).also {
            instance = it
        }
    }

    override suspend fun loadDistrictFromRegionWithId(id: Long): Array<District> {
        return cachedRegionDistrictMap.getOrPut(id) {
            arrayOf()
        }
    }
}