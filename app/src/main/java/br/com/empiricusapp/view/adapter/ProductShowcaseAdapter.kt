package br.com.empiricusapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.empiricusapp.R
import br.com.empiricusapp.model.Group
import br.com.empiricusapp.model.Showcase
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.base_showcase_item.view.*
import java.util.*

class ProductShowcaseAdapter(
    private var showcases: MutableList<Showcase>,
    private val context: Context
) :
    RecyclerView.Adapter<ProductShowcaseAdapter.ViewHolder>(), Filterable {

    val itemsFilter = showcases.toMutableList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var showcaseImage = itemView.ivShowcaseAuthor!!
        var showcaseIdentifierName = itemView.tvIdentifierName!!
        var showcaseAuthor = itemView.tvAuthor!!
        var showcaseShortDescription = itemView.tvShortDescription!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.base_showcase_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return showcases.first().groups.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val showcase = showcases.first().groups[position]
        configureShowcaseView(holder, showcase)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(filter: CharSequence): FilterResults {
                val filterString = filter.toString().toLowerCase(Locale.ROOT)
                var filterList = mutableListOf<Showcase>()
                val listOfGroups = mutableListOf<Group>()
                val filterResults = FilterResults()

                if (filterString.isEmpty()) {
                    filterList = itemsFilter
                } else {
                    itemsFilter.forEachIndexed { _, itemsFilter ->
                        for (group in itemsFilter.groups) {
                            if (group.name.toLowerCase(Locale.ROOT).contains(filterString)) {
                                listOfGroups.add(group)
                            }
                        }
                        filterList.add(0, Showcase(listOfGroups))
                    }
                }

                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                showcases.clear()
                showcases.addAll(results.values as Collection<Showcase>)

                notifyDataSetChanged()
            }
        }
    }

    private fun configureShowcaseView(it: ViewHolder, showcase: Group) {
        it.showcaseIdentifierName.text = showcase.name
        it.showcaseShortDescription.text = showcase.shortDescription
        it.showcaseAuthor.text = getAllAuthors(showcase)

        loadImage(showcase.backgroundSmall, it.showcaseImage)
    }

    private fun getAllAuthors(showcase: Group): String {
        var authors = ""

        for (author in showcase.authors) {
            authors += author.name + " "
        }

        return authors
    }

    private fun loadImage(url: String?, it: ImageView) {
        Glide.with(context).load(url).into(it)
    }

}