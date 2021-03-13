package com.pablogarcia.marvel.ui.characters.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pablogarcia.marvel.R
import com.pablogarcia.marvel.di.MarvelApplication
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.ui.base.BaseFragment
import com.pablogarcia.marvel.ui.base.UiState
import com.pablogarcia.marvel.ui.custom.LoadDataRecyclerView
import com.pablogarcia.marvel.ui.custom.LoadingView
import javax.inject.Inject

class CharacterListFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: CharacterListViewModel

    private lateinit var characterRecyclerView: LoadDataRecyclerView
    private lateinit var loadingView: LoadingView

    private lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MarvelApplication).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        this.setupAdapter()
        this.observeData()
        viewModel.onViewCreated()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.menu_all_characters -> adapter.clearFilter()
            R.id.menu_favorite_characters -> adapter.filterFavorites()
        }

        return super.onOptionsItemSelected(item)
    }

    //region OVERRIDE_METHODS

    override fun bindViews(view: View) {

        characterRecyclerView = view.findViewById(R.id.character_list_recycler)
        loadingView = view.findViewById(R.id.loadingView)
    }

    //endregion

    //region PRIVATE_METHODS

    /**
     * Add observers to live data
     */
    private fun observeData() {

        viewModel.characters.observe(viewLifecycleOwner) { _characters ->
            adapter.setData(_characters)
        }

        viewModel.uiState.observe(viewLifecycleOwner) { isLoading ->
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
     * Setup recycler view adapter
     */
    private fun setupAdapter() {

        adapter = CharactersAdapter(viewModel, ::navigateDetail)
        characterRecyclerView.adapter = adapter
        characterRecyclerView.callback = {
            if(!adapter.isFiltered)
                viewModel.loadCharacters(fromLocal = false, showLoading = false)
        }
    }

    /**
     * Navigate to character detail
     *
     * @character
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
