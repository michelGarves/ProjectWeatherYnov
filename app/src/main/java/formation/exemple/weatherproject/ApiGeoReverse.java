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

public class ApiGeoReverse {

    public static String getCityWithCoordinates(String lat, String lon, Context appContext){
        String[] cityName = new String[1];

        String url = "https://api-adresse.data.gouv.fr/reverse/?lon=" + lon + "&lat=" + lat;
        RequestQueue queue = Volley.newRequestQueue(appContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jObj = new JSONObject(response);
                            cityName[0] = jObj.getJSONArray("features")
                                    .getJSONObject(0)
                                    .getJSONObject("properties")
                                    .getString("city");
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

        return cityName[0];
    }
}
