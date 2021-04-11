package br.com.empiricusapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.empiricusapp.R
import br.com.empiricusapp.model.Group
import br.com.empiricusapp.model.ShowCase
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.base_showcase_item.view.*

class ProductShowcaseAdapter(private val showcases: ShowCase, private val context: Context) :
    RecyclerView.Adapter<ProductShowcaseAdapter.ViewHolder>() {

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
        return showcases.groups.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val showcase = showcases.groups[position]
        configureShowcaseView(holder, showcase)
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