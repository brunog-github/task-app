package dev.bruno.mytasksapp.controller

import dev.bruno.mytasksapp.domain.model.Task
import dev.bruno.mytasksapp.domain.model.dto.*
import dev.bruno.mytasksapp.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/user")
class TaskController(
    private val taskService: TaskService
) {
    @PostMapping("/{user_id}/task")
    fun add(
        @RequestBody createTaskDto: CreateTaskDto,
        @PathVariable("user_id") userId: String
    ): ResponseEntity<Unit> {
        taskService.add(taskDto = createTaskDto, userId = userId)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{user_id}/task")
    fun list(@PathVariable("user_id") userId: String): ResponseEntity<List<ListTaskDto>> {
        val tasks = taskService.list(userId = userId)
        return ResponseEntity.ok(tasks)
    }

    @GetMapping("/{user_id}/task/{task_id}")
    fun getTaskById(
        @PathVariable("user_id") userId: String,
        @PathVariable("task_id") taskId: String,
    ): ResponseEntity<GetTaskDto> {
        val task = taskService.getById(userId = userId, taskId = taskId)
        return ResponseEntity.ok(task)
    }

    @PutMapping("/{user_id}/task/{task_id}")
    fun update(
        @PathVariable("user_id") userId: String,
        @PathVariable("task_id") taskId: String,
        @RequestBody updateTaskDto: UpdateTaskDto
    ): ResponseEntity<Unit> {
        taskService.update(taskId = taskId, userId = userId, updateTaskDto = updateTaskDto)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @DeleteMapping("/{user_id}/task/{task_id}")
    fun remove(
        @PathVariable("user_id") userId: String,
        @PathVariable("task_id") taskId: String
    ): ResponseEntity<Unit> {
        taskService.remove(userId = userId, taskId = taskId)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @DeleteMapping("/{user_id}/task")
    fun removeAll(
        @PathVariable("user_id") userId: String
    ): ResponseEntity<Unit> {
        taskService.removeAll(userId = userId)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }
}