package com.pablogarcia.marvel.ui.characters.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pablogarcia.marvel.R
import com.pablogarcia.marvel.di.MarvelApplication
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Thumbnail
import com.pablogarcia.marvel.ui.base.BaseFragment
import com.pablogarcia.marvel.ui.base.UiState
import com.pablogarcia.marvel.ui.characters.list.CharactersAdapter
import com.pablogarcia.marvel.ui.custom.LoadingView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_character_detail.*
import javax.inject.Inject

class CharacterDetailFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: CharacterDetailViewModel

    private lateinit var toolbar: Toolbar
    private lateinit var characterImageView: ImageView
    private lateinit var characterNameTextView: AppCompatTextView
    private lateinit var characterDescriptionView: AppCompatTextView
    private lateinit var comicList: RecyclerView
    private lateinit var loadingView: LoadingView

    private lateinit var adapter: ComicsAdapter
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
        this.setupRecyclerView()
        this.observeData()
        viewModel.setDataSet(args.characterDetail)
    }

    //region OVERRIDE_METHODS

    override fun bindViews(view: View) {

        toolbar = view.findViewById(R.id.character_detail_toolbar)
        characterImageView = view.findViewById(R.id.character_detail_image)
        characterNameTextView = view.findViewById(R.id.character_detail_name)
        characterDescriptionView = view.findViewById(R.id.character_detail_description)
        comicList = view.findViewById(R.id.character_detail_comics)
        loadingView = view.findViewById(R.id.loadingView)

        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

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
        characterDescriptionView.text = character.description
        Picasso.with(context)
            .load(character.thumbnail?.obtainImage(Thumbnail.Companion.ImageType.LANDSCAPE_LARGE))
            .into(characterImageView)
    }

    private fun setupRecyclerView() {
        adapter = ComicsAdapter()
        comicList.layoutManager = LinearLayoutManager(
            view?.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        comicList.adapter = adapter
    }

    private fun observeData() {

        viewModel.character.observe(viewLifecycleOwner) { character ->
            updateUI(character)
        }

        viewModel.comics.observe(viewLifecycleOwner) { comics ->
            adapter.setData(comics)
        }

        viewModel.uiState.observe(viewLifecycleOwner) { isLoading ->
            when (isLoading) {
                UiState.SUCCESS -> loadingView.hide()
                UiState.LOADING -> loadingView.show()
                UiState.ERROR,
                null -> {
                    loadingView.hide()
                    showError()
                }
            }
        }
    }

    private fun showError() {

        Toast.makeText(context, resources.getText(R.string.loading_error), Toast.LENGTH_LONG).show()
    }

    //endregion
}
