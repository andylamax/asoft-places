package com.asofttz.places.framework.admin

import com.asofttz.module.Module
import com.asofttz.module.ModuleProps
import react.buildElement
import react.router.dom.RouteResultProps

object AdminModule : Module() {
    override val name = "Places"
    override val author = "andylamax <andylamax@programmer.net>"
    override val version = "0.0.0"
    override var icon = "settings"
    override val mainSection = object : Section() {
        override val name: String = "Places"
        override val permits: Array<String> = arrayOf(":admin")
        override val route: String = "/"
        override val component = Appearance
    }
    override val sections: Array<Section> = arrayOf()
}

private val Appearance = { props: RouteResultProps<ModuleProps> ->
    with(props.match.params) {
        setTitle("aSoft Places")
        buildElement {
            child(Admin::class.js,Admin.Props) {}
        }
    }
}