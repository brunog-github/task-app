package dev.bruno.mytasksapp.service

import dev.bruno.mytasksapp.data.repository.UserRepository
import dev.bruno.mytasksapp.domain.model.User
import dev.bruno.mytasksapp.domain.model.dto.CreateUserDto
import dev.bruno.mytasksapp.domain.model.dto.GetUserDto
import dev.bruno.mytasksapp.domain.model.dto.UpdateUserDto
import dev.bruno.mytasksapp.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun register(userDto: CreateUserDto): User {
        val user = User(
            name = userDto.name,
            email = userDto.email,
            hashedPassword = userDto.password,
            createdAt = Instant.now()
        )
        return userRepository.save(user)
    }

    fun getByEmail(email: String): GetUserDto {
        val user = userRepository.findByEmail(email) ?: throw NotFoundException("User with email $email not found")
        return GetUserDto(
            id = user.id,
            name = user.name,
            email = user.email,
            createdAt = user.createdAt,
            updateAt = user.updateAt
        )
    }

    fun getById(id: String): GetUserDto {
        val user = getUserOrThrowException(id)
        return GetUserDto(
            id = user.id,
            name = user.name,
            email = user.email,
            createdAt = user.createdAt,
            updateAt = user.updateAt
        )
    }

    fun update(id: String, updateUserDto: UpdateUserDto) {
        val user = getUserOrThrowException(id)
        userRepository.save(
            user.copy(
                name = updateUserDto.name,
                hashedPassword = updateUserDto.password
            )
        )
    }

    fun remove(id: String) {
        val user = getUserOrThrowException(id)
        userRepository.deleteById(user.id)
    }

    fun getUserOrThrowException(id: String): User {
        return userRepository.findByIdOrNull(id) ?: throw NotFoundException("User with id $id not found")
    }
}