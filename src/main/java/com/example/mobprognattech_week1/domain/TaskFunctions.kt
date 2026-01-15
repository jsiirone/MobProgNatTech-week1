package com.example.mobprognattech_week1.domain

fun addTask(list: List<Task>, newtask: Task): List<Task> {
    return list + newtask
}

fun toggleDone(list: List<Task>, id: Int): List<Task> {
    return list.map { task ->
        if (task.id == id) {
            task.copy(done = !task.done)
        } else {
            task
        }
    }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { task ->
        task.done == done
    }
}

fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { task ->
        task.dueDate
    }
}
