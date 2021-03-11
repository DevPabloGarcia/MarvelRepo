package com.pablogarcia.marvel.ui.characters.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.pablogarcia.marvel.R
import com.pablogarcia.marvel.di.MarvelApplication
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CharacterDetailFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: CharacterDetailViewModel

    lateinit var characterImageView: ImageView
    lateinit var characterNameTextView: AppCompatTextView
    lateinit var characterDescriptioneView: AppCompatTextView

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MarvelApplication).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.character.observe(viewLifecycleOwner) { _character ->

            updateUI(_character)
        }
        viewModel.setDataSet(args.characterDetail)
    }

    //region OVERRIDE_METHODS

    override fun bindViews(view: View) {

        characterImageView = view.findViewById(R.id.character_detail_image)
        characterNameTextView = view.findViewById(R.id.character_detail_name)
        characterDescriptioneView = view.findViewById(R.id.character_detail_description)
    }

    //endregion

    //region PRIVATE_METHODS

    /**
     * Update view with the character info
     *
     * @param character
     */
    private fun updateUI(character: Character) {

        characterNameTextView.text = character.name
        characterDescriptioneView.text = character.description
        Picasso.with(context)
            .load(character.obtainImage(Character.Companion.ImageType.PORTRAIT_LARGE))
            .into(characterImageView)
    }

    //endregion
}
