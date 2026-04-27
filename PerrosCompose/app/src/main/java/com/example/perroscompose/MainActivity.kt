package com.example.perroscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.perroscompose.pantallas.navigation.NavGraph
import com.example.perroscompose.ui.theme.PerrosComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PerrosComposeTheme {
                NavGraph()
            }
        }
    }
}