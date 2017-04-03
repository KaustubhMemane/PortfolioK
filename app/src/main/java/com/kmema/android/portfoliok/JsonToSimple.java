package com.kmema.android.portfoliok;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kmema on 3/27/2017.
 */

public class JsonToSimple {

    static ResumeDataType[] jsonConvertString(Context context, String ResumeJsonString) throws JSONException {
        ResumeDataType[] parsedResumeData = new ResumeDataType[1];
        String name;
        String birthdate;
        String myclass;
        String major;
        String country;
        String street;
        String city;
        String state;
        String country1;
        String overview;
        String skill;
        String qualification;
        String databaseLang="Database: ";
        String os="Operating System: ";
        String videos="Video: ";
        String programmingLang = "Programming Language: ";
        String webLang= "Web Services / Web Development: ";
        String profileImageLink;


        JSONObject resumeDBJson = new JSONObject(ResumeJsonString);
        name = resumeDBJson.getString("name");
        birthdate = resumeDBJson.getString("birthdate");
        myclass = resumeDBJson.getString("class");
        major = resumeDBJson.getString("major");
        country = resumeDBJson.getString("country");

        JSONObject resumeAddressDBJSON = resumeDBJson.getJSONObject("address");
        street = resumeAddressDBJSON.getString("street");
        city = resumeAddressDBJSON.getString("city");
        state = resumeAddressDBJSON.getString("state");
        country1 = resumeAddressDBJSON.getString("Country");

        overview = resumeDBJson.getString("overview");
        skill = resumeDBJson.getString("skill");
        qualification = resumeDBJson.getString("qualification");


        JSONArray programmingLanguage = resumeDBJson.getJSONArray("programming_language");
        for(int i = 0;i<programmingLanguage.length();i++)
        {
//            programmingLang =  new StringBuilder(",").append(programmingLanguage.getString(i)).toString();
            programmingLang = programmingLang.concat(" ").concat(programmingLanguage.getString(i));
        }


        JSONArray webArray = resumeDBJson.getJSONArray("Web_Development_Web_Services");
        for(int i = 0;i<webArray.length();i++)
        {
            webLang = webLang.concat(" ").concat(webArray.getString(i));
        }

        JSONArray Database_SystemArray = resumeDBJson.getJSONArray("Database_System");
        for(int i = 0;i<Database_SystemArray.length();i++)
        {
            databaseLang = databaseLang.concat(" ").concat(Database_SystemArray.getString(i));
        }

        JSONArray OSArray = resumeDBJson.getJSONArray("Operating_System_And_Tools");
        for(int i = 0;i<OSArray.length();i++)
        {
            os = os.concat(" ").concat(OSArray.getString(i));
        }

        profileImageLink = resumeDBJson.getString("profile_image");

        JSONArray VideosArray = resumeDBJson.getJSONArray("videos");
        for(int i = 0;i<VideosArray.length();i++)
        {
            videos = videos.concat("/").concat(VideosArray.getString(i));
        }

        parsedResumeData[0] = new ResumeDataType(name,birthdate,myclass,major,country,street,
                city,state,country1,overview,skill, qualification,programmingLang,webLang,
                databaseLang,os,profileImageLink,videos);

        return parsedResumeData;
    }
}
