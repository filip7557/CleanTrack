package hr.ferit.filipcuric.cleantrack.ui.addworker

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddWorkerScreen(
    viewModel: AddWorkerViewModel,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        val foundWorkers by viewModel.foundWorkers.collectAsState()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            stickyHeader {
                Text(
                    text = "Add a worker",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
            item {
                TextField(
                    value = viewModel.searchValue,
                    label = {
                        Text(text = "Username")
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Green,
                        focusedLabelColor = Green,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(bottom = 10.dp, start = 12.dp, end = 12.dp)
                        .fillMaxWidth(),
                    onValueChange = { viewModel.onSearchValueChange(it) }
                )
            }
            item {
                if (viewModel.hasError) {
                    Text(
                        text = "You cannot add an existing worker or yourself.",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    )
                }
            }
            items(
                foundWorkers,
                key = { user -> user.id!! }
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .height(60.dp)
                        .clickable {
                            viewModel.addWorkerToCompany(it.id!!)
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
    }
}
