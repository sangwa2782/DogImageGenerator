package com.example.dogimagegenerator.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogimageapp.model.RemoteClient
import com.example.dogimagegenerator.model.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DogViewModel(application: Application) : AndroidViewModel(application) {

    val apiService = RemoteClient.apiService
    private val repository = DogRepository(apiService, application)

    private val _dogImages = MutableStateFlow(repository.getCachedImages())
    val dogImages = _dogImages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun generateDogImage() {
        viewModelScope.launch {

//            _isLoading.value = true
//            val newImage = repository.getRandomDogImage()
//            _dogImages.value = repository.getCachedImages()
//            _isLoading.value = false

            _isLoading.value = true // 🔹 Show loader

            try {
                val newImage = repository.getRandomDogImage()
                _dogImages.value = repository.getCachedImages()
            } catch (e: HttpException) {
                Log.e("DogViewModel", "API Error: ${e.message}", e)
            } catch (e: Exception) {
                Log.e("DogViewModel", "Unknown Error: ${e.message}", e)
            } finally {
                _isLoading.value = false // 🔹 Hide loader after completion
            }
        }
    }

    fun clearCache() {
        repository.clearCache()
        _dogImages.value = emptyList()
    }
}
