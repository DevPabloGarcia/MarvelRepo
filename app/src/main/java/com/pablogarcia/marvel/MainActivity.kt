package com.pablogarcia.marvel

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            android.R.id.home -> {

                navController.popBackStack()
            }
        }

        return true
    }

    //region PRIVATE_METHODS

    /**
     * Setup navigation view with bottom navigation view
     */
    private fun setupNavigation() {

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->

            this.updateToolbarBackButton(destination)
        }
    }

    /**
     * Update toolbar back button visibility
     */
    private fun updateToolbarBackButton(destination: NavDestination) {

        when (destination.id) {

            R.id.character_list -> supportActionBar?.setDisplayHomeAsUpEnabled(false)
            R.id.character_detail -> supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    //endregion
}
