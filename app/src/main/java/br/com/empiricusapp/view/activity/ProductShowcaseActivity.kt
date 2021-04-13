package br.com.empiricusapp.view.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.empiricusapp.R
import br.com.empiricusapp.contract.ProductShowcaseContract
import br.com.empiricusapp.domain.BASE_URL
import br.com.empiricusapp.domain.ShowcaseAPI
import br.com.empiricusapp.model.Showcase
import br.com.empiricusapp.view.adapter.ProductShowcaseAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_product_showcase.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProductShowcaseActivity : AppCompatActivity(), ProductShowcaseContract.View {

    private lateinit var adapter: ProductShowcaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_showcase)

        GlobalScope.launch(Dispatchers.IO) {
            configureAdapter(getShowcasesByAPI())
        }

        configureBottomNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        menuInflater.inflate(R.menu.options_menu, menu)

        return true
    }

    override suspend fun getShowcasesByAPI(): Showcase {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShowcaseAPI::class.java)

        return api.getShowcases()
    }

    override fun configureAdapter(showcase: Showcase) {
        val recyclerView = rvShowcase

        runOnUiThread {
            val tempList: MutableList<Showcase> = ArrayList()
            tempList.add(showcase)

            adapter = ProductShowcaseAdapter(tempList, this)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun configureBottomNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.background = null

        setNavViewListener()
    }

    private fun setNavViewListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.filter.filter(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }

}