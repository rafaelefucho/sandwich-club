package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {




    public static Sandwich parseSandwichJson(String json) {
        final String TAG = "parseSandwichJson";

        Sandwich sandwich = null;
        JSONObject sandwichJsonObj = null;
        try {
            sandwichJsonObj = new JSONObject(json);
            String mainName = sandwichJsonObj.getJSONObject("name").get("mainName").toString();

            JSONArray alsoKnownasJsonArray = sandwichJsonObj.getJSONObject("name").getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = fromJsonArraytoStringList(alsoKnownasJsonArray) ;

            String placeOfOrigin = sandwichJsonObj.get("placeOfOrigin").toString();

            String description = sandwichJsonObj.get("description").toString();
            String image = sandwichJsonObj.get("image").toString();

            JSONArray ingredientsJsonArray = sandwichJsonObj.getJSONArray("ingredients");
            List<String> ingredients = fromJsonArraytoStringList(ingredientsJsonArray);


            sandwich = new Sandwich(
                    mainName,
                    alsoKnownAs,
                    placeOfOrigin,
                    description,
                    image,
                    ingredients
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sandwich;
    }

    private static List<String> fromJsonArraytoStringList(JSONArray jsonArray){
        // Taked from https://stackoverflow.com/questions/15871309/convert-jsonarray-to-string-array

        List<String> result = new ArrayList<String>();
        for(int i = 0; i < jsonArray.length(); i++){
            result.add(jsonArray.optString(i));
        }
        return result;
    }
}
