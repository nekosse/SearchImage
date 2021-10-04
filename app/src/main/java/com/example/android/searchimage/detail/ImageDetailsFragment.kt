package com.example.android.searchimage.detail

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.searchimage.R
import com.example.android.searchimage.databinding.FragmentImageViewDetailsBinding
import com.example.android.searchimage.network.ImageProperty
import com.synnapps.carouselview.ImageListener


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

    lateinit var imageProperty : Array<ImageProperty>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentImageViewDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        imageProperty = ImageDetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty

        val viewModelFactory = ImageDetailsFactory(imageProperty, application)

        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(ImageDetailsViewModel::class.java)

        binding.carouselView.setPageCount(imageProperty.size)
        binding.carouselView.setImageListener(imageListener);


        return binding.root
    }


    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            // You can use Glide or Picasso here
            imageProperty[position].largeImageURL?.let {
                val imgUri = imageProperty[position].largeImageURL.toUri().buildUpon().scheme("https").build()
                Glide.with(imageView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(imageView)
            }
        }
    }
}
