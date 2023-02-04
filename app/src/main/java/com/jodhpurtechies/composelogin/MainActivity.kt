package com.jodhpurtechies.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jodhpurtechies.composelogin.ui.screens.NavigationRoutes
import com.jodhpurtechies.composelogin.ui.screens.authenticatedGraph
import com.jodhpurtechies.composelogin.ui.screens.unauthenticatedGraph
import com.jodhpurtechies.composelogin.ui.theme.ComposeLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MainAppNavHost()
    }

}

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoutes.Unauthenticated.NavigationRoute.route
    ) {
        // Unauthenticated user flow screens
        unauthenticatedGraph(navController = navController)

        // Authenticated user flow screens
        authenticatedGraph(navController = navController)
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLoginTheme {
        MainApp()
    }
}