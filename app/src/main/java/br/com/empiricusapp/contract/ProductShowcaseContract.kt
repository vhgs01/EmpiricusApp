package br.com.empiricusapp.contract

import br.com.empiricusapp.model.Showcase

interface ProductShowcaseContract {
    interface View {
        suspend fun callApi(): Showcase
        fun configureAdapter(showcase: Showcase)
    }

    interface Presenter {

    }
}