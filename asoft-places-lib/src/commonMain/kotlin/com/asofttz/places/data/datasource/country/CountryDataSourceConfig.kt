package com.asofttz.places.data.datasource.country

import com.asofttz.persist.DataSourceConfig

class CountryDataSourceConfig(url: String) : DataSourceConfig(url) {
    var specificUrlForCode = ""
}