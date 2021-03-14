package com.pablogarcia.marvel

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupNavigation()
        setSupportActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navController.popBackStack()
            }
        }

        return false
    }

    //region PRIVATE_METHODS

    /**
     * Setup navigation view with bottom navigation view
     */
    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            this.updateToolbarBackButton(destination)
        }
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    /**
     * Update toolbar back button visibility
     */
    private fun updateToolbarBackButton(destination: NavDestination) {
        when (destination.id) {
            R.id.character_list -> toolbar.visibility = View.VISIBLE
            R.id.character_detail -> toolbar.visibility = View.GONE
        }
    }

    //endregion
}
