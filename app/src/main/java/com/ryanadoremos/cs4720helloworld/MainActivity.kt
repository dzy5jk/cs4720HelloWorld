package com.ryanadoremos.cs4720helloworld


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryanadoremos.cs4720helloworld.ui.theme.Cs4720HelloWorldTheme
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cs4720HelloWorldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    var name by remember { mutableStateOf("Your name here") }
    var selectedGreeting by remember { mutableStateOf("Hello") }
    val greetings = listOf("Hello", "Hi There", "Bonjour")


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$selectedGreeting, $name!", style = MaterialTheme.typography.headlineMedium)


        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Your name") }
        )




        Spacer(modifier = Modifier.height(8.dp))




        DropdownMenuExample(greetings) { selectedGreeting = it }
    }
}


@Composable
fun DropdownMenuExample(greetings: List<String>, onGreetingSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }


    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            greetings[selectedIndex],
            modifier = Modifier.fillMaxWidth().clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            greetings.forEachIndexed { index, greeting ->
                DropdownMenuItem(
                    text = { Text(greeting) },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                        onGreetingSelected(greeting)
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Cs4720HelloWorldTheme {
        MyApp()
    }
}
