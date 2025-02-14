package com.example.dogimagegenerator.model.repository

import android.content.Context
import android.util.LruCache
import com.example.dogimagegenerator.model.api.ApiService
import com.example.dogimagegenerator.model.dataClass.DogResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository(private val apiService: ApiService, context: Context) {

    private val cacheSize = 20
    private val lruCache = LruCache<String, String>(cacheSize)
    private val sharedPrefs = context.getSharedPreferences("dog_prefs", Context.MODE_PRIVATE)

    init {
        loadCache()
    }

    suspend fun getRandomDogImage(): String {
        return withContext(Dispatchers.IO) {
            val response: DogResponse = apiService.getRandomDogImage()
            response.message.also {
                saveToCache(it)
            }
        }
    }

    private fun saveToCache(imageUrl: String) {
        lruCache.put(imageUrl, imageUrl)
        saveCache()
    }

    fun getCachedImages(): List<String> {
        return lruCache.snapshot().keys.toList()
    }

    private fun saveCache() {
        sharedPrefs.edit().putStringSet("cached_dogs", getCachedImages().toSet()).apply()
    }

    private fun loadCache() {
        sharedPrefs.getStringSet("cached_dogs", emptySet())?.forEach {
            lruCache.put(it, it)
        }
    }

    fun clearCache() {
        lruCache.evictAll()
        saveCache()
    }
}
