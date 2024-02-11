package hr.ferit.filipcuric.cleantrack.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.R

@Composable
fun LabelWithBullet(
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.bullet),
            contentDescription = "bullet icon",
        )
        Text(
            text = text,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview
@Composable
fun LabelWithBulletPreview() {
    LabelWithBullet(text = "Firstname Lastname")
}
