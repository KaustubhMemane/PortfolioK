package com.kmema.android.portfoliok;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, LoaderManager.LoaderCallbacks<String>{

    private static final String resumeLink = "http://kaustubhmemane.com/my_resume_overview";
    public static final String SEARCH_QUERY_URL_EXTRA = "query";
    private static final int RESUME_LOADER = 10;
    ResumeDataType[] resumeDataFromJSON;
    public SQLiteDatabase resumeDB;
    TextView textViewName;
    TextView textViewBirthdate;
    TextView textViewClass;
    TextView textViewMajor;
    TextView textViewCountry;
    TextView textViewAddress;
    TextView textViewStreet;
    TextView textViewCity;
    TextView textViewState;
    TextView textViewCountyStay;
    TextView textViewOverview;
    TextView textViewSkill;
    TextView textViewQualification;
    TextView textViewProgrammingLanguage;
    TextView textViewWeb;
    TextView textViewDatabase;
    TextView textViewOS;
    ImageView ImageViewProfileImage;
    TextView textViewVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewName = (TextView) findViewById(R.id.tv_name);
        textViewBirthdate = (TextView) findViewById(R.id.tv_Birthdate);
        textViewClass  = (TextView) findViewById(R.id.tv_class);
        textViewMajor= (TextView) findViewById(R.id.tv_major);
        textViewCountry = (TextView) findViewById(R.id.tv_country);
        textViewStreet = (TextView) findViewById(R.id.tv_street);
        textViewCity = (TextView) findViewById(R.id.tv_city);
        textViewState = (TextView) findViewById(R.id.tv_state);
        textViewCountyStay = (TextView) findViewById(R.id.tv_country1);
        textViewOverview = (TextView) findViewById(R.id.tv_overview);

        textViewSkill = (TextView) findViewById(R.id.tv_skill);
        textViewQualification = (TextView) findViewById(R.id.tv_qualification);
        textViewProgrammingLanguage = (TextView) findViewById(R.id.tv_programming_language);
        textViewWeb = (TextView) findViewById(R.id.tv_web_development);
        textViewDatabase= (TextView) findViewById(R.id.tv_database);
        textViewOS = (TextView) findViewById(R.id.tv_operatingSystem);
        ImageViewProfileImage =(ImageView)findViewById(R.id.profileImage);

        ResumeDataBaseHelper resumeHelper = new ResumeDataBaseHelper(this);
        resumeDB = resumeHelper.getWritableDatabase();
        requestingResume(resumeLink);
    }

    private void requestingResume(String link) {

        URL jsonResponse = ConnectingNetwork.buildUrl(link);

        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY_URL_EXTRA, jsonResponse.toString());

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> resumeLoader = loaderManager.getLoader(RESUME_LOADER);

        if(resumeLoader == null)
        {
            loaderManager.initLoader(RESUME_LOADER, bundle,this);
        }
        else
        {
            loaderManager.restartLoader(RESUME_LOADER, bundle,this);
        }

        //storeDataBase();
    }

    private void displayEveryThing(Cursor cursor) {

        final String[] name = new String[cursor.getCount()];
        int i = 0;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                name[i] = cursor.getString(cursor.getColumnIndex("resumeName"));

                textViewBirthdate.setText(cursor.getString(cursor.getColumnIndex("resumeBirthdate")));
                textViewClass.setText(cursor.getString(cursor.getColumnIndex("resumeClass")));
                textViewMajor.setText(cursor.getString(cursor.getColumnIndex("resumeMajor")));
                textViewCountry.setText(cursor.getString(cursor.getColumnIndex("resumeCountry")));
                textViewStreet.setText(cursor.getString(cursor.getColumnIndex("resumeStreet")));
                textViewCity.setText(cursor.getString(cursor.getColumnIndex("resumeCity")));
                textViewState.setText(cursor.getString(cursor.getColumnIndex("resumeState")));
                textViewCountyStay.setText(cursor.getString(cursor.getColumnIndex("resumeCountryStay")));
                textViewOverview.setText(cursor.getString(cursor.getColumnIndex("resumeOverview")));
                textViewSkill.setText(cursor.getString(cursor.getColumnIndex("resumeSkill")));
                textViewQualification.setText(cursor.getString(cursor.getColumnIndex("resumeQualification")));
                textViewProgrammingLanguage.setText(cursor.getString(cursor.getColumnIndex("resumeProgrammingLanguage")));
                textViewWeb.setText(cursor.getString(cursor.getColumnIndex("resumeWeb")));
                textViewDatabase.setText(cursor.getString(cursor.getColumnIndex("resumeDatabaseLanguage")));
                textViewOS.setText(cursor.getString(cursor.getColumnIndex("resumeOS")));

                String addr = cursor.getString(cursor.getColumnIndex("resumeProfileImage"));
                String imageAddress = "http://kaustubhmemane.com/"+addr;
                Picasso.with(this)
                        .load(imageAddress).fit()
                        .into(ImageViewProfileImage);
                Toast.makeText(this, imageAddress, Toast.LENGTH_LONG).show();

                cursor.moveToNext();
                i++;
            }
        }
        textViewName.setText(name[0]);
    }

    private Cursor loadAllDataForResume() {

Cursor cursor = resumeDB.query(ResumeDatabaseContract.ResumeDatabaseEntry.TABLE_NAME,

                new String[]{
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_NAME,
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_BIRTHDATE,
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_CLASS,
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_MAJOR,
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_COUNTRY,
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_STREET,
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_CITY,
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_STATE,
                ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_COUNTRY_STAY,
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
        ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_NAME
        );

        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        }
        return null;
    }


    private void storeDataBase(ResumeDataType[] myresumeDataFromJSON) {
        Boolean inserting = false;

        inserting = checkDuplicate(myresumeDataFromJSON[0].myName, myresumeDataFromJSON[0].mySkill,myresumeDataFromJSON[0].myProfileImage);

        if(inserting==true)
        {
            Toast.makeText(this, "ALREADY PRESENT", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Toast.makeText(this,"Update", Toast.LENGTH_SHORT).show();
            ContentValues cv = new ContentValues();
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_NAME, resumeDataFromJSON[0].myName);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_BIRTHDATE, resumeDataFromJSON[0].myBirthday);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_CLASS, resumeDataFromJSON[0].myClass);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_MAJOR, resumeDataFromJSON[0].myMajor);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_COUNTRY, resumeDataFromJSON[0].myCountry);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_STREET, resumeDataFromJSON[0].myStreet);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_CITY, resumeDataFromJSON[0].myCity);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_STATE, resumeDataFromJSON[0].myState);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_COUNTRY_STAY, resumeDataFromJSON[0].myCountry1);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_OVERVIEW, resumeDataFromJSON[0].myOveview);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_SKILL, resumeDataFromJSON[0].mySkill);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_QUALIFICATION,
                    resumeDataFromJSON[0].myQualification);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_PROGRAMMING_LANG,
                    resumeDataFromJSON[0].myProgrammingLanguage);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_WEB, resumeDataFromJSON[0].myWeb);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_DATABASE_LANG,
                    resumeDataFromJSON[0].myDatabase);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_OS, resumeDataFromJSON[0].myOs);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_PROFILE_IMAGE,
                    resumeDataFromJSON[0].myProfileImage);
            cv.put(ResumeDatabaseContract.ResumeDatabaseEntry.COLUMN_RESUME_VIDEOS, resumeDataFromJSON[0].myVideos);

            Uri uri = getContentResolver().insert(ResumeDatabaseContract.ResumeDatabaseEntry.CONTENT_URI, cv);

        }
    }

    public boolean checkDuplicate(String name, String skill, String profileImage) {
        Cursor cursor =resumeDB.query(
                        ResumeDatabaseContract.ResumeDatabaseEntry.TABLE_NAME,
                        null,
                        "resumeName = ? AND resumeSkill = ? AND resumeProfileImage = ?", new String[]{name,skill,profileImage},
                        null,
                        null,
                        null);

        if (cursor != null && cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {


        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(args == null)
                {
                    Toast.makeText(MainActivity.this, "Nothing in Search Query", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "args present", Toast.LENGTH_SHORT).show();
                forceLoad();
            }

            @Override
            public String loadInBackground() {

                String SearchQueryURLString = args.getString(SEARCH_QUERY_URL_EXTRA);
                if(SearchQueryURLString == null || TextUtils.isEmpty(SearchQueryURLString))
                {
                    return  null;
                }

                Context context = MainActivity.this;
                //URL searchURL = params[0];
                String jsonResponse;
                try {
                    URL searchURL = new URL(SearchQueryURLString);
                    final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


                    if (networkInfo != null && networkInfo.isConnected()) {
                        jsonResponse = ConnectingNetwork.getResponseFromHttpUrl(searchURL);

                        System.out.print(jsonResponse);
                        return jsonResponse;
                        //return JsonToSimple.jsonConvertString(MainActivity.this, jsonResponse);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
                return null;
            }
        };
    }
    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        if (data != null) {
            //gridView(s);
            try {
                resumeDataFromJSON = JsonToSimple.jsonConvertString(MainActivity.this,data);
                storeDataBase(resumeDataFromJSON);
                Cursor cursor =loadAllDataForResume();
                displayEveryThing(cursor);
                textViewOverview.setText(resumeDataFromJSON[0].myOveview);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this,"Data NULL", Toast.LENGTH_SHORT).show();
            //showErrorMessage();
        }
    }
    @Override
    public void onLoaderReset(Loader<String> loader) {
    }
}
