package hr.ferit.filipcuric.cleantrack.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.R

@Composable
fun CreateNewCompanyCard(
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
                text = stringResource(id = R.string.create_new_company),
                color = Color(0, 90, 4),
                fontSize = 18.sp,
            )
        }
    }
}

@Preview
@Composable
fun CreateNewCompanyCardPreview() {
    CreateNewCompanyCard(
        onClick = {},
        modifier = Modifier
            .height(250.dp)
            .width(160.dp)
    )
}
