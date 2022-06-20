package com.zaki.sosmedapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaki.sosmedapp.network.model.Post
import com.zaki.sosmedapp.network.model.User
import com.zaki.sosmedapp.network.repository.SosmedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: SosmedRepository
): ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch(Dispatchers.IO) {
        val userResponse = repository.getUsers()

        getPosts(userResponse)
    }

    private suspend fun getPosts(user: List<User>) {
        val response = repository.getPosts()

        response.forEach { post ->
            val userPost = user.find { it.id == post.userId }
            if (userPost != null) {
                post.user = userPost
            }
        }

        _posts.postValue(response)
    }
}