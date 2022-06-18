package com.zaki.sosmedapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaki.sosmedapp.network.model.Album
import com.zaki.sosmedapp.network.model.Photo
import com.zaki.sosmedapp.network.repository.SosmedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: SosmedRepository
): ViewModel() {

    private val _albums = MutableLiveData<List<Album>?>()
    val album: LiveData<List<Album>?> get() = _albums

    fun getAlbums(userId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.getAlbums(userId)

        response.forEach {
            it.photos = getPhotos(it.id)
        }

        _albums.postValue(response)
    }

    private suspend fun getPhotos(albumId: Int): List<Photo> {
        return repository.getPhotos(albumId)
    }
}