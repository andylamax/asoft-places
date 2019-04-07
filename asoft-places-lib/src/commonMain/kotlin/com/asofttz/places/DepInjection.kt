package com.asofttz.places

import com.asofttz.persist.DataSourceConfig
import com.asofttz.persist.RepoFactory
import com.asofttz.places.data.dao.country.CountryDao
import com.asofttz.places.data.dao.district.DistrictDao
import com.asofttz.places.data.dao.region.RegionDao
import com.asofttz.places.data.datasource.country.CountryDataSourceConfig
import com.asofttz.places.data.repo.country.CountryRepo
import com.asofttz.places.data.repo.district.DistrictRepo
import com.asofttz.places.data.repo.region.RegionRepo

object injection {
    private object config {
        val country = CountryDataSourceConfig(url = "https://restcountries.eu/rest/v2/all").apply {
            specificUrlForCode = "https://restcountries.eu/rest/v2/alpha"
        }

        val region = DataSourceConfig()
        val district = DataSourceConfig()
    }

    private object dao {
        val country = CountryDao.getInstance(config.country)
        val region = RegionDao.getInstance(config.region)
        val district = DistrictDao.getInstance(config.district)
    }

    object repo {
        val country = CountryRepo.getInstance(dao.country)
        val region = RegionRepo.getInstance(dao.country, dao.region)
        val district = DistrictRepo.getInstance(dao.region, dao.district)
    }
}