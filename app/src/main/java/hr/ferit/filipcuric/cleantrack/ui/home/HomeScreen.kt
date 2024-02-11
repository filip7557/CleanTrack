package hr.ferit.filipcuric.cleantrack.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.R
import hr.ferit.filipcuric.cleantrack.navigation.CompanyInfoDestination
import hr.ferit.filipcuric.cleantrack.ui.component.CompanyCard
import hr.ferit.filipcuric.cleantrack.ui.component.CompanyCardViewState
import hr.ferit.filipcuric.cleantrack.ui.component.LoadingAnimation

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
            LazyColumn {
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.my_companies),
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier
                                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                        )
                        Text(
                            text = "Create a new company",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier
                                .padding(top = 12.dp, end = 12.dp)
                                .clickable {
                                    onCreateClick()
                                },
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
                items(
                    items = viewModel.companies,
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
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                        onClick = { onCompanyClick(CompanyInfoDestination.createNavigation(it.id!!)) },
                    )
                }
            }
        }
    }
}
