package formation.exemple.weatherproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class WeatherActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meteo);



        Intent intent = this.getIntent();
        String selectedCity  = intent.getStringExtra("selectedCity");

        TextView txtCity = findViewById(R.id.city);

        txtCity.setText(selectedCity);

        String url = "https://www.prevision-meteo.ch/services/json/" + selectedCity;


        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jObj = new JSONObject(response);

                            JSONObject objCur = jObj.getJSONObject("current_condition");
                            JSONObject objDay1 = jObj.getJSONObject("fcst_day_1");
                            JSONObject objDay2 = jObj.getJSONObject("fcst_day_2");
                            JSONObject objDay3 = jObj.getJSONObject("fcst_day_3");
                            JSONObject objDay4 = jObj.getJSONObject("fcst_day_4");

                            String icon = objCur.getString("icon_big");
                            String tmp = objCur.getString("tmp");
                            String condition = objCur.getString("condition");

                            TextView tvTmp = findViewById(R.id.actualTmp);
                            TextView tvCondition = findViewById(R.id.actualCondition);
                            ImageView imgIconToday = findViewById(R.id.actualWeatherIcon);


                            Picasso.get().load(icon).into(imgIconToday);
                            tvTmp.setText(tmp + "°C");
                            tvCondition.setText(condition);
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



    }
}



