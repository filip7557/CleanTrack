package hr.ferit.filipcuric.cleantrack.ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hr.ferit.filipcuric.cleantrack.R
import hr.ferit.filipcuric.cleantrack.mock.getCompanies
import hr.ferit.filipcuric.cleantrack.navigation.HOME_ROUTE
import hr.ferit.filipcuric.cleantrack.navigation.NavigationItem
import hr.ferit.filipcuric.cleantrack.navigation.REGISTER_ROUTE
import hr.ferit.filipcuric.cleantrack.ui.home.HomeScreen
import hr.ferit.filipcuric.cleantrack.ui.home.HomeScreenViewState
import hr.ferit.filipcuric.cleantrack.ui.login.LoginScreen
import hr.ferit.filipcuric.cleantrack.ui.login.LoginViewModel
import hr.ferit.filipcuric.cleantrack.ui.register.RegisterRoute
import hr.ferit.filipcuric.cleantrack.ui.register.RegisterViewModel
import hr.ferit.filipcuric.cleantrack.ui.theme.Green
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.ParametersHolder

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val loginViewModel = koinViewModel<LoginViewModel> { ParametersHolder(List<NavController>(1){navController}.toMutableList()) }
    val registerViewModel = koinViewModel<RegisterViewModel>()
    val mainViewModel = koinViewModel<MainViewModel>()

    mainViewModel.isUserLoggedIn()


    Scaffold(
        topBar = {
            TopBar()
        }
    ) { padding ->
        Surface(
            color = if(isSystemInDarkTheme()) Color.Black else Color(0xFFF3F0F0),
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = if(mainViewModel.isLoggedIn) NavigationItem.HomeDestination.route else NavigationItem.LoginDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationItem.LoginDestination.route) {
                    LoginScreen(
                        viewModel = loginViewModel,
                        onRegisterClick = {
                            navController.navigate(REGISTER_ROUTE)
                        },
                    )
                }
                composable(NavigationItem.RegisterDestination.route) {
                    RegisterRoute(
                        viewModel = registerViewModel,
                        onRegisterClick = {
                            navController.navigate(HOME_ROUTE)
                        }
                    )
                }
                composable(NavigationItem.HomeDestination.route) {
                    HomeScreen(
                        homeScreenViewState = HomeScreenViewState(
                            companies = getCompanies()
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Green,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}
