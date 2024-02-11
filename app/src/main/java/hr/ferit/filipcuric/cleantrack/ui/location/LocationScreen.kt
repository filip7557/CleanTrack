package hr.ferit.filipcuric.cleantrack.ui.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.R
import hr.ferit.filipcuric.cleantrack.ui.component.LabelWithBullet
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

@Composable
fun LocationScreen(
    viewModel: LocationViewModel,
    onDeleteLocationClick: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        val foundCleaners by viewModel.foundCleaners.collectAsState()
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(12.dp)
        ) {
            item {
                Text(
                    text = viewModel.location.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = viewModel.location.address,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                )
            }
            item {
                Text(
                    text = "${viewModel.location.frequency} times/week",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                )
            }
            item {
                Text(
                    text = "Cleaners:",
                    fontSize = 20.sp
                )
            }
            if (viewModel.cleaners.isEmpty()) {
                item {
                    Text(
                        text = "There are no cleaners on this location yet.",
                        fontSize = 18.sp
                    )
                }
            }
            items(
                items = viewModel.cleaners,
                key = { cleaner -> cleaner.id!! }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (viewModel.isUserCompanyManager) {
                        Icon(
                            painter = painterResource(id = R.drawable.remove_button_svgrepo_com),
                            contentDescription = "remove icon",
                            tint = Color.Red,
                            modifier = Modifier
                                .clickable {
                                    viewModel.onCleanerRemoveClick(it)
                                }
                        )
                    }
                    LabelWithBullet(text = "${it.firstname} ${it.lastname}")
                }
            }
            if (viewModel.isUserCompanyManager) {
                item {
                    TextField(
                        value = viewModel.cleaner,
                        label = {
                            Text(text = "Cleaner")
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Green,
                            focusedLabelColor = Green,
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(top = 12.dp, bottom = 10.dp)
                            .fillMaxWidth(),
                        onValueChange = { viewModel.onCleanerValueChange(it) }
                    )
                }
                items(
                    items = foundCleaners,
                    key = { cleaner -> cleaner.id!! }
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .height(60.dp)
                            .clickable {
                                viewModel.onCleanerAddClick(it)
                            },
                        shape = CardDefaults.elevatedShape,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${it.firstname} ${it.lastname} - (${it.username})"
                            )
                        }
                    }
                }
            }
            item {
                Text(
                    text = "Jobs:",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 25.dp)
                )
            }
            if (viewModel.jobs.isEmpty()) {
                item {
                    Text(
                        text = "There are no jobs on this location yet.",
                        fontSize = 18.sp
                    )
                }
            }
            items(
                items = viewModel.jobs,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (viewModel.isUserCompanyManager) {
                        Icon(
                            painter = painterResource(id = R.drawable.remove_button_svgrepo_com),
                            contentDescription = "remove icon",
                            tint = Color.Red,
                            modifier = Modifier
                                .clickable {
                                    viewModel.onJobRemoveClick(it)
                                }
                        )
                    }
                    LabelWithBullet(text = it)
                }
            }
            if (viewModel.isUserCompanyManager) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 25.dp)
                    ) {
                        TextField(
                            value = viewModel.job,
                            label = {
                                Text(text = "Job")
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Green,
                                focusedLabelColor = Green,
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .padding(bottom = 10.dp),
                            onValueChange = { viewModel.onJobValueChange(it) }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_circledplus),
                            contentDescription = "plus icon",
                            tint = Green,
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .size(50.dp)
                                .clickable {
                                    viewModel.onJobAddClick()
                                }
                        )
                    }
                }
                if (viewModel.isUserCompanyManager) {
                    item {
                        Button(
                            onClick = { viewModel.deleteLocation(); onDeleteLocationClick() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top=25.dp)
                        ) {
                            Text(
                                text = "Delete this location",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}
