package com.asofttz.places.data.dao.country

import com.asofttz.persist.lock
import com.asofttz.places.country.Country
import com.asofttz.places.data.datasource.country.CountryDataSourceConfig
import com.asofttz.rx.ObservableList
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list

class CountryDao private constructor(private val config: CountryDataSourceConfig) : CountryAbstractDao() {
    private val client = HttpClient { }

    companion object {
        private var instance: CountryAbstractDao? = null
        fun getInstance(config: CountryDataSourceConfig) = instance ?: CountryDao(config).also {
            instance = it
        }
    }

    override suspend fun loadCountryByCode(code: String): Country? {
        var country = cached.value.filter {
            it.alpha2Code == code || it.alpha3Code == code
        }.firstOrNull()

        if (country == null) {
            val cdata = client.get<String>("${config.specificUrlForCode}/$code")
            country = try {
                JSON.parse(Country.serializer(), cdata)
            } catch (c: Throwable) {
                println("asoft-places api")
                null
            }
        }

        if (country != null) {
            cached.add(country)
        }
        return country
    }

    override suspend fun loadAll(): ObservableList<Country> = lock {
        val resp = client.get<String>(config.url)
        val data = try {
            JSON.nonstrict.parse(Country.serializer().list, resp)
        } catch (c: Throwable) {
            println("asoft-places api: ${c.message}")
            listOf<Country>()
        }
        cached.value.addAll(data)
        cached
    }
}