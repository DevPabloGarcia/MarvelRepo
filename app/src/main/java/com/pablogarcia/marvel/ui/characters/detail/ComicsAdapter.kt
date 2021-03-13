package com.pablogarcia.marvel.ui.characters.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pablogarcia.marvel.R
import com.pablogarcia.marvel.model.Comic
import com.pablogarcia.marvel.model.Thumbnail
import com.squareup.picasso.Picasso

class ComicsAdapter: RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>() {

    private var comics: MutableList<Comic> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_comic,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = comics.size


    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {

        holder.bind(comics[position])
    }

    /**
     * Set comics data
     */
    fun setData(comics: List<Comic>) {

        this.comics.clear()
        this.comics.addAll(comics)
        notifyDataSetChanged()
    }

    inner class ComicViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.row_comic_image)

        fun bind(comic: Comic) {

            Picasso.with(view.context)
                .load(comic.thumbnail?.obtainImage(Thumbnail.Companion.ImageType.PORTRAIT_LARGE))
                .into(imageView)

        }
    }
}
