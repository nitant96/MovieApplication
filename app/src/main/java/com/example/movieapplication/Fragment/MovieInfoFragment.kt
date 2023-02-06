package com.example.movieapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.uservitals.coreui.observeIn
import com.example.domain.moviedetails.MovieDetailData
import com.example.movieapplication.adapter.MovieContributorsAdapter
import com.example.movieapplication.databinding.MovieInfoLayoutBinding
import com.example.movieapplication.viewModel.MovieInfoViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MovieInfoFragment : Fragment() {
    private var _binding: MovieInfoLayoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieInfoViewModel by viewModels()
    private var castAdapter: MovieContributorsAdapter? = null
    private var crewAdapter: MovieContributorsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieInfoLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetails()
        viewModel.getMovieCastCrewDetails()
        setupObservers()

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

    }

    private fun setupObservers(){
        //Movie data Shared flow Observer
        viewModel.movieDetailData.onEach {
            setupMovieBasicDetails(it)
        }.observeIn(this)

        //Movie Cast/Crew Shared Flow Observer
        viewModel.movieCastCrewDetailData.onEach {
            viewModel.movieCastList?.let {
                setupCastAdapter()
            }
            viewModel.movieCrewList?.let {
                setupCrewAdapter()
            }
        }.observeIn(this)
    }

    private fun setupMovieBasicDetails(data:MovieDetailData){
        val imagePath = "https://image.tmdb.org/t/p/w500${data.backdropPath}"
        Picasso.get().load(imagePath).into(_binding?.posterImage)

        _binding?.movieName?.text = data.title
        _binding?.taglineText?.text = data.tagline
    }

    //Setting Up the Cast Adapter post Data received
    private fun setupCastAdapter(){
        viewModel.movieCastList?.let {
            println("Nitant inside cast list befor adapter")
            castAdapter = MovieContributorsAdapter()
            castAdapter?.setListOfItems(it)
            binding.castRecyclerView.adapter = castAdapter
            binding.castRecyclerView.setHasFixedSize(true)
            binding.castRecyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        }
    }

    //Setting up the Crew Adapter post Data received
    private fun setupCrewAdapter(){
        viewModel.movieCrewList?.let {
            println("Nitant inside crew list befor adapter")
            crewAdapter = MovieContributorsAdapter()
            crewAdapter?.setListOfItems(it)
            binding.crewRecyclerView.adapter = crewAdapter
            binding.crewRecyclerView.setHasFixedSize(true)
            binding.crewRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}