package hr.ferit.filipcuric.cleantrack.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class CompanyCardViewState(
    val imageUrl: String?,
    val name: String,
    val position: String,
)

@Composable
fun CompanyCard(
    companyCardViewState: CompanyCardViewState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 0.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = CardDefaults.elevatedShape,
        modifier = modifier
            .clickable(onClick = onClick),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = companyCardViewState.imageUrl,
                contentDescription = "Company logo",
                modifier = Modifier
                    .fillMaxHeight(0.75f),
                contentScale = ContentScale.Crop,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 10.dp, bottom=10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = companyCardViewState.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = companyCardViewState.position,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
fun CompanyCardPreview() {
    CompanyCard(
        companyCardViewState = CompanyCardViewState(
            imageUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/cleaning-service-company-logo-design-template-db40091848b1283994ec71ae58270d9f_screen.jpg?ts=1669998818",
            name = "ZOP - KOT",
            position = "Manager",
        ),
        onClick = {},
        modifier = Modifier
            .height(250.dp)
            .width(160.dp)
    )
}
