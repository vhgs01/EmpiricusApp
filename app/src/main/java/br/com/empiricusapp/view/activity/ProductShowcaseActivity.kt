package br.com.empiricusapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.empiricusapp.R
import br.com.empiricusapp.contract.ProductShowcaseContract
import br.com.empiricusapp.domain.BASE_URL
import br.com.empiricusapp.domain.ShowcaseAPI
import br.com.empiricusapp.model.ShowCase
import br.com.empiricusapp.view.adapter.ProductShowcaseAdapter
import kotlinx.android.synthetic.main.activity_product_showcase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductShowcaseActivity : AppCompatActivity(), ProductShowcaseContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_showcase)

        GlobalScope.launch(Dispatchers.IO) {
            val result = callApi()
            configureAdapter(result)
        }
    }

    override suspend fun callApi(): ShowCase {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShowcaseAPI::class.java)

        return api.getShowcases()
    }

    override fun configureAdapter(showcase: ShowCase) {
        val recyclerView = rvShowcase
        runOnUiThread {
            recyclerView.adapter = ProductShowcaseAdapter(showcase, this)
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}