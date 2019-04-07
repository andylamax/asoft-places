package com.asofttz.places.framework.admin.regions

import com.asofttz.module.ModuleProps
import com.asofttz.module.react.ScopedRComponent
import com.asofttz.places.country.Country
import com.asofttz.places.framework.admin.country.country
import com.asofttz.places.framework.admin.regions.Regions.Props
import com.asofttz.places.framework.admin.regions.Regions.State
import com.asofttz.places.framework.injection
import com.asofttz.places.region.Region
import com.asofttz.ui.react.icons.reacticons.icon
import com.asofttz.ui.react.layout.centerLayout
import com.asofttz.ui.react.tools.css
import com.asofttz.ui.react.tools.isDesktop
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RState
import styled.*
import kotlin.browser.document
import kotlin.browser.window

class Regions : ScopedRComponent<Props, State>() {
    private val viewModel = injection.viewModel.region

    object Props : ModuleProps() {
        var country: Country = Country()
    }

    class State : RState {
        var regions = arrayOf<Region>()
        var doneLoading = false
        var newRegion = ""
    }

    init {
        state = State()
    }

    override fun componentDidMount() {
        fetchRegions()
    }

    private fun fetchRegions() = launch {
        syncState {
            regions = viewModel.loadAllRegionsIn(props.country)
            doneLoading = true
        }
    }

    private fun RBuilder.region(r: Region) = styledDiv {
        css {
            display = Display.grid
            gridTemplateColumns = GridTemplateColumns("1fr 1fr 1fr 1fr")
            alignItems = Align.center
            cursor = Cursor.pointer
            gap = Gap("0.5em")
            padding(0.5.em)
            boxShadow(Color.gray, blurRadius = 3.px, spreadRadius = 1.px)
        }

        styledH4 {
            css {
                gridColumn = GridColumn("1/4")
            }
            +r.name
        }
        styledDiv {
            css { fontSize = 1.em }
            icon("fa/FaTrashAlt")
        }
    }

    private fun add() = launch {
        if (viewModel.addRegionInCountryWithCode(props.country.alpha2Code!!, Region().apply {
                    name = state.newRegion.toLowerCase().capitalize()
                })) {
            fetchRegions()
            (document.getElementById("adder") as HTMLInputElement?)?.value = ""
            state.newRegion = ""
        }
    }

    private fun RBuilder.addForm() = styledDiv {
        css {
            gridColumn = GridColumn("1/" + if (isDesktop) "5" else "3")
        }
        styledInput {
            attrs.placeholder = "Add Region"
            attrs.id = "adder"
            attrs.onChangeFunction = {
                state.newRegion = it.target.asDynamic().value
            }
        }

        styledButton {
            attrs.onClickFunction = {
                if (state.newRegion.isNotBlank()) {
                    add()
                } else {
                    window.alert("Can't add an empty region")
                }
            }
            +"Add"
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

        country(props.country, css = {
            gridColumn = GridColumn("1/" + if (isDesktop) "5" else "3")
        }) {}

        addForm()
        if (state.regions.isNotEmpty()) {
            state.regions.forEach {
                region(it)
            }
        } else {
            centerLayout {
                css {
                    gridColumn = GridColumn("1/" + if (isDesktop) "5" else "3")
                    height = 100.vh
                }
                +(
                        if (state.doneLoading)
                            "No regions Found"
                        else
                            "Loading regions please wait"
                        )
            }
        }
    }
}