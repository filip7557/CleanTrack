package hr.ferit.filipcuric.cleantrack.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.mock.getLocations
import hr.ferit.filipcuric.cleantrack.model.Location

data class LocationCardViewState(
    val location: Location,
)

@Composable
fun LocationCard(
    locationCardViewState: LocationCardViewState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        shape = CardDefaults.elevatedShape,
        border = CardDefaults.outlinedCardBorder(true),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = modifier
            .clickable(onClick = onClick),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = locationCardViewState.location.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = locationCardViewState.location.address,
                fontWeight = FontWeight.Light,
            )
            Text(
                text = "${locationCardViewState.location.frequency} times/week",
                fontWeight = FontWeight.Light,
            )
        }
    }
}

@Preview
@Composable
fun LocationCardPreview() {
    LocationCard(
        locationCardViewState = LocationCardViewState(
            location = getLocations().first()
        ),
        modifier = Modifier
            .height(100.dp)
            .width(200.dp),
        onClick = { /*TODO*/ },
    )
}
