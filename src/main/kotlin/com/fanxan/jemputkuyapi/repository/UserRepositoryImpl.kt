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
        return if(existingUser.isSuccess){
            throw JemputkuyException("User Exists")
        }else{
            getCollection().insertOne(user).wasAcknowledged().toResult()
        }
    }

    override fun getUserById(id: String): Result<User> {
        return getCollection().findOne(User::id eq id).toResult("User with Id $id not found")
    }

    override fun getUserByUsername(username: String): Result<User> {
        return getCollection().findOne(User::username eq username).toResult("User with Username $username not found!")
    }
//
//    override fun getUserByRoles(roles: String): Result<User> {
//        return getCollection().findOne(User::roles eq roles).toResult()
//    }

}