package com.obennouna.imedia24.utils

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class MigrationDB {
    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `product`"+
                            " (`sku` INTEGER NOT NULL,"+
                            "`categoryId` INTEGER NOT NULL" +
                            "`nameShort` TEXT NOT NULL,"+
                            "`averageStars` INTEGER NOT NULL,"+
                            "`productPrice` TEXT NOT NULL,"+
                            "PRIMARY KEY(`sku`))"
                )
            }
        }
    }
}