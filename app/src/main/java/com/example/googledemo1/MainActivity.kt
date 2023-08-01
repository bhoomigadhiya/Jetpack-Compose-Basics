package com.example.googledemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set Material3 theme for the entire app
            MaterialTheme {
                // Set Surface for top-level composable
                Surface(color = Color.White, modifier = Modifier.fillMaxSize(),) {
                    EventsAppUI()
                }
            }
        }
    }
}

// Event list with static data
val events = listOf(
    EventItemData(R.drawable.speaker1, "Bhoomi Vaghasiya", "Simplify Development with Compose", "10:00 AM - 11:00 AM"),
    EventItemData(R.drawable.speaker1, "Vivek Yadav", "Let's scale Flutter Web", "11:00 AM - 12:00 PM"),
    EventItemData(R.drawable.speaker1, "Jay Thakkar", "Machine Learning Basics", "12:00 PM - 1:00 PM")
)


@Preview
@Composable
fun PreviewData() {
    MaterialTheme {
        // Set Surface for top-level composable
        Surface(color = Color.White, modifier = Modifier.fillMaxSize(),) {
            EventsAppUI()
        }
    }
}


@Composable
fun EventsAppUI() {

    // State to keep track of the current displayed event
    val currentEventIndex = remember { mutableStateOf(0) }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Banner Image
            Image(
                painter = painterResource(id = R.drawable.google_io),
                contentDescription = "Google I/O Extended",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            // Event Item Card
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            
            ) {
                Box(
                    contentAlignment = Alignment.Center // Align the content (EventItem) to the center of the Box
                ) {
                    EventItem(events[currentEventIndex.value])
                }
            }

            // Next Event Button
            Button(
                onClick = {
                    currentEventIndex.value = (currentEventIndex.value + 1) % events.size
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Next Event")
            }
        }
}

@Composable
fun EventItem(eventItemData: EventItemData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Align children horizontally to the center)(
        modifier = Modifier.padding(16.dp)
    ) {
        // Speaker Image
        Image(
            painter = painterResource(id = eventItemData.speakerImage),
            contentDescription = "Speaker Image",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Speaker and Topic Details
        Text(
            text = eventItemData.speakerName,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
        Text(
            text = eventItemData.topicName,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            fontSize = 16.sp
        )
        Text(
            text = eventItemData.time,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

// Data class to represent event item data
data class EventItemData(
    val speakerImage: Int,
    val speakerName: String,
    val topicName: String,
    val time: String
)

@Preview
@Composable
fun PreviewEventScheduleAppUI() {
    EventsAppUI()
}
