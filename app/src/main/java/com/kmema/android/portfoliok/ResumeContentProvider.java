package com.kmema.android.portfoliok;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by kmema on 3/27/2017.
 */

public class ResumeContentProvider extends ContentProvider{

    public  static final int RESUMES = 100;
    public static final int RESUME_WITH_ID = 101;

    private ResumeDataBaseHelper mRESUMEDBHelper;
    private static final UriMatcher sUriMatcher = buildURIMatcher();

    public static UriMatcher buildURIMatcher()
    {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ResumeDatabaseContract.AUTHORITY, ResumeDatabaseContract.PATH_TO_TABLE_MOVIE, RESUMES);
        uriMatcher.addURI(ResumeDatabaseContract.AUTHORITY, ResumeDatabaseContract.PATH_TO_TABLE_MOVIE + "/#", RESUME_WITH_ID);
        return  uriMatcher;
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        mRESUMEDBHelper = new ResumeDataBaseHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase sqLiteDatabase = mRESUMEDBHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor returnCursor;
        switch(match) {
            case RESUMES:
                returnCursor = sqLiteDatabase.query(ResumeDatabaseContract.ResumeDatabaseEntry.TABLE_NAME,
                        new String[]{
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_NAME,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_BIRTHDATE,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_CLASS,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_MAJOR,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_COUNTRY,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_STREET,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_CITY,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_STATE,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_COUNTRY,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_OVERVIEW,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_SKILL,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_QUALIFICATION,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_PROGRAMMING_LANG,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_WEB,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_DATABASE_LANG,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_OS,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_PROFILE_IMAGE,
                                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_VIDEOS
                        },
                        null,
                        null,
                        null,
                        null,
                        ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_BIRTHDATE
                );
                break;

            default:
                throw new UnsupportedOperationException("Unknown URI ::" + uri);
        }
        returnCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final SQLiteDatabase sqLiteDatabase = mRESUMEDBHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match)
        {
            case RESUMES:
                long id = sqLiteDatabase.insert(ResumeDatabaseContract.ResumeDatabaseEntry.TABLE_NAME,null,values);
                if(id > 0)
                {
                    returnUri = ContentUris.withAppendedId(ResumeDatabaseContract.ResumeDatabaseEntry.CONTENT_URI,id);
                }
                else
                {
                    throw new android.database.SQLException("Failed To insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI"+ uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
