package com.asofttz.places.framework.admin

import com.asofttz.module.ModuleProps
import com.asofttz.module.react.ObservingComponent
import com.asofttz.places.country.Country
import com.asofttz.places.framework.admin.Admin.Props
import com.asofttz.places.framework.admin.Admin.State
import com.asofttz.places.framework.admin.country.Countries
import com.asofttz.places.framework.admin.regions.Regions
import react.RBuilder
import react.RState
import react.setState

class Admin(p: Props) : ObservingComponent<Country, Props, State>(p) {
    object Props : ModuleProps()

    class State : RState {
        var currentUI = UI.countries
        var country: Country = Country()
    }

    enum class UI {
        countries, regions
    }

    init {
        state = State()
    }

    private fun RBuilder.countries() = child(Countries::class.js, Countries.Props) {
        attrs.onCountrySelected = {
            setState {
                country = it
                currentUI = UI.regions
            }
        }
    }

    private fun RBuilder.regions() = child(Regions::class.js, Regions.Props) {
        attrs.country = state.country
    }

    override fun RBuilder.render(): dynamic = when (state.currentUI) {
        UI.countries -> countries()
        UI.regions -> regions()
    }
}