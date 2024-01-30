package hr.ferit.filipcuric.cleantrack.ui.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import hr.ferit.filipcuric.cleantrack.R

@Composable
fun UploadLogoCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        shape = CardDefaults.elevatedShape,
        border = CardDefaults.outlinedCardBorder(true),
        modifier = modifier
            .clickable(onClick = onClick),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if(imageUri != Uri.EMPTY) {
                AsyncImage(
                    model = imageUri
                    ,
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Surface (
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(0f, 0f, 0f, 0.8f, ColorSpaces.Srgb)
                ) {}
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_circledplus),
                        contentDescription = "Circled plus icon",
                        colorFilter = ColorFilter.tint(Color(0, 90, 4)),
                    )
                    Text(
                        text = stringResource(id = R.string.upload_logo),
                        color = Color(0, 90, 4),
                        fontSize = 18.sp,
                    )
            }
        }
    }
}

@Preview
@Composable
fun UploadLogoCardPreview() {
    UploadLogoCard(
        onClick = {},
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
    )
}
