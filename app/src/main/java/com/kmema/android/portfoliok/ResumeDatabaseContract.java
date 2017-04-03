package com.kmema.android.portfoliok;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by kmema on 3/27/2017.
 */

public class ResumeDatabaseContract {
    static  final String AUTHORITY = "com.kmema.android.portfoliok";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ AUTHORITY);
    static final String PATH_TO_TABLE_MOVIE = "resumeList";
    static final class ResumeDatabaseEntry implements BaseColumns
    {
        public static  final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TO_TABLE_MOVIE).build();
        static final String TABLE_NAME = "resumeList";
        static final String COLUMN_RESUME_NAME = "resumeName";
        static final String COLUMN_RESUME_CLASS = "resumeClass";
        static final String COLUMN_RESUME_BIRTHDATE = "resumeBirthdate";
        static final String COLUMN_RESUME_MAJOR = "resumeMajor";
        static final String COLUMN_RESUME_COUNTRY = "resumeCountry";
        static final String COLUMN_RESUME_STREET = "resumeStreet";
        static final String COLUMN_RESUME_CITY = "resumeCity";
        static final String COLUMN_RESUME_STATE = "resumeState";
        static final String COLUMN_RESUME_COUNTRY_STAY = "resumeCountryStay";
        static final String COLUMN_RESUME_OVERVIEW = "resumeOverview";
        static final String COLUMN_RESUME_SKILL = "resumeSkill";
        static final String COLUMN_RESUME_QUALIFICATION = "resumeQualification";
        static final String COLUMN_RESUME_PROGRAMMING_LANG = "resumeProgrammingLanguage";
        static final String COLUMN_RESUME_WEB = "resumeWeb";
        static final String COLUMN_RESUME_DATABASE_LANG = "resumeDatabaseLanguage";
        static final String COLUMN_RESUME_OS = "resumeOS";
        static final String COLUMN_RESUME_PROFILE_IMAGE = "resumeProfileImage";
        static final String COLUMN_RESUME_VIDEOS = "resumeVideos";
    }
}
