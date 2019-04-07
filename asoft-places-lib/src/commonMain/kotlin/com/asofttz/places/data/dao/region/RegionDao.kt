package com.asofttz.places.data.dao.region

import com.asofttz.persist.DataSourceConfig
import com.asofttz.places.region.Region

class RegionDao private constructor(private val config: DataSourceConfig) : RegionAbstractDao() {
    private val tzRegions = arrayOf(
            "Arusha", "Dar-Es-Salaam", "Dodoma", "Iringa", "Kagera",
            "Katavi", "Kigoma", "Kilimanjaro", "Kusini Unguja",
            "Kusini-Pemba", "Lindi", "Manyara", "Mara", "Mbeya"
            , "Morogoro", "Mtwara", "Mwanza", "Njombe", "Pemba Kaskazini"
            , "Pemba Kusini", "Pwani", "Rukwa", "Ruvuma", "Shinyanga", "Simiyu"
            , "Singida", "Tabora", "Tanga", "Unguja Kaskazini", "Unguja Kusini"
    ).mapIndexed { index, s -> Region(id = index + 1, name = s) }.toMutableList()

    private val countryRegionMap = mutableMapOf(
            "TZ" to tzRegions
    )

    companion object {
        private var instance: RegionAbstractDao? = null
        fun getInstance(config: DataSourceConfig) = instance ?: RegionDao(config).also {
            instance = it
        }
    }

    override suspend fun createRegionInCountryWith(code: String, region: Region): Boolean {
        val regions = countryRegionMap.getOrPut(code.toUpperCase()) {
            mutableListOf()
        }
        return regions.add(region)
    }

    override suspend fun load(id: Int): Region? {
        val regions = mutableListOf<Region>()
        countryRegionMap.forEach {
            regions.addAll(it.value)
        }
        return regions.firstOrNull { it.id == id }
    }

    override suspend fun loadRegionsInCountryWithCode(code: String): Array<Region> {
        return countryRegionMap.getOrPut(code.toUpperCase()) {
            mutableListOf()
        }.toTypedArray()
    }
}