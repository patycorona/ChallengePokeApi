package com.example.challengepokeapi.ui.detailhome.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.challengepokeapi.R
import com.example.challengepokeapi.databinding.DetailPokemonBottomSheetBinding
import com.example.challengepokeapi.domain.model.ConstantGeneral
import com.example.challengepokeapi.domain.model.DetailPokemonModel
import com.example.challengepokeapi.ui.component.setLayoutHeight
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonBottomSheet(
    private var detailPokemonModel:DetailPokemonModel
): BottomSheetDialogFragment(){

    private var binding: DetailPokemonBottomSheetBinding?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = true
        setupHeight()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        binding = DetailPokemonBottomSheetBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        configBottomSheet()
        buildScreen()
        return binding?.root
    }

    private fun buildScreen() {

        binding?.apply {
            var characteristic = ""
            tvName.text = detailPokemonModel.name
            tvHeight.text = detailPokemonModel.height.toString()
            tvWeight.text = detailPokemonModel.weight.toString()

            detailPokemonModel.itemAbility.forEach{ item ->
                characteristic = item.ability.name
            }
            tvCharacteristic.text = characteristic
            Glide.with(this@DetailPokemonBottomSheet)
                .load(detailPokemonModel.urlImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding!!.imgPokemon)
        }
    }

    private fun configBottomSheet() {
        binding?.root?.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.bg_bottom_sheet
        )
    }

     private fun setupHeight() {
        val displayMetrics = activity?.resources?.displayMetrics
        val height = displayMetrics?.heightPixels
            ?: resources.getDimension(R.dimen.modal_height)
                .toInt()
        val maxHeight = height * ConstantGeneral.HEIGHT_80

        binding?.root?.viewTreeObserver?.addOnGlobalLayoutListener {
            binding?.containerDetailScreen?.setLayoutHeight(maxHeight.toFloat())
        }

        binding?.root?.post {
            val params =
                (binding?.root?.parent as View).layoutParams as CoordinatorLayout.LayoutParams

            params.behavior?.let { behavior ->
                if (behavior is BottomSheetBehavior<*>) {
                    behavior.peekHeight = maxHeight.toInt()
                    behavior.skipCollapsed = true
                }
            }
        }
    }

    override fun getTheme(): Int = R.style.CustomSheetDialog

    companion object {

        @JvmStatic
        fun newInstance(
            detailPokemonModel: DetailPokemonModel
        ): DetailPokemonBottomSheet {
            return DetailPokemonBottomSheet(detailPokemonModel )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}