package com.zaki.sosmedapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaki.sosmedapp.network.model.Comment
import com.zaki.sosmedapp.network.repository.SosmedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val repository: SosmedRepository
): ViewModel() {

    private val _comments = MutableLiveData<List<Comment>>()
    val comment: LiveData<List<Comment>> get() = _comments

    fun getComments(postId: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.getComments(postId)

        _comments.postValue(response)
    }
}