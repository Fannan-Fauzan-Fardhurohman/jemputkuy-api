package com.fanxan.jemputkuyapi.repository

import com.fanxan.jemputkuyapi.DatabaseComponent
import com.fanxan.jemputkuyapi.JemputkuyException
import com.fanxan.jemputkuyapi.model.User
import com.fanxan.jemputkuyapi.orThrow
import com.fanxan.jemputkuyapi.toResult
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.litote.kmongo.updateOneById
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    @Autowired
    private val databaseComponent: DatabaseComponent
) : UserRepository {

    private fun getCollection(): MongoCollection<User> {
        return databaseComponent.database.getDatabase("jemputkuy").getCollection()
    }

    override fun insertUser(user: User): Result<Boolean> {
        val existingUser = getUserByUsername(user.username)
        return if (existingUser.isSuccess) {
            throw JemputkuyException("User Exists")
        } else {
            getCollection().insertOne(user).wasAcknowledged().toResult()
        }
    }

    override fun getUserById(id: String): Result<User> {
        return getCollection().findOne(User::id eq id).toResult("User with Id $id not found")
    }

    override fun getUserByUsername(username: String): Result<User> {
        return getCollection().findOne(User::username eq username).toResult("User with Username $username not found!")
    }

    override fun updateUser(id: String, user: User): Result<Boolean> {
        println("User => $user")
        val existingUser = getCollection().findOne(User::id eq id)

        // check username is Exist
        val existingUsername = getCollection().findOne(User::username eq user.username)


        if (existingUsername != null) {
            println("existing username "+existingUsername.id)
            if (existingUsername.id == existingUser?.id) {
                return doUpdate(
                    User(
                        id = existingUser.id,
                        username = user.username,
                        password = user.password.ifEmpty { existingUser.password },
                        first_name = user.first_name,
                        last_name = user.last_name,
                        phone_number = user.phone_number,
                        email = user.email,
                        role = existingUser.role
                    )
                )
            } else {
                throw JemputkuyException("Username is already exist!")
            }
        } else {
            return doUpdate(
                User(
                    id = existingUser?.id ?: "",
                    username = user.username,
                    password = user.password.ifEmpty { existingUser?.password ?: "" },
                    first_name = user.first_name,
                    last_name = user.last_name,
                    phone_number = user.phone_number,
                    email = user.email,
                    role = existingUser?.role ?: 1
                )
            )
        }
    }

    private fun doUpdate(user: User): Result<Boolean> {
        return getCollection().updateOneById(User::id eq user.id, user).wasAcknowledged().toResult()
    }
}