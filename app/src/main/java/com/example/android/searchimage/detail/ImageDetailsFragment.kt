package com.example.android.searchimage.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.android.searchimage.databinding.FragmentImageViewDetailsBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageDetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentImageViewDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val imageProperty = ImageDetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = ImageDetailsFactory(imageProperty, application)

        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(ImageDetailsViewModel::class.java)



        return binding.root
    }
}
