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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

// Event list with static data
val events = listOf(
    Event(
        R.drawable.ic_jetpack_compose,
        "Bhoomi Vaghasiya",
        "Simplify Development with Compose",
        "10:15 AM - 11:00 AM"
    ),
    Event(
        R.drawable.ic_chatgpt,
        "Jay Thakkar",
        "GEN AI and LLM",
        "11:00 AM - 11:45 AM"
    ),
    Event(
        R.drawable.ic_flutter,
        "Vivek Yadav",
        "Let's scale Flutter Web",
        "11:45 AM - 12:30 PM"
    )
)


@Composable
fun MyApp() {

    // State to keep track of the current displayed event
    val currentEventIndex = remember { mutableStateOf(0) }

    val colors = listOf(
        Color(0xFFD1E2F7), // Light pastel blue
        Color(0xFFF9D8D5), // Light pastel red
        Color(0xFFF9E7D5)  // Light pastel yellow
    )


    //Column to arrange UI element in horizontal manner
    Column(modifier = Modifier.padding(10.dp)) {

        Image(
            painter = painterResource(id = R.drawable.google_io_banner),
            contentDescription = "Google IO"
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = "Events", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.padding(5.dp))

        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(colors[currentEventIndex.value % colors.size])
        ) {
            //Box to arrange UI element in center
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                EventItem(events[currentEventIndex.value])
            }
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                if (currentEventIndex.value > 0)
                    currentEventIndex.value = currentEventIndex.value - 1
            }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4))) {
                Text("Previous")
            }

            Button(onClick = {
                if (currentEventIndex.value <= 1)
                    currentEventIndex.value = currentEventIndex.value + 1
            }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4))) {
                Text("Next")
            }
        }

    }

}



//Reusable composable
@Composable
fun EventItem(event: Event) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp, 16.dp)
    ) {
        Image(
            painter = painterResource(id = event.speakerImage),
            contentDescription = event.speakerName,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = event.speakerName,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium
        )


        Text(
            text = event.topicName,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = event.time,
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMyApp() {
    MyApp()
}


/*
@Preview(showBackground = true)
@Composable
fun MyEventPreview() {
    EventItem(event = Event(R.drawable.ic_chatgpt, "Name", "Topic", "time"))
}
*/


// Data class to represent event item data
data class Event(
    val speakerImage: Int,
    val speakerName: String,
    val topicName: String,
    val time: String
)

