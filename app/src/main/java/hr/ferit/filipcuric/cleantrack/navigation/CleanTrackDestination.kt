package hr.ferit.filipcuric.cleantrack.navigation

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
}

data object CompanyInfoDestination : CleanTrackDestination(COMPANY_INFO_ROUTE_WITH_PARAMS) {
    fun createNavigation(companyId: String): String = "$COMPANY_INFO_ROUTE/${companyId}"
}

data object EditCompanyDestination : CleanTrackDestination(EDIT_COMPANY_ROUTE_WITH_PARAMS) {
    fun createNavigation(companyId: String): String = "$EDIT_COMPANY_ROUTE/${companyId}"
}

data object AddLocationDestination : CleanTrackDestination(ADD_LOCATION_ROUTE_WITH_PARAMS) {
    fun createNavigation(companyId: String): String = "$ADD_LOCATION_ROUTE_WITH_PARAMS/${companyId}"
}
