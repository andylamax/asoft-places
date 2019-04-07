package com.asofttz.places.framework.admin.country

import com.asofttz.places.country.Country
import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.html.js.onClickFunction
import react.RBuilder
import styled.*

fun RBuilder.country(c: Country, css: CSSBuilder.() -> Unit = {}, onClick: () -> Unit) = styledDiv {
    css {
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns("1fr 1fr 1fr 1fr")
        alignItems = Align.center
        gap = Gap("0.5em")
        cursor = Cursor.pointer
        padding(0.5.em)
        boxShadow(Color.gray, blurRadius = 3.px, spreadRadius = 1.px)
        +css
    }

    attrs.onClickFunction = { onClick() }

    styledH3 {
        css {
            gridColumn = GridColumn("1/4")
        }
        +(c.name ?: "Not Found")
    }

    styledImg(src = c.flag) {
        css {
            width = 100.pct
            gridColumn = GridColumn("4/5")
        }
    }
}