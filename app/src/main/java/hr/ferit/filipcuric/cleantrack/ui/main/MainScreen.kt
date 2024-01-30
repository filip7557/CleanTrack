package hr.ferit.filipcuric.cleantrack.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hr.ferit.filipcuric.cleantrack.R
import hr.ferit.filipcuric.cleantrack.navigation.HOME_ROUTE
import hr.ferit.filipcuric.cleantrack.navigation.LOGIN_ROUTE
import hr.ferit.filipcuric.cleantrack.navigation.NavigationItem
import hr.ferit.filipcuric.cleantrack.navigation.REGISTER_ROUTE
import hr.ferit.filipcuric.cleantrack.ui.home.HomeScreen
import hr.ferit.filipcuric.cleantrack.ui.home.HomeViewModel
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
    val homeViewModel = koinViewModel<HomeViewModel>()
    val mainViewModel = koinViewModel<MainViewModel>()

    mainViewModel.isUserLoggedIn()

    val showBackIcon by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route != HOME_ROUTE && navBackStackEntry?.destination?.route != LOGIN_ROUTE
        }
    }

    val showSignOutButton by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == HOME_ROUTE
        }
    }


    Scaffold(
        topBar = {
            TopBar(
                backNavigationIcon = { if(showBackIcon) BackIcon(onBackClick = { navController.popBackStack() }) },
                secondNavigationIcon = { if(showSignOutButton) SignOutIcon(onBackClick = {
                    mainViewModel.signUserOut()
                    navController.navigate(LOGIN_ROUTE) {
                        popUpTo(HOME_ROUTE) {
                            inclusive = true
                        }
                    }
                }) }
            )
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
                        homeViewModel = homeViewModel,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    backNavigationIcon: @Composable (() -> Unit)? = null,
    secondNavigationIcon: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                if(backNavigationIcon != null)
                    backNavigationIcon()
                if(secondNavigationIcon != null)
                    secondNavigationIcon()
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Green,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

@Composable
private fun BackIcon(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onBackClick),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun SignOutIcon(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logout_svgrepo_com),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onBackClick),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}
