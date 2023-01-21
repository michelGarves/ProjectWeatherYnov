package formation.exemple.weatherproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 413;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_locate);



        Spinner spinnerCity = findViewById(R.id.spinnerCityHome);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cities));
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);


        spinnerCity.setAdapter(adapter);


        Button confirmCity = findViewById(R.id.confirmCity);
        Button useLocation = findViewById(R.id.useLocation);
        useLocation.setOnClickListener(this);
        confirmCity.setOnClickListener(this);
        spinnerCity.setOnItemSelectedListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmCity:
                Spinner spinner = findViewById(R.id.spinnerCityHome);
                String selectedCity = spinner.getSelectedItem().toString();

                Intent intentWithSpinner = new Intent(this, WeatherActivity.class);
                intentWithSpinner.putExtra("selectedCity", selectedCity);
                this.startActivity(intentWithSpinner);

                break;
            case R.id.useLocation:
                Intent intentWithLocation = new Intent(this, WeatherActivity.class);
                /*
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)  {
                    Toast.makeText(this, "Donnez d'abord l'autorisation à l'application d'accéder à votre localisation.", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION},
                            LOCATION_PERMISSION_REQUEST_CODE);
                } else {
                    Intent intentWithLocation = new Intent(this, WeatherActivity.class);
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (lastKnownLocation != null) {

                            String city = ApiGeoReverse.getCityWithCoordinates(Double.toString(lastKnownLocation.getLatitude()),Double.toString(lastKnownLocation.getLongitude()),this);
                            intentWithLocation.putExtra("selectedCity", city);
                        Log.i("Longitude : ", String.format ("%.3f", lastKnownLocation.getLongitude()));
                        Log.i("Latitude : ",String.format ("%.3f", lastKnownLocation.getLatitude()));

                    } else {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {

                                    String city = ApiGeoReverse.getCityWithCoordinates(Double.toString(location.getLatitude()),Double.toString(location.getLongitude()),MainActivity.this);
                                    intentWithLocation.putExtra("selectedCity", city);

                                Log.i("Longitude : ", String.format ("%.3f", location.getLongitude()));
                                Log.i("Latitude : ",String.format ("%.3f", location.getLatitude()));
                                locationManager.removeUpdates(this);
                            }

                        });
                    }
                    */
                this.startActivity(intentWithLocation);
                    break;
                }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("onItemSelected", "engaged");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i("onNothingSelected", "engaged");
    }
}

