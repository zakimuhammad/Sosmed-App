package com.zaki.sosmedapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.zaki.sosmedapp.R
import com.zaki.sosmedapp.databinding.FragmentPhotoBinding

class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    private val navArgs by navArgs<PhotoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photo = navArgs.photo
        binding.tvPhotoTitle.text = photo?.title

        val url = GlideUrl(photo?.url, LazyHeaders.Builder()
            .addHeader("User-Agent", "your-user-agent")
            .build())

        Glide.with(requireContext())
            .load(url)
            .placeholder(R.drawable.ic_baseline_broken_image_24)
            .into(binding.ivPhoto)
    }
}