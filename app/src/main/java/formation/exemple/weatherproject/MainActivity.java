package formation.exemple.weatherproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ArrayList<String> cityNames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_locate);
       /*switch(checkPermissionLocation()){
            case 1 :
                setContentView(R.layout.meteo);
                break;

            case 0 :
                setContentView(R.layout.no_locate);
                break;

            default:

        }*/

        cityNames = FirebaseData.getCities();


        Spinner spinner = findViewById(R.id.spinnerCityHome);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityNames);


        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(this);
        Button confirmCity = findViewById(R.id.confirmCity);
        confirmCity.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        Spinner spinner = findViewById(R.id.spinnerCity);
        String city = spinner.getSelectedItem().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("savedCity", city );
        editor.commit();


        super.onDestroy();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedCity = parent.getItemAtPosition(3).toString();
        Toast.makeText(MainActivity.this, selectedCity, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.confirmCity:
                /*Spinner spinner = findViewById(R.id.spinnerCityHome);
                String selectedCity = spinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, selectedCity , Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Button clicked" , Toast.LENGTH_SHORT).show();
                */
                Log.i("City 1", cityNames.get(1));
                break;
        }
    }

    public int checkPermissionLocation(){
        int result = -1;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    public void askPermission(){

    }


}

