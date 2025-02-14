package com.example.dogimagegenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.dogimagegenerator.navigation.DogAppNaveController
import com.example.dogimagegenerator.ui.theme.DogImageGeneratorTheme
import com.example.dogimagegenerator.viewModel.DogViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            DogImageGeneratorTheme {

                    val navController = rememberNavController()
                    val viewModel: DogViewModel = viewModel(factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                    DogAppNaveController(navController, viewModel)

            }
        }
    }
}
