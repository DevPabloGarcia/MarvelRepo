package com.pablogarcia.marvel.ui.characters.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pablogarcia.marvel.R
import com.pablogarcia.marvel.model.Character
import com.squareup.picasso.Picasso

class CharactersAdapter(
    private var onItemClickListener: (Character) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {

    private var characters: List<Character>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {

        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_character, parent, false),
            onItemClickListener
        )
    }

    override fun getItemCount(): Int = characters?.size ?: 0


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        characters?.let {_characters ->

            holder.bind(_characters[position])
        }
    }

    fun setData(characters: List<Character>?) {

        this.characters = characters
        notifyDataSetChanged()
    }

}

class CharacterViewHolder(val view: View,
                          val onItemClickListener: (Character) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val nameView: TextView = view.findViewById(R.id.row_character_name)
    private val imageView: ImageView = view.findViewById(R.id.row_character_image)

    fun bind(character: Character?) {

        character?.run {

            nameView.text = name
            Picasso.with(view.context)
                .load(character.obtainImage(Character.Companion.ImageType.LANDSCAPE_LARGE))
                .into(imageView)
        }
        character?.let {

            view.setOnClickListener { onItemClickListener.invoke(character) }
        }
    }
}
