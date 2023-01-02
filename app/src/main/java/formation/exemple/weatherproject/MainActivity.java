package formation.exemple.weatherproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static int MY_PERMISSIONS_LOCATION = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://geo.api.gouv.fr/communes";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jObj = new JSONObject(response);

                            JSONObject jObjCurrent = jObj.getJSONObject("nom");
                            String tmp = jObjCurrent.getString("tmp");
                            String condition = jObjCurrent.getString("condition");
                            String icon = jObjCurrent.getString("icon");

                            textView.setText("Condition :" + condition);
                            textView2.setText("Température :" + tmp);
                            ImageView imageView = findViewById(R.id.imageView);
                            Picasso.get().load(icon).into(imageView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }


    }
    public void onClick(View v) {

    };

    @Override
    protected void onDestroy() {
        EditText editText = findViewById(R.id.editText);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("savedCity", editText. );
        editor.commit();


        super.onDestroy();
    }
}