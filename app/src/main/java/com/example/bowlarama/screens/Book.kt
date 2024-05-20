package com.example.bowlarama.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.bowlarama.MainActivity
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter



@Composable
fun BookScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        inputForm()
        /*Text(
            text = "Book screen",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )*/

    }
}

@Composable
fun inputForm() {
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    var pickedTime by remember {
        mutableStateOf(LocalTime.NOON)
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }

    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(pickedTime)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    var teamName by remember { mutableStateOf("") }
    var formValid by remember { mutableStateOf(true) }
    var formSubmitted by remember { mutableStateOf(false) }

    var feedbackMessage = if (formValid) "" else "Please fill in all fields."

    Column (
        modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
    ){
        // Input elements for the team name
        Text(text = "Please enter your team name")

        TextField(value = teamName, onValueChange = {
            teamName = it
        })

        Spacer(modifier = Modifier.height(16.dp))

        // Input elements for the user to choose a date and time
        Button(onClick = {
            dateDialogState.show()
        }) {
            Text(text = "Pick date")
        }
        Text(text = formattedDate)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            timeDialogState.show()
        }) {
            Text(text = "Pick time")
        }
        Text(text = formattedTime)

        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "OK")
                negativeButton(text = "Cancel")
            }
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",

            ) {
                pickedDate = it
            }
        }

        MaterialDialog(
            dialogState = timeDialogState,
            buttons = {
                positiveButton(text = "OK")
                negativeButton(text = "Cancel")
            }
        ) {
            timepicker(
                initialTime = LocalTime.NOON,
                title = "Pick a date",

                ) {
                pickedTime = it
            }
        }

        Text(text = feedbackMessage)

        Button(onClick = {
            val strDate = pickedDate.toString()
            val strTime = pickedTime.toString()

            if (!validateForm(teamName, pickedDate.toString(), pickedTime.toString())) {
                formValid = false
            }
            else {
                formValid = true
                Log.d(MainActivity::class.java.simpleName, "Team name: $teamName\nDate: $strDate\nTime: $strTime")
                formSubmitted = true
            }
        }) {
            Text(text = "Submit")
        }

    }

    if (formSubmitted) {
        val strDate = pickedDate.toString()
        val strTime = pickedTime.toString()

        pickedDate = LocalDate.now()
        pickedTime = LocalTime.NOON
        teamName = ""

        Toast.makeText(LocalContext.current, "Team name: $teamName\nDate: $strDate, Time: $strTime", Toast.LENGTH_LONG).show()

        formSubmitted = false
    }


}

fun validateForm(teamName : String, date: String, time: String) : Boolean {
    return teamName.isNotBlank() && date.isNotBlank() && time.isNotBlank()
}


@Composable
@Preview
fun BookScreenPreview() {
    BookScreen()
}