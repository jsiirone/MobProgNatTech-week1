package com.example.mobprognattech_week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import com.example.mobprognattech_week1.domain.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                HomeScreen()
            }
        }
    }
}


@Composable
fun HomeScreen() {
    var taskList by remember { mutableStateOf(mockTasks) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {

        Text(
            text = "Tasks",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        taskList.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = if (task.done) "✔ ${task.title}" else task.title
                )

                Button( // toggle done
                    onClick = {
                        taskList = toggleDone(taskList, task.id)
                    }
                ) {
                    Text("Toggle")
                }
            }
        }

        Button( // add task
            onClick = {
                val newTask = Task(
                    id = taskList.size + 1,
                    title = "New Task ${taskList.size + 1}",
                    description = "New Description",
                    priority = 3,
                    dueDate = "15.01.2026",
                    done = false
                )
                taskList = addTask(taskList, newTask)
            }
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button( // filter by done
            onClick = {
                taskList = filterByDone(taskList, done = true)
            }
        ) {
            Text("Show Done")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button( // sort by due date
            onClick = {
                taskList = sortByDueDate(taskList)
            }
        ) {
            Text("Sort by Due Date")
        }

    }
}