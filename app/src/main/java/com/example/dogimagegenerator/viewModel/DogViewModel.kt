package com.example.dogimagegenerator.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogimageapp.model.RemoteClient
import com.example.dogimagegenerator.model.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogViewModel(application: Application) : AndroidViewModel(application) {

    val apiService = RemoteClient.apiService
    private val repository = DogRepository(apiService, application)

    private val _dogImages = MutableStateFlow(repository.getCachedImages())
    val dogImages = _dogImages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun generateDogImage() {
        viewModelScope.launch {

            _isLoading.value = true
            val newImage = repository.getRandomDogImage()
            _dogImages.value = repository.getCachedImages()
            _isLoading.value = false
        }
    }

    fun clearCache() {
        repository.clearCache()
        _dogImages.value = emptyList()
    }
}
