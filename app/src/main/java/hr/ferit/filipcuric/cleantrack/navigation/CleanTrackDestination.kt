package hr.ferit.filipcuric.cleantrack.navigation

import hr.ferit.filipcuric.cleantrack.CleanTrack

const val LOGIN_ROUTE = "Login"
const val REGISTER_ROUTE = "Register"
const val HOME_ROUTE = "Home"
const val CREATE_COMPANY_ROUTE = "CreateCompany"

const val COMPANY_INFO_ROUTE = "CompanyInfo"
const val COMPANY_KEY_ID = "companyId"
const val COMPANY_INFO_ROUTE_WITH_PARAMS = "$COMPANY_INFO_ROUTE/{$COMPANY_KEY_ID}"

const val EDIT_COMPANY_ROUTE = "EditCompany"
const val EDIT_COMPANY_KEY_ID = "companyId"
const val EDIT_COMPANY_ROUTE_WITH_PARAMS = "$EDIT_COMPANY_ROUTE/{$EDIT_COMPANY_KEY_ID}"

const val ADD_LOCATION_ROUTE = "AddLocation"
const val ADD_LOCATION_KEY_COMPANY_ID = "companyId"
const val ADD_LOCATION_ROUTE_WITH_PARAMS = "$ADD_LOCATION_ROUTE/{$ADD_LOCATION_KEY_COMPANY_ID}"

const val ADD_WORKER_ROUTE = "AddWorker"
const val ADD_WORKER_COMPANY_KEY_ID = "companyId"
const val ADD_WORKER_ROUTE_WITH_PARAMS = "$ADD_WORKER_ROUTE/{$ADD_WORKER_COMPANY_KEY_ID}"

const val LOCATION_ROUTE = "Location"
const val LOCATION_KEY_ID = "locationId"
const val LOCATION_IS_MANAGER = "isUserManager"
const val LOCATION_ROUTE_WITH_PARAMS = "$LOCATION_ROUTE/{$LOCATION_KEY_ID}&{$LOCATION_IS_MANAGER}"

sealed class CleanTrackDestination(
    open val route: String
)

sealed class NavigationItem(
    override val route: String,
) : CleanTrackDestination(route) {

    data object LoginDestination : NavigationItem(
        route = LOGIN_ROUTE
    )

    data object RegisterDestination : NavigationItem(
        route = REGISTER_ROUTE
    )

    data object HomeDestination : NavigationItem(
        route = HOME_ROUTE
    )

    data object CreateCompanyDestination : NavigationItem(
        route = CREATE_COMPANY_ROUTE
    )
}

data object CompanyInfoDestination : CleanTrackDestination(COMPANY_INFO_ROUTE_WITH_PARAMS) {
    fun createNavigation(companyId: String): String = "$COMPANY_INFO_ROUTE/${companyId}"
}

data object EditCompanyDestination : CleanTrackDestination(EDIT_COMPANY_ROUTE_WITH_PARAMS) {
    fun createNavigation(companyId: String): String = "$EDIT_COMPANY_ROUTE/${companyId}"
}

data object AddLocationDestination : CleanTrackDestination(ADD_LOCATION_ROUTE_WITH_PARAMS) {
    fun createNavigation(companyId: String): String = "$ADD_LOCATION_ROUTE/${companyId}"
}

data object AddWorkerDestination : CleanTrackDestination(ADD_WORKER_ROUTE_WITH_PARAMS) {
    fun createNavigation(companyId: String): String = "$ADD_WORKER_ROUTE/${companyId}"
}

data object LocationDestination : CleanTrackDestination(LOCATION_ROUTE_WITH_PARAMS) {
    fun createNavigation(locationId: String, isUserManager: Boolean): String = "$LOCATION_ROUTE/${locationId}&${isUserManager}"
}
