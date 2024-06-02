package com.example.challengepokeapi.ui.home.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.challengepokeapi.databinding.FragmentItemPokeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemPokeFragment : Fragment() {

    private var binding :FragmentItemPokeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentItemPokeBinding.inflate(LayoutInflater.from(context), null, false)
        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ItemPokeFragment().apply {
            }
    }
}