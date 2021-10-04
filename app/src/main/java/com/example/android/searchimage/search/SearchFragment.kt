package com.example.android.searchimage.search

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        if(viewModel.response.value?.isNotEmpty() == true) {
            binding.nextButton.visibility = View.VISIBLE
        }


        binding.searchButton.setOnClickListener {
                binding.imageView.visibility = View.VISIBLE
                viewModel.displaySearchResponse(binding.editTextTextPersonName.text.toString())
                binding.nextButton.visibility = View.VISIBLE

                binding.invalidateAll()
        }

        binding.nextButton.setOnClickListener{

            if(listImageChecked.size<2){
                Toast.makeText(context,"Vous devez selectionner au moins 2 images",Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.displayImageDetails(listImageChecked)
            }
        }


        viewModel.navigateToSelectedImage.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToImageViewDetails(
                    it.toTypedArray()
                ))
                viewModel.displayImageDetailsComplete()
            }
        })

        //Disapear the loading animation when data are loading
        viewModel.eventDataLoadFinish.observe(viewLifecycleOwner, Observer { loadFinish ->
            if (loadFinish) {
                binding.imageView.visibility = View.GONE
                viewModel.loadFinishComplete()
            }
        })

        return binding.root
    }


}