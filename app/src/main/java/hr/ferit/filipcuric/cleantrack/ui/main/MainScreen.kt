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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.filipcuric.cleantrack.R
import hr.ferit.filipcuric.cleantrack.navigation.ADD_WORKER_COMPANY_KEY_ID
import hr.ferit.filipcuric.cleantrack.navigation.AddWorkerDestination
import hr.ferit.filipcuric.cleantrack.navigation.COMPANY_KEY_ID
import hr.ferit.filipcuric.cleantrack.navigation.CompanyInfoDestination
import hr.ferit.filipcuric.cleantrack.navigation.EDIT_COMPANY_KEY_ID
import hr.ferit.filipcuric.cleantrack.navigation.EditCompanyDestination
import hr.ferit.filipcuric.cleantrack.navigation.HOME_ROUTE
import hr.ferit.filipcuric.cleantrack.navigation.LOGIN_ROUTE
import hr.ferit.filipcuric.cleantrack.navigation.NavigationItem
import hr.ferit.filipcuric.cleantrack.navigation.REGISTER_ROUTE
import hr.ferit.filipcuric.cleantrack.ui.addworker.AddWorkerScreen
import hr.ferit.filipcuric.cleantrack.ui.addworker.AddWorkerViewModel
import hr.ferit.filipcuric.cleantrack.ui.companyinfo.CompanyInfoScreen
import hr.ferit.filipcuric.cleantrack.ui.companyinfo.CompanyInfoViewModel
import hr.ferit.filipcuric.cleantrack.ui.createcompany.CreateCompanyScreen
import hr.ferit.filipcuric.cleantrack.ui.createcompany.CreateCompanyViewModel
import hr.ferit.filipcuric.cleantrack.ui.editcompany.EditCompanyScreen
import hr.ferit.filipcuric.cleantrack.ui.editcompany.EditCompanyViewModel
import hr.ferit.filipcuric.cleantrack.ui.home.HomeScreen
import hr.ferit.filipcuric.cleantrack.ui.home.HomeViewModel
import hr.ferit.filipcuric.cleantrack.ui.login.LoginScreen
import hr.ferit.filipcuric.cleantrack.ui.login.LoginViewModel
import hr.ferit.filipcuric.cleantrack.ui.register.RegisterScreen
import hr.ferit.filipcuric.cleantrack.ui.register.RegisterViewModel
import hr.ferit.filipcuric.cleantrack.ui.theme.Green
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState() //current screen

    val loginViewModel = koinViewModel<LoginViewModel>(parameters = { parametersOf(navController) })
    val registerViewModel = koinViewModel<RegisterViewModel>()
    val homeViewModel = koinViewModel<HomeViewModel>()
    val mainViewModel = koinViewModel<MainViewModel>()
    val createCompanyViewModel = koinViewModel<CreateCompanyViewModel>(parameters = { parametersOf(navController) })

    mainViewModel.isUserLoggedIn()
    if(mainViewModel.isLoggedIn)
        homeViewModel.getCompanies()

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

    var showCompanyEditButton by remember { mutableStateOf(false) }

    var currentCompanyId = ""
    var addedWorker = false


    Scaffold(
        topBar = {
            TopBar(
                backNavigationIcon = { if(showBackIcon) BackIcon(onBackClick = { navController.popBackStack() }) },
                secondNavigationIcon = { if(showSignOutButton) SignOutIcon(onClick = {
                    mainViewModel.signUserOut()
                    loginViewModel.loading = false
                    navController.navigate(LOGIN_ROUTE) {
                        popUpTo(HOME_ROUTE) {
                            inclusive = true
                        }
                    }
                }) else if (showCompanyEditButton) {
                    CompanyEditIcon(onClick = {
                        navController.navigate(EditCompanyDestination.createNavigation(currentCompanyId))
                    })
                }
                }
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
                    showCompanyEditButton = false
                    LoginScreen(
                        viewModel = loginViewModel,
                        onLoginClick = {
                            homeViewModel.getCompanies()
                        },
                        onRegisterClick = {
                            navController.navigate(REGISTER_ROUTE)
                        },
                    )
                }
                composable(NavigationItem.RegisterDestination.route) {
                    RegisterScreen(
                        viewModel = registerViewModel,
                        onRegisterClick = {
                            navController.navigate(HOME_ROUTE)
                            homeViewModel.getCompanies()
                        }
                    )
                }
                composable(NavigationItem.HomeDestination.route) {
                    HomeScreen(
                        viewModel = homeViewModel,
                        onCreateClick = {
                            navController.navigate(NavigationItem.CreateCompanyDestination.route)
                        },
                        onCompanyClick = {
                            navController.navigate(it)
                        }
                    )
                }
                composable(NavigationItem.CreateCompanyDestination.route) {
                    showCompanyEditButton = false
                    CreateCompanyScreen(
                        viewModel = createCompanyViewModel,
                        onCreateClick = {
                            homeViewModel.getCompanies()
                        }
                    )
                }
                composable(
                    route = CompanyInfoDestination.route,
                    arguments = listOf(navArgument(COMPANY_KEY_ID) { type = NavType.StringType}),
                ) {
                    val companyId = it.arguments?.getString(COMPANY_KEY_ID)
                    currentCompanyId = companyId!!
                    val viewModel = koinViewModel<CompanyInfoViewModel>(parameters = { parametersOf(navController, companyId) })
                    if(addedWorker) {
                        viewModel.getWorkers()
                        addedWorker = false
                    }
                    showCompanyEditButton = viewModel.isManager
                    CompanyInfoScreen(
                        viewModel = viewModel,
                        onDeleteCompanyClick = {
                            homeViewModel.getCompanies()
                        },
                        onLocationClick = { /*TODO*/ }) {

                    }
                }
                composable(
                    route = AddWorkerDestination.route,
                    arguments = listOf(navArgument(ADD_WORKER_COMPANY_KEY_ID) { type = NavType.StringType }),
                ) {
                    val companyId = it.arguments?.getString(ADD_WORKER_COMPANY_KEY_ID)
                    val viewModel = koinViewModel<AddWorkerViewModel>(parameters = { parametersOf(navController, companyId) })
                    addedWorker = true
                    showCompanyEditButton = false
                    AddWorkerScreen(
                        viewModel = viewModel,
                    )
                }
                composable(
                    route = EditCompanyDestination.route,
                    arguments = listOf(navArgument(EDIT_COMPANY_KEY_ID) { type = NavType.StringType })
                ) {
                    val companyId = it.arguments?.getString(EDIT_COMPANY_KEY_ID)
                    val viewModel = koinViewModel<EditCompanyViewModel>(parameters = { parametersOf(navController, companyId) })
                    viewModel.getCurrentCompany()
                    showCompanyEditButton = false
                    EditCompanyScreen(
                        viewModel = viewModel,
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
                .padding(0.dp)
                .clickable(onClick = onBackClick),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun SignOutIcon(
    onClick: () -> Unit,
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
                .clickable(onClick = onClick),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun CompanyEditIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onClick),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}
