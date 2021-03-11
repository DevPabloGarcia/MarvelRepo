package com.pablogarcia.marvel.ui.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pablogarcia.marvel.R
import com.pablogarcia.marvel.di.MarvelApplication
import com.pablogarcia.marvel.ui.base.BaseFragment
import com.pablogarcia.marvel.ui.custom.LoadingView
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.ui.base.UiState
import javax.inject.Inject

class CharacterListFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: CharacterListViewModel

    private lateinit var characterRecyclerView: RecyclerView
    private lateinit var characterSwipe: SwipeRefreshLayout
    private lateinit var loadingView: LoadingView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MarvelApplication).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        this.setupAdapter()
        this.setupSwipeLayout()
        viewModel.onViewCreated()
    }

    //region OVERRIDE_METHODS

    override fun bindViews(view: View) {

        characterRecyclerView = view.findViewById(R.id.character_list_recycler)
        characterSwipe = view.findViewById(R.id.character_list_swipe)
        loadingView = view.findViewById(R.id.loadingView)
    }

    //endregion

    //region PRIVATE_METHODS

    /**
     * Setup recycler view adapter
     */
    private fun setupAdapter() {

        val adapter = CharactersAdapter(::navigateDetail)
        characterRecyclerView.adapter = adapter
        characterRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.characters.observe(viewLifecycleOwner) { _characters ->

            adapter.setData(_characters)
            characterRecyclerView.scheduleLayoutAnimation()
        }

        viewModel.uiState.observe(viewLifecycleOwner) { isLoading ->

            characterSwipe.isRefreshing = false
            when (isLoading) {

                UiState.SUCCESS -> loadingView.hide()
                UiState.LOADING -> loadingView.show()
                UiState.ERROR -> {

                    loadingView.hide()
                    showError()
                }
            }
        }
    }

    /**
     * Setup swipe layout
     */
    private fun setupSwipeLayout() {

        characterSwipe.setOnRefreshListener {

            viewModel.loadCharacters()
        }
    }

    /**
     * Navigate to character detail
     *
     * @cha
     */
    private fun navigateDetail(character: Character) {

        val action = CharacterListFragmentDirections.characterDetailAction(character)
        findNavController().navigate(action)
    }

    private fun showError() {

        Toast.makeText(context, resources.getText(R.string.loading_error), Toast.LENGTH_LONG).show()
    }
    //endregion
}
