package com.example.challengepokeapi.ui.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.challengepokeapi.databinding.FragmentHomeBinding
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.CODE
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.ERROR
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.PNG
import com.example.challengepokeapi.domain.model.DetailPokemonModel
import com.example.challengepokeapi.domain.model.DetailPokemonResult
import com.example.challengepokeapi.domain.model.PokemonModel
import com.example.challengepokeapi.domain.model.PokemonResult
import com.example.challengepokeapi.ui.MainActivity
import com.example.challengepokeapi.ui.component.Screen
import com.example.challengepokeapi.ui.detailhome.viewmodel.DetailPokemonViewModel
import com.example.challengepokeapi.ui.detailhome.views.DetailPokemonBottomSheet
import com.example.challengepokeapi.ui.home.viewmodel.HomeAllPokemonViewModel
import com.example.challengepokeapi.ui.home.views.adapter.HomeAllPokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val homeAllPokemonViewModel: HomeAllPokemonViewModel by viewModels()
    private val detailPokemonViewModel: DetailPokemonViewModel by viewModels()
    private var pokemonModel = PokemonModel()
    private var detailPokemonModel = DetailPokemonModel()
    private var itemsShow = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            (activity as MainActivity)
                .changeScreen(Screen.HomeFragment)
        }
    }

    private val listPokeObserver = Observer<PokemonResult> { pokeResult ->
        if (pokeResult.success) {
            pokeResult.pokemon?.let {
                val adapter = HomeAllPokemonAdapter(
                    it,
                    onItemClickListener,
                    requireContext()
                )
                binding?.recyclerview?.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }

    private val detailPokemonObserver = Observer<DetailPokemonResult> { result ->
        if (result.success) {

            detailPokemonModel =
                DetailPokemonModel(
                    result.idPokemon,
                    result.name,
                    result.urlImage  + result.idPokemon + PNG,
                    result.height,
                    result.weight,
                    result.itemAbility
                )
            DetailPokemonBottomSheet.newInstance(detailPokemonModel)
                .show(requireActivity().supportFragmentManager, "")
        }else
            Toast.makeText(
                requireContext(), ERROR ,
                Toast.LENGTH_SHORT
            ).show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context),null,false)

        initRecycler()
        initObserver()
        initButtons()
        homeAllPokemonViewModel.getAllPokemon(itemsShow)
        return binding?.root
    }

    private fun initButtons() {
        binding?.apply {
            tvNext.setOnClickListener {
                itemsShow += 1
                if (itemsShow == 0) {
                    tvPrevius.visibility = View.GONE
                } else {
                    tvPrevius.visibility = View.VISIBLE
                }
                homeAllPokemonViewModel.getAllPokemon(itemsShow)
            }
            tvPrevius.setOnClickListener {
                itemsShow -= 1
                if (itemsShow <= 0) {
                    itemsShow = 0
                    tvPrevius.visibility = View.GONE
                }
                homeAllPokemonViewModel.getAllPokemon(itemsShow)
            }
        }
    }

    private var onItemClickListener: ((pokeModel: PokemonModel) -> Unit) = { PK ->

        if (PK.idPokemon != CODE){
             detailCharacterist(PK.name)
            var imageUrl = PK.urlImage + PK.idPokemon + PNG
            pokemonModel = PokemonModel(PK.idPokemon,PK.name,PK.url,imageUrl)

        }
    }

    private fun detailCharacterist(name:String){
        detailPokemonViewModel.getDetailPokemon(name)
    }
    private fun initObserver() {
        homeAllPokemonViewModel.listPokeR.observe(viewLifecycleOwner, listPokeObserver)
        detailPokemonViewModel.detPokemonResult.observe(viewLifecycleOwner, detailPokemonObserver )
    }

    private fun initRecycler() {
        val linearLayoutManager =  GridLayoutManager(requireContext(), 2)
        binding?.recyclerview?.apply {
            layoutManager = linearLayoutManager
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}