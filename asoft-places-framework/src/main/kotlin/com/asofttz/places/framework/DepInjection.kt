package com.asofttz.places.framework

import com.asofttz.places.framework.data.CountryViewModel
import com.asofttz.places.framework.data.RegionViewModel
import com.asofttz.places.injection as PlacesInjection

object injection {

    private val repo = PlacesInjection.repo

    object viewModel {
        val country get() = CountryViewModel(repo.country)
        val region get() = RegionViewModel(repo.region)
    }
}