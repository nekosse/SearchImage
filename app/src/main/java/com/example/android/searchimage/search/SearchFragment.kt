package com.example.android.searchimage.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.android.searchimage.databinding.FragmentSearchBinding
import com.example.android.searchimage.network.ImageProperty


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {


    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSearchBinding.inflate(inflater)
        val listImageChecked = arrayListOf<ImageProperty>()
        val listImageAdpaters = ImageListAdapter(listImageChecked)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosList.adapter = listImageAdpaters

        binding.searchButton.setOnClickListener {
                viewModel.displaySearchResponse(binding.editTextTextPersonName.text.toString())
                binding.nextButton.visibility = View.VISIBLE
                binding.invalidateAll()
        }

        binding.nextButton.setOnClickListener{
            Toast.makeText(context,"Nb of image select : ${listImageChecked.size}",Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }


}