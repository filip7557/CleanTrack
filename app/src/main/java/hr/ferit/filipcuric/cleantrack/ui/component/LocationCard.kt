package hr.ferit.filipcuric.cleantrack.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier
            .clickable(onClick = onClick)
            .defaultMinSize(minHeight = 100.dp, minWidth = 300.dp),
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
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = locationCardViewState.location.address,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "${locationCardViewState.location.frequency} times/week",
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
