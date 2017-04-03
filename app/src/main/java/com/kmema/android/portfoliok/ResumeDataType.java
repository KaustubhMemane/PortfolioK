package com.kmema.android.portfoliok;

/**
 * Created by kmema on 3/27/2017.
 */

public class ResumeDataType {

    String myName;
    String myBirthday;
    String myClass;
    String myMajor;
    String myCountry;
    String myStreet;
    String myCity;
    String myState;
    String myCountry1;
    String myOveview;
    String mySkill;
    String myQualification;
    String myProgrammingLanguage;
    String myWeb;
    String myDatabase;
    String myOs;
    String myProfileImage;
    String myVideos;

    String id;
    ResumeDataType(String MName,String MBirthday, String MClass, String MMajor,
            String MCountry, String MStreet, String MCity, String MState, String MCountry1, String
             MOverview, String MSkill, String MQualification, String MProgrammingLanguage, String MWeb,
             String MDatanase, String MOs, String MProfileImage, String MVideo)
    {
        this.myName = MName;
        this.myBirthday = MBirthday;
        this.myClass = MClass;
        this.myMajor = MMajor;
        this.myCountry = MCountry;
        this.myStreet = MStreet;
        this.myCity = MCity;
        this.myState = MState;
        this.myCountry1 = MCountry1;
        this.myOveview = MOverview;
        this.mySkill = MSkill;
        this.myQualification = MQualification;
        this.myProgrammingLanguage = MProgrammingLanguage;
        this.myWeb = MWeb;
        this.myDatabase = MDatanase;
        this.myOs = MOs;
        this.myProfileImage = MProfileImage;
        this.myVideos = MVideo;
    }
}
