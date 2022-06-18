package com.zaki.sosmedapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaki.sosmedapp.R
import com.zaki.sosmedapp.databinding.FragmentUserBinding
import com.zaki.sosmedapp.helper.OnItemClick
import com.zaki.sosmedapp.network.model.Photo
import com.zaki.sosmedapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UserFragmentArgs>()

    private val viewModel by viewModels<UserViewModel>()

    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initArgumentData()
        observeViewModel()
    }

    private fun initArgumentData() {
        val user = args.user

        binding.tvUserName.text = user?.username
        binding.tvEmail.text = user?.email
        binding.tvAddress.text = user?.address?.getAddress()
        binding.tvCompany.text = user?.company?.name

        user?.id?.let { viewModel.getAlbums(it) }
    }

    private fun initAdapter() {
        albumAdapter = AlbumAdapter()
        binding.rvAlbums.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        albumAdapter.onItemClick = object : OnItemClick<Photo> {
            override fun onItemClick(data: Photo) {
                findNavController().navigate(R.id.action_userFragment_to_photoFragment)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.album.observe(viewLifecycleOwner) { albums ->
            albums?.let {
                albumAdapter.setAlbums(it)
            }
        }
    }
}