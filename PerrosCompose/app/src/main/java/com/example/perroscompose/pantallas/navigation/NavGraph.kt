package com.example.perroscompose.pantallas.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.perroscompose.pantallas.screens.FotosScreen
import com.example.perroscompose.pantallas.screens.ListaRazasScreen
import com.example.perroscompose.viewmodel.DogViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    //viewmodel para compartir datos entre pantallas
    val viewModel: DogViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.LISTA_RAZAS
    ) {
        composable(Routes.LISTA_RAZAS) {
            ListaRazasScreen(
                viewModel = viewModel,
                onRazaClick = { raza ->
                    navController.navigate("${Routes.FOTOS_RAZA}/$raza")
                }
            )
        }

        composable(
            route = "${Routes.FOTOS_RAZA}/{raza}",
            arguments = listOf(
                navArgument("raza") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            // recogemos la raza que hemos pasado por navegación
            val raza = backStackEntry.arguments?.getString("raza") ?: ""

            FotosScreen(
                viewModel = viewModel,
                raza = raza
            )
        }
    }
}
