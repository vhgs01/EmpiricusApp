package br.com.empiricusapp.contract

import br.com.empiricusapp.model.Showcase

interface ProductShowcaseContract {
    interface View {
        suspend fun getShowcasesByAPI(): Showcase
        fun configureAdapter(showcase: Showcase)
        fun configureBottomNavigation()
    }
}