package dev.bruno.mytasksapp.domain.model

import dev.bruno.mytasksapp.util.GenerateId
import jakarta.persistence.*
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "users")
data class User(
    @Id
    val id: String = GenerateId.generate(),
    val name: String,
    val email: String,
    @Column(name = "hashed_password")
    val hashedPassword: String,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updateAt: Instant? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val tasks: List<Task> = emptyList()
)
