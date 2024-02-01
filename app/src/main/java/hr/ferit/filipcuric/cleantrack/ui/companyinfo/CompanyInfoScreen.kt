package hr.ferit.filipcuric.cleantrack.ui.companyinfo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import hr.ferit.filipcuric.cleantrack.R
import hr.ferit.filipcuric.cleantrack.ui.component.LocationCard
import hr.ferit.filipcuric.cleantrack.ui.component.LocationCardViewState
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

@Composable
fun CompanyInfoScreen(
    viewModel: CompanyInfoViewModel,
    onLocationClick: () -> Unit,
    onAddLocationClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {
            AsyncImage(
                model = viewModel.company.imageUrl,
                contentDescription = "Company logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.45f)
                    .padding(bottom = 12.dp)
            )
        }
        item {
            Text(
                text = viewModel.company.name,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
        }
        item {
            Text(
                text = "Manager:",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
        item {
            Text(
                text = viewModel.manager,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 12.dp)
            )
        }
        item {
            Text(
                text = "Workers",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        item {
            if (viewModel.workers.isEmpty()) {
                Text(
                    text = "There are no workers added yet.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(bottom=12.dp)
                )
            } else {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(3),
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .height(100.dp),
                    contentPadding = PaddingValues(8.dp),
                ) {
                    this@LazyHorizontalGrid.items(
                        viewModel.workers,
                        key = { worker -> worker.id!! }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(end=8.dp)
                                .width(150.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = if (viewModel.isManager) Arrangement.SpaceBetween else Arrangement.Center
                        ) {
                            Text(
                                text = "${it.firstname} ${it.lastname}",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 16.sp
                            )
                            if(viewModel.isManager) {
                                Icon(
                                    painter = painterResource(id = R.drawable.remove_button_svgrepo_com),
                                    contentDescription = "remove icon",
                                    tint = Color.Red,
                                    modifier = Modifier
                                        .clickable {
                                            viewModel.removeWorker(it.id)
                                        }
                                        .padding(horizontal = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        item {
            if (viewModel.isManager) {
                Button(
                    onClick = { viewModel.onAddWorkerClick() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green,
                    ),
                    modifier = Modifier
                        .padding(bottom=12.dp)
                ) {
                    Text(
                        text = "Add a worker",
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
        item {
            Text(
                text = "Locations",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        item {
            if (viewModel.locations.isEmpty()) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "There are no locations added yet.",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
            } else if (viewModel.locations.count() == 1) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LocationCard(
                        locationCardViewState = LocationCardViewState(
                            location = viewModel.locations.first(),
                        ),
                        onClick = onLocationClick,
                        modifier = Modifier
                            .height(120.dp)
                            .width(200.dp)
                            .padding(vertical = 12.dp)
                    )
                }
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                ) {
                    items(
                        viewModel.locations,
                        key = { location -> location.id }
                    ) {
                        LocationCard(
                            locationCardViewState = LocationCardViewState(
                                location = it,
                            ),
                            onClick = onLocationClick,
                            modifier = Modifier
                                .height(120.dp)
                                .width(200.dp)
                                .padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }
        item {
            if (viewModel.isManager) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = onAddLocationClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green,
                        )
                    ) {
                        Text(
                            text = "Add a location",
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    Button(
                        onClick = { viewModel.deleteCompany() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )
                    ) {
                        Text(
                            text = "Delete this company",
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
        }
    }
}
