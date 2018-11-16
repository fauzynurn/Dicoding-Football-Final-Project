package com.example.fauzy.footballmatchschedule.api

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.fauzy.footballmatchschedule.models.Favorite
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance =
                        DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            "TABLE_FAVORITE", true,
            Favorite.EVENT_ID to TEXT + UNIQUE + PRIMARY_KEY,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            "AWAY_TEAM_SCORE" to TEXT,
            "HOME_TEAM_SCORE" to TEXT,
            Favorite.EVENT_DATE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("TABLE_FAVORITE", true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)