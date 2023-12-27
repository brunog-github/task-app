package dev.bruno.mytasksapp.domain.model

import dev.bruno.mytasksapp.util.GenerateId
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    val id: String = GenerateId.generate(),
    val title: String,
    val description: String,
    val completed: Boolean = false,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updateAt: Instant? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)
