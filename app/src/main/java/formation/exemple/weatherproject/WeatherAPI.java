package formation.exemple.weatherproject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherAPI {
    public JSONObject[] getCityWeather(String city, Context appContext){
        String url = "https://www.prevision-meteo.ch/services/json/" + city;
        RequestQueue queue = Volley.newRequestQueue(appContext);
        final JSONObject[] returnObjArray = new JSONObject[5];
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jObj = new JSONObject(response);
                            returnObjArray[0] = jObj.getJSONObject("current_condition");
                            returnObjArray[1] = jObj.getJSONObject("fcst_day_1");
                            returnObjArray[2] = jObj.getJSONObject("fcst_day_2");
                            returnObjArray[3] = jObj.getJSONObject("fcst_day_3");
                            returnObjArray[4] = jObj.getJSONObject("fcst_day_4");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);
        return returnObjArray;
    }

    public String getDayShort(JSONObject obj) throws JSONException {
        return obj.getString("day_short");
    }
    public String getTmp(JSONObject obj) throws JSONException {
        return obj.getString("tmp");
    }
    public String getCondition(JSONObject obj) throws JSONException {
        return obj.getString("condition");
    }
    public String getIcon(JSONObject obj) throws JSONException {
        return obj.getString("icon");
    }



}
