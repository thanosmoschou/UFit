package com.example.ufit.http_request;

import android.os.StrictMode;

import com.example.ufit.model.Exercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHandler
{
    private final String API_KEY = "YOUR-API-KEY";

    public HttpHandler()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public List<Exercise> getExercisesByType(String exerciseType)
    {
        String url = "https://api.api-ninjas.com/v1/exercises?type=" + exerciseType;
        List<Exercise> availableExercises = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("X-API-KEY", API_KEY)
                .build();

        Response response;
        try
        {
            response = client.newCall(request).execute();

            JSONArray jsonArray = new JSONArray(response.body().string());

            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject currentJsonObject = jsonArray.getJSONObject(i);

                String name = currentJsonObject.getString("name");
                String type = currentJsonObject.getString("type");
                String muscle = currentJsonObject.getString("muscle");
                String equipment = currentJsonObject.getString("equipment");
                String difficulty = currentJsonObject.getString("difficulty");
                String instructions = currentJsonObject.getString("instructions");

                availableExercises.add(new Exercise(name, type, muscle, equipment, difficulty, instructions));
            }

            //System.out.println(response.body().string());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (JSONException e)
        {
            throw new RuntimeException(e);
        }

        /*for(Exercise e : availableExercises)
            System.out.println("******\n" + e.getName() + " " + e.getType() + " " + e.getMuscle() + " " + e.getEquipment() + " " + e.getDifficulty() + " " + e.getInstructions());*/

        return availableExercises;
    }
}
