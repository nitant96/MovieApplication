package com.example.movieapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.data.MovieApiService
import com.example.movieapplication.R
import com.example.movieapplication.databinding.MovieInfoLayoutBinding
import com.example.movieapplication.viewModel.MovieInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MovieInfoFragment  : Fragment() {
    private var _binding: MovieInfoLayoutBinding? = null
    private val binding get() = _binding!!
    val viewModel : MovieInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieInfoLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movieDetailData.onEach {
         println("Nitant -------> inside on Each")
        }

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}