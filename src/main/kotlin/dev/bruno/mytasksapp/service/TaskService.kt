package dev.bruno.mytasksapp.service

import dev.bruno.mytasksapp.data.repository.TaskRepository
import dev.bruno.mytasksapp.domain.model.Task
import dev.bruno.mytasksapp.domain.model.dto.CreateTaskDto
import dev.bruno.mytasksapp.domain.model.dto.GetTaskDto
import dev.bruno.mytasksapp.domain.model.dto.ListTaskDto
import dev.bruno.mytasksapp.domain.model.dto.UpdateTaskDto
import dev.bruno.mytasksapp.exception.IllegalAccessTaskException
import dev.bruno.mytasksapp.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val userService: UserService
) {

    fun add(userId: String, taskDto: CreateTaskDto) {
        val user = userService.getUserOrThrowException(userId)
        taskRepository.save(
            Task(
                title = taskDto.title,
                description = taskDto.description,
                completed = taskDto.completed,
                user = user
            )
        )
    }

    fun list(userId: String): List<ListTaskDto> {
        val user = userService.getUserOrThrowException(userId)
        return taskRepository.findAll().filter { it.user.id == user.id }.map {
            ListTaskDto(
                id = it.id,
                title = it.title,
                description = it.description,
                completed = it.completed,
                createdAt = it.createdAt,
                updateAt = it.updateAt
            )
        }
    }

    fun getById(taskId: String, userId: String): GetTaskDto {
        val task = getTaskOrThrowException(taskId)
        if (task.user.id != userId) throw IllegalAccessTaskException("the task with id ${task.id} does not belong to you.")
        return GetTaskDto(
            id = task.id,
            title = task.title,
            description = task.description,
            completed = task.completed,
            createdAt = task.createdAt,
            updateAt = task.updateAt
        )
    }

    fun update(taskId: String, userId: String, updateTaskDto: UpdateTaskDto) {
        val task = getTaskOrThrowException(id = taskId)
        if (task.user.id != userId) throw IllegalAccessTaskException("the task with id ${task.id} does not belong to you.")
        taskRepository.save(
            task.copy(
                title = updateTaskDto.title,
                description = updateTaskDto.description,
                completed = updateTaskDto.completed
            )
        )
    }

    fun remove(taskId: String, userId: String) {
        val task = getById(taskId = taskId, userId = userId)
        taskRepository.deleteById(task.id)
    }

    fun removeAll(userId: String) {
        val user = userService.getById(userId)
        taskRepository.deleteAll(taskRepository.findAll().filter { it.user.id == user.id })
    }

    private fun getTaskOrThrowException(id: String): Task {
        return taskRepository.findByIdOrNull(id) ?: throw NotFoundException("Task with id $id not found")
    }
}