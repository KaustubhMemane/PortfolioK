package com.kmema.android.portfoliok;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ResumeDataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_FILE_NAME = "resumeList.db";
    public static final int DATABASE_VERSION_NUMBER = 3;

    ResumeDataBaseHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_RESUME_TABLE = "CREATE TABLE " +
                ResumeDatabaseContract.ResumeDatabaseEntry.TABLE_NAME + " (" +
                ResumeDatabaseContract.ResumeDatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_NAME + " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_BIRTHDATE + " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_CLASS + " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_MAJOR + " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_COUNTRY + " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_STREET + " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_CITY + " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_STATE + " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_COUNTRY_STAY + " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_OVERVIEW+ " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_SKILL+ " TEXT NOT NULL," +
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_QUALIFICATION + " TEXT NOT NULL,"+
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_PROGRAMMING_LANG + " TEXT NOT NULL,"+
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_WEB + " TEXT NOT NULL,"+
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_DATABASE_LANG + " TEXT NOT NULL,"+
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_OS + " TEXT NOT NULL,"+
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_PROFILE_IMAGE + " TEXT NOT NULL,"+
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_VIDEOS + " TEXT NOT NULL"+
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_RESUME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ResumeDatabaseContract.ResumeDatabaseEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
