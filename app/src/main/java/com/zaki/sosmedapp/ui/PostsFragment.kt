package com.zaki.sosmedapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaki.sosmedapp.databinding.FragmentPostsBinding
import com.zaki.sosmedapp.helper.OnItemClick
import com.zaki.sosmedapp.network.model.Post
import com.zaki.sosmedapp.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private lateinit var postAdapter: PostAdapter

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initVMObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        postAdapter = PostAdapter()
        binding.rvPosts.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
        postAdapter.onItemClick = object : OnItemClick<Post> {
            override fun onItemClick(data: Post) {
                val action = PostsFragmentDirections.actionPostsFragmentToDetailPostFragment(data)
                findNavController().navigate(action)
            }
        }
    }

    private fun initVMObserver() {
        viewModel.posts.observe(viewLifecycleOwner) {
            postAdapter.setPosts(it)
        }
    }
}