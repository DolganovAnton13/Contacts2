package com.antondolganov.contacts2.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.antondolganov.contacts2.R


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.antondolganov.contacts2.R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    fun setToolbarWithButtonHome(toolbar: Toolbar?, title: String) {
        if (toolbar != null) {
            toolbar!!.setTitle(title)
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navController.popBackStack()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
