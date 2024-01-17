package hr.ferit.filipcuric.cleantrack.ui.companyinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import hr.ferit.filipcuric.cleantrack.mock.getCompanies
import hr.ferit.filipcuric.cleantrack.mock.getLocations
import hr.ferit.filipcuric.cleantrack.model.Company
import hr.ferit.filipcuric.cleantrack.model.Location
import hr.ferit.filipcuric.cleantrack.ui.component.LocationCard
import hr.ferit.filipcuric.cleantrack.ui.component.LocationCardViewState
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

data class CompanyInfoScreenViewState(
    val company: Company,
    val isManager: Boolean,
    val locations: List<Location>,
)

@Composable
fun CompanyInfoScreen(
    companyInfoScreenViewState: CompanyInfoScreenViewState,
    onLocationClick: () -> Unit,
    onAddLocationClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = companyInfoScreenViewState.company.imageUrl,
            contentDescription = "Company logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .padding(bottom = 12.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = companyInfoScreenViewState.company.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
            Text(
                text = "Locations",
                fontSize = 22.sp,
            )
        }
        LazyRow(
            modifier = Modifier
                .padding(vertical=12.dp)
        ) {
            items(
                companyInfoScreenViewState.locations,
                key = { location -> location.id}
            ) {
                LocationCard(
                    locationCardViewState = LocationCardViewState(
                        location = it,
                    ),
                    onClick = onLocationClick,
                    modifier = Modifier
                        .height(100.dp)
                        .width(200.dp)
                        .padding(horizontal = 4.dp)
                )
            }
        }
        if(companyInfoScreenViewState.isManager) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = onAddLocationClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green,
                    )
                ) {
                    Text(text = "Add a location")
                }
            }
        }
    }
}

@Preview
@Composable
fun CompanyInfoScreenPreview() {
    CompanyInfoScreen(
        companyInfoScreenViewState = CompanyInfoScreenViewState(
            company = getCompanies().first(),
            isManager = true,
            locations = getLocations().filter { it.companyId == getCompanies().first().id }
        ),
        onLocationClick = { /*TODO*/ },
        onAddLocationClick = { /*TODO*/ }
    )
}
