package com.worldcountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.worldcountries.databinding.ActivityMainBinding
import com.worldcountries.ui.favorites.FavoritesFragment
import com.worldcountries.ui.home.HomeFragment
import com.worldcountries.ui.search.SearchFragment
import com.worldcountries.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCurrentFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> { setCurrentFragment(HomeFragment()) }
                R.id.search -> { setCurrentFragment(SearchFragment()) }
                R.id.favorites -> { setCurrentFragment(FavoritesFragment()) }
                R.id.settings -> { setCurrentFragment(SettingsFragment()) }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}