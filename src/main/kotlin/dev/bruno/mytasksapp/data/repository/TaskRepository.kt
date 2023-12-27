package dev.bruno.mytasksapp.data.repository

import dev.bruno.mytasksapp.domain.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, String>