package com.example.challengepokeapi.ui.home.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.challengepokeapi.R
import com.example.challengepokeapi.databinding.FragmentHomeItemPokemonBinding
import com.example.challengepokeapi.domain.model.PokemonModel

class HomeAllPokeAdapter (
    private val dataSource: MutableList<PokemonModel>,
    private var onClickListener: ((pokeModel: PokemonModel) -> Unit),
    val context: Context
) : RecyclerView.Adapter<HomeAllPokeAdapter.ViewHolder>() {

    inner class ViewHolder(
        private var binding: FragmentHomeItemPokemonBinding,
        private var ctx: Context,
        private var onListHitItemClickListener: ((pokeModel: PokemonModel) -> Unit)
    ) : RecyclerView.ViewHolder(binding!!.root)
    {
        var root: ConstraintLayout = binding.layoutItemPokemon

        fun bind(dataSource: PokemonModel){
            binding?.tvNamePoke?.text = dataSource.name
            Glide.with(context)
                .load(dataSource.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding!!.imagePoke)

            binding.layoutItemPokemon.setOnClickListener {
                binding.layoutItemPokemon.setBackgroundColor(ctx.getColor(R.color.white))

                onListHitItemClickListener.invoke(dataSource)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentHomeItemPokemonBinding.inflate(
            LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(binding, viewGroup.context, onClickListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val pokeModel: PokemonModel = dataSource[position]
        viewHolder.bind(pokeModel)
    }

    override fun getItemCount() = dataSource.size
}