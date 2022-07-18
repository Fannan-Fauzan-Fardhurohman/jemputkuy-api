package com.fanxan.jemputkuyapi

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Component

@Component
class DatabaseComponent {
    companion object {
        private const val DB_URL = "mongodb+srv://aej:1234@cluster0.msfnaox.mongodb.net/?retryWrites=true&w=majority"
    }

    //    private val DB_URL = System.getenv("DATABASE_URL")
    val database: MongoClient = KMongo.createClient(DB_URL)
}