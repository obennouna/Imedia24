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
                            "`imageUris` TEXT NOT NULL," +
                            "PRIMARY KEY(`sku`))"
                )
            }
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `ProductDetail`"+
                            "(`sku` INTEGER NOT NULL,"+
                            "`title` TEXT NOT NULL,"+
                            "`nameShort` TEXT NOT NULL,"+
                            "`longDescription` TEXT NOT NULL,"+
                            "`averageStars` REAL NOT NULL,"+
                            "`productPrice` TEXT NOT NULL,"+
                            "`imageUris` TEXT NOT NULL, " +
                            "`brandNameShort` TEXT NOT NULL," +
                            "`brandNameLong` TEXT NOT NULL," +
                            "`additionalInformation` TEXT NOT NULL," +
                            "`legalText` TEXT NOT NULL," +
                            "`deliveryText` TEXT NOT NULL," +
                            "PRIMARY KEY(`sku`))"
                )
            }
        }
    }
}