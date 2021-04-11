package br.com.empiricusapp.contract

import br.com.empiricusapp.model.ShowCase

interface ProductShowcaseContract {
    interface View {
        suspend fun callApi(): ShowCase
        fun configureAdapter(showcase: ShowCase)
    }

    interface Presenter {

    }
}