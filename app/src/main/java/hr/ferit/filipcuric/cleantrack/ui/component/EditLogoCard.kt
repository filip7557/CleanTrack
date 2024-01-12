package hr.ferit.filipcuric.cleantrack.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import hr.ferit.filipcuric.cleantrack.R

data class EditLogoCardViewState(
    val imageUrl: String?,
)

@Composable
fun EditLogoCard(
    editLogoCardViewState: EditLogoCardViewState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = CardDefaults.elevatedShape,
        border = CardDefaults.outlinedCardBorder(true),
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick)
        ) {
            AsyncImage(
                model = editLogoCardViewState.imageUrl,
                contentDescription = "Company logo",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Surface (
                modifier = Modifier
                    .fillMaxSize(),
                color = Color(0f, 0f, 0f, 0.9f, ColorSpaces.Srgb)
            ) {}
            Image(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "Circled plus icon",
                colorFilter = ColorFilter.tint(Color(0, 90, 4)),
            )
        }
    }
}

@Preview
@Composable
fun EditLogoCardPreview() {
    EditLogoCard(
        editLogoCardViewState = EditLogoCardViewState(
            imageUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/cleaning-service-company-logo-design-template-db40091848b1283994ec71ae58270d9f_screen.jpg?ts=1669998818",
        ),
        onClick = { },
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
    )
}
