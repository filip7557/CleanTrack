package hr.ferit.filipcuric.cleantrack.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.R
import hr.ferit.filipcuric.cleantrack.mock.getCompanies
import hr.ferit.filipcuric.cleantrack.model.Company
import hr.ferit.filipcuric.cleantrack.ui.component.CompanyCard
import hr.ferit.filipcuric.cleantrack.ui.component.CompanyCardViewState
import hr.ferit.filipcuric.cleantrack.ui.component.CreateNewCompanyCard

private const val NUMBER_OF_COLUMNS = 2

data class HomeScreenViewState(
    val companies: List<Company>
)

@Composable
fun HomeScreen(
    homeScreenViewState: HomeScreenViewState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.my_companies),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .padding(start = 12.dp, top=12.dp, bottom=12.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(NUMBER_OF_COLUMNS),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            item {
                CreateNewCompanyCard(
                    modifier = Modifier
                        .height(280.dp)
                        .width(160.dp)
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                    onClick = { /*TODO*/ }
                )
            }

            items(
                homeScreenViewState.companies,
                key = { company -> company.id }
            ) {
                CompanyCard(
                    companyCardViewState = CompanyCardViewState(
                        name = it.name,
                        position = it.position,
                        imageUrl = it.imageUrl,
                    ),
                    modifier = Modifier
                        .height(280.dp)
                        .width(160.dp)
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                    onClick = { /*TODO*/ },
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        homeScreenViewState = HomeScreenViewState(
            companies = getCompanies()
        )
    )
}
