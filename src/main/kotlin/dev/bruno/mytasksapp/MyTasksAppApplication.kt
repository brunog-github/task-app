package dev.bruno.mytasksapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyTasksAppApplication

fun main(args: Array<String>) {
	runApplication<MyTasksAppApplication>(*args)
}
