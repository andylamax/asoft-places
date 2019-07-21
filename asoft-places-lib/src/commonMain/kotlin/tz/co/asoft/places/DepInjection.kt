package tz.co.asoft.places

import com.asofttz.firebase.app.Options
import com.asofttz.firebase.app.firebase
import com.asofttz.persist.DataSourceConfig
import tz.co.asoft.places.data.dao.PlacesFirebaseDao
import tz.co.asoft.places.data.repo.PlacesRepo
import tz.co.asoft.places.data.viewmodel.TZPlacesViewModel

object injection {
    private object config {
        val region = DataSourceConfig()
        val district = DataSourceConfig()

        val firebase = Options().apply {
            apiKey = "AIzaSyAAG_21gH0J526JoNvsfQ86mqAwci4LUic"
            authDomain = "asoft-places.firebaseapp.com"
            databaseURL = "https=//asoft-places.firebaseio.com"
            projectId = "asoft-places"
            storageBucket = "asoft-places.appspot.com"
            messagingSenderId = "934679534088"
        }
    }

    private object app {
        private val fBase = firebase.initializeApp(config.firebase)
        val firestore = fBase.firestore()
    }

    private object dao {
        val places = PlacesFirebaseDao.getInstance(app.firestore)
    }

    object repo {
        val places = PlacesRepo.getInstance(dao.places)
    }

    object viewModel {
        val places get() = TZPlacesViewModel(repo.places)
    }
}