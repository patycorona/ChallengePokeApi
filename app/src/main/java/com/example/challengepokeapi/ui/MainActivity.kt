package com.example.challengepokeapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.challengepokeapi.R
import com.example.challengepokeapi.databinding.ActivityMainBinding
import com.example.challengepokeapi.ui.component.Screen
import com.example.challengepokeapi.ui.login.views.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeScreen(Screen.LoginFragment)
    }

    private fun changeFragment(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.Fragment_principal, fragment)
        ft.commit()
    }

    fun changeScreen(typeScreen: Screen) {

        binding.apply {

            when (typeScreen) {
                Screen.LoginFragment -> {
                    lblTitle.visibility = View.GONE
                    openLoginFragment()
                }
                Screen.MainActivity -> {
                    //initListener()
                }

                else -> {
                    Screen.LoginFragment
                }
            }
        }
    }

    private fun openLoginFragment() {
        changeFragment(LoginFragment.newInstance())
    }

}