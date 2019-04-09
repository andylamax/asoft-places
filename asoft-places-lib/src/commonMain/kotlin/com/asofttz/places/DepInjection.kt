package com.asofttz.places

import com.asofttz.firebase.app.Options
import com.asofttz.firebase.app.firebase
import com.asofttz.persist.DataSourceConfig
import com.asofttz.places.data.dao.PlacesFirebaseDao
import com.asofttz.places.data.dao.country.CountryFirestoreDao
import com.asofttz.places.data.dao.district.DistrictDao
import com.asofttz.places.data.dao.region.RegionDao
import com.asofttz.places.data.repo.PlacesRepo
import com.asofttz.places.data.repo.country.CountryRepo
import com.asofttz.places.data.repo.district.DistrictRepo
import com.asofttz.places.data.repo.region.RegionRepo

object injection {
    private object config {
        val region = DataSourceConfig()
        val district = DataSourceConfig()

        val firebaseConfig = Options().apply {

        }
    }

    private object app {
        val fBase = firebase.initializeApp(config.firebaseConfig)
        val firestore = fBase.firestore()
    }

    private object dao {
        //        val country = CountryDao.getInstance(config.country)

        var country = CountryFirestoreDao.getInstance(app.firestore)
        val region = RegionDao.getInstance(config.region)
        val district = DistrictDao.getInstance(config.district)

        val places = PlacesFirebaseDao.getInstance(app.firestore)
    }

    object repo {
        val country = CountryRepo.getInstance(dao.country)
        val region = RegionRepo.getInstance(dao.country, dao.region)
        val district = DistrictRepo.getInstance(dao.region, dao.district)
        val places = PlacesRepo.getInstance(dao.places)
    }
}