package com.asofttz.places.framework

import com.asofttz.date.Date
import com.asofttz.framework.SettingsModule
import com.asofttz.framework.framework
import com.asofttz.places.framework.admin.AdminModule
import com.asofttz.theme.Theme
import react.dom.render
import kotlin.browser.document

fun main() = render(document.getElementById("root")) {
    framework {
        attrs {
            title = "aSoft Places"
            val copyright = "Copyright ${169.toChar()} ${Date().getFullYear()}"
            footer = "$copyright aSoft Ltd - All Rights Reserved"
            themes = arrayOf(Theme())
            isDevMode = true

            pages = arrayOf()

            modules = arrayOf(
                    AdminModule,
                    SettingsModule
            )
        }
    }
}