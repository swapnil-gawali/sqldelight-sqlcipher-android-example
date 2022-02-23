package com.example.sqldelight

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sqldelight.user.data.UserQueries
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create a factory and provide database password
        val factory = SupportFactory(SQLiteDatabase.getBytes("MyPassword".toCharArray()))

        // following implementation must be singleton
        val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, this, "test.db", factory)
        val database = Database(driver)

        val query: UserQueries = database.userQueries

        val userById = query.selectById(2)
        Log.wtf(TAG, "onCreate: $userById")

        val fetchAllUsers = query.selectAll()
        Log.wtf(TAG, "onCreate: $fetchAllUsers")
    }
}