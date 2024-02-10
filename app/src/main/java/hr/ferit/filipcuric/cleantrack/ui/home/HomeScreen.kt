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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.R
import hr.ferit.filipcuric.cleantrack.navigation.CompanyInfoDestination
import hr.ferit.filipcuric.cleantrack.ui.component.CompanyCard
import hr.ferit.filipcuric.cleantrack.ui.component.CompanyCardViewState
import hr.ferit.filipcuric.cleantrack.ui.component.CreateNewCompanyCard
import hr.ferit.filipcuric.cleantrack.ui.component.LoadingAnimation

private const val NUMBER_OF_COLUMNS = 2

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onCreateClick: () -> Unit,
    onCompanyClick: (String) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        if (viewModel.loading) {
            viewModel.getCompanies()
            LoadingAnimation()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.my_companies),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
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
                            onClick = onCreateClick,
                        )
                    }

                    items(
                        viewModel.companies,
                        key = { company -> company.id!! }
                    ) {
                        CompanyCard(
                            companyCardViewState = CompanyCardViewState(
                                name = it.name,
                                position = if (it.managerId == null || it.managerId != viewModel.userId) "Worker" else "Manager",
                                imageUrl = it.imageUrl,
                            ),
                            modifier = Modifier
                                .height(280.dp)
                                .width(160.dp)
                                .padding(vertical = 8.dp, horizontal = 8.dp),
                            onClick = { onCompanyClick(CompanyInfoDestination.createNavigation(it.id!!)) },
                        )
                    }
                }
            }
        }
    }
}
