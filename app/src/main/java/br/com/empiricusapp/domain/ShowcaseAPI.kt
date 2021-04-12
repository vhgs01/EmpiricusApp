package br.com.empiricusapp.domain

import br.com.empiricusapp.model.Showcase
import retrofit2.http.GET

const val BASE_URL = "https://empiricus-app.empiricus.com.br"

interface ShowcaseAPI {

    @GET("/mock/test/showcase.json")
    suspend fun getShowcases(): Showcase
}