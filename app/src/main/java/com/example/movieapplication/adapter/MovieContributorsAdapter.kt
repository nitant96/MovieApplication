package com.example.movieapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.castcrewdetail.MovieCastCrewDataModel
import com.example.domain.castcrewdetail.MovieCastCrewDetailData
import com.example.movieapplication.CircleTransform
import com.example.movieapplication.databinding.CastCrewItemLayoutBinding
import com.squareup.picasso.Picasso

class MovieContributorsAdapter() : RecyclerView.Adapter<MovieContributorsAdapter.ViewHolder>()

{
    private var listOfItem = mutableListOf<MovieCastCrewDataModel>()

    class ViewHolder(val binding: CastCrewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieCastCrewDataModel) {
            when(data)
            {
                is MovieCastCrewDetailData.Cast -> {
                    binding.nameText.text = data.name
                    binding.descriptionText.text = data.character
                    val imagePath = "https://image.tmdb.org/t/p/w200${data.imagePath}"
                    Picasso.get().load(imagePath).transform(CircleTransform()).into(binding.image)
                }
                is MovieCastCrewDetailData.Crew -> {
                    binding.nameText.text = data.name
                    binding.descriptionText.text = data.job
                    val imagePath = "https://image.tmdb.org/t/p/w200${data.imagePath}"
                    Picasso.get().load(imagePath).transform(CircleTransform()).into(binding.image)
                }
            }
        }
    }

    internal fun setListOfItems(data: List<MovieCastCrewDataModel>) {
        listOfItem = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CastCrewItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfItem[position])
    }

    override fun getItemCount(): Int {
        return listOfItem.count()
    }
}