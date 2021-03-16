package com.pablogarcia.marvel.ui.characters.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pablogarcia.marvel.R
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Thumbnail
import com.squareup.picasso.Picasso

class CharactersAdapter(
    private val viewModel: CharacterListViewModel,
    private var onItemClickListener: (Character) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.BaseViewHolder>() {

    private var characters: MutableList<Character> = mutableListOf()
    private var filteredCharacter: MutableList<Character> = mutableListOf()
    var isFiltered = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        when (viewType) {
            LOADING_ROW -> {
                return LoadingViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.row_loading,
                        parent,
                        false
                    )
                )
            }
            else -> {
                return CharacterViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_character, parent, false),
                    onItemClickListener
                )
            }
        }
    }

    override fun getItemCount(): Int {

        return if (!isFiltered)
            filteredCharacter.size.plus(1)
        else
            filteredCharacter.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        if (holder is CharacterViewHolder && position < filteredCharacter.size) {
            filteredCharacter.let { _characters ->
                holder.bind(_characters[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (!isFiltered && position == filteredCharacter.size) {
            return LOADING_ROW
        }
        return CHARACTER_ROW
    }

    //region PUBLIC_METHODS

    /**
     * Set data to adapter
     *
     * @param characters
     */
    fun setData(characters: List<Character>) {

        this.characters.addAll(characters)
        this.filteredCharacter.addAll(characters)
        notifyDataSetChanged()
    }

    /**
     * Called when like item is clicked
     * - Update character like value
     *
     * @param character - character to be updated
     */
    fun likeClicked(character: Character?) {
        character?.let {
            val index = characters.indexOf(character)
            characters[index].like = !characters[index].like
            notifyItemChanged(index)
            viewModel.updateLike(character)
        }
    }

    /**
     * Clear filter in order to show all characters
     */
    fun clearFilter(){
        filteredCharacter.clear()
        filteredCharacter.addAll(characters)
        isFiltered = false
        notifyDataSetChanged()
    }

    /**
     * Add filter in order to show favorite characters
     */
    fun filterFavorites() {
        filteredCharacter.clear()
        filteredCharacter.addAll(characters.filter { it.like })
        isFiltered = true
        notifyDataSetChanged()
    }

    //endregion

    companion object {
        const val CHARACTER_ROW = 0
        const val LOADING_ROW = 1
    }

    open inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class LoadingViewHolder(view: View) : BaseViewHolder(view)

    inner class CharacterViewHolder(
        private val view: View,
        private val onItemClickListener: (Character) -> Unit
    ) : BaseViewHolder(view) {

        private val nameView: TextView = view.findViewById(R.id.row_character_name)
        private val imageView: ImageView = view.findViewById(R.id.row_character_image)
        private val likeImageView: ImageView = view.findViewById(R.id.row_character_like)

        fun bind(character: Character?) {

            character?.run {

                nameView.text = name
                val drawableResource = if (character.like)
                    R.drawable.ic_like_selected
                else
                    R.drawable.ic_like
                likeImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        view.context,
                        drawableResource
                    )
                )
                Picasso.with(view.context)
                    .load(character.thumbnail?.obtainImage(Thumbnail.Companion.ImageType.LANDSCAPE_LARGE))
                    .into(imageView)
                view.setOnClickListener { onItemClickListener.invoke(this) }
                likeImageView.setOnClickListener { likeClicked(character) }
            }
        }
    }
}
