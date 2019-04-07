package com.asofttz.places.framework.admin.country

import com.asofttz.module.ModuleProps
import com.asofttz.module.react.ObservingComponent
import com.asofttz.places.country.Country
import com.asofttz.places.framework.admin.country.Countries.Props
import com.asofttz.places.framework.admin.country.Countries.State
import com.asofttz.places.framework.injection
import com.asofttz.ui.react.layout.centerLayout
import com.asofttz.ui.react.tools.css
import com.asofttz.ui.react.tools.isDesktop
import kotlinx.coroutines.launch
import kotlinx.css.*
import react.RBuilder
import react.RState
import react.setState
import styled.css
import styled.styledButton
import styled.styledDiv

class Countries : ObservingComponent<Country, Props, State>() {
    private val viewModel = injection.viewModel.country

    object Props : ModuleProps() {
        var onCountrySelected = { _: Country -> }
    }

    class State : RState {
        var countries = arrayOf<Country>()
        var doneLoading = false
    }

    init {
        state = State()
    }

    override fun componentDidMount() {
        launch {
            subscriber = viewModel.loadAllCountries().subscribe {
                setState {
                    countries = it.toTypedArray()
                    doneLoading = true
                }
            }
        }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            width = 100.pct
            padding(1.em)
            display = Display.grid
            gap = Gap("1em")
            gridTemplateColumns = GridTemplateColumns("1fr 1fr" + if (isDesktop) " 1fr 1fr" else "")
        }

        if (state.countries.isNotEmpty()) {
            state.countries.forEach {
                country(it) {
                    props.onCountrySelected(it)
                }
            }
        } else {
            centerLayout {
                css {
                    gridColumn = GridColumn("1/" + if (isDesktop) "5" else "3")
                    height = 100.vh
                }
                +"Loading countries please wait"
            }
        }
    }
}