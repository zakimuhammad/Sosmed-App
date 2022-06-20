package com.zaki.sosmedapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaki.sosmedapp.databinding.FragmentDetailPostBinding
import com.zaki.sosmedapp.viewmodel.DetailPostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostFragment : Fragment() {

    private var _binding: FragmentDetailPostBinding? = null
    private val binding: FragmentDetailPostBinding get() = _binding!!

    private val viewModel by viewModels<DetailPostViewModel>()
    private lateinit var commentAdapter: CommentAdapter

    private val args by navArgs<DetailPostFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initArguments()
        initVMObserver()
        initClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun initArguments() {
        val post = args.post
        if (post != null) {
            binding.tvTitle.text = post.title
            binding.tvUserName.text = "by ${post.user.username}"
            binding.tvDescription.text = post.body

            viewModel.getComments(post.id.toString())
        }
    }

    private fun initAdapter() {
        commentAdapter = CommentAdapter()
        binding.rvComments.apply {
            adapter = commentAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun initVMObserver() {
        viewModel.comment.observe(viewLifecycleOwner) {
            commentAdapter.setComments(it)
        }
    }

    private fun initClickListener() {
        binding.tvUserName.setOnClickListener {
            val action = DetailPostFragmentDirections.actionDetailPostFragmentToUserFragment(args.post?.user)
            findNavController().navigate(action)
        }
    }
}