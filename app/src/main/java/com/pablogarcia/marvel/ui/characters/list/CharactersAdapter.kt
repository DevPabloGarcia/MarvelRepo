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
import com.squareup.picasso.Picasso

class CharactersAdapter(
    private var onItemClickListener: (Character) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {

    private var characters: MutableList<Character> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        when (viewType){
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
                    LayoutInflater.from(parent.context).inflate(R.layout.row_character, parent, false),
                    onItemClickListener
                )
            }
        }
    }

    override fun getItemCount(): Int = characters.size.plus(1)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        if (holder is CharacterViewHolder && position < characters.size){
            characters.let {_characters ->
                holder.bind(_characters[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == characters.size) {
            return LOADING_ROW
        }
        return CHARACTER_ROW
    }

    fun setData(characters: List<Character>) {
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    companion object {
        const val CHARACTER_ROW = 0
        const val LOADING_ROW = 1
    }
}

open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

class CharacterViewHolder(private val view: View,
                          private val onItemClickListener: (Character) -> Unit
) : BaseViewHolder(view) {

    private val nameView: TextView = view.findViewById(R.id.row_character_name)
    private val imageView: ImageView = view.findViewById(R.id.row_character_image)
    private val likeImageView: ImageView = view.findViewById(R.id.row_character_like)

    fun bind(character: Character?) {

        character?.run {

            nameView.text = name
            if (character.id?.rem(2) == 0) likeImageView.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.ic_like_selected))
            Picasso.with(view.context)
                .load(character.obtainImage(Character.Companion.ImageType.LANDSCAPE_LARGE))
                .into(imageView)
        }
        character?.let {//

            view.setOnClickListener { onItemClickListener.invoke(character) }
        }
    }
}

class LoadingViewHolder(view: View) : BaseViewHolder(view)
