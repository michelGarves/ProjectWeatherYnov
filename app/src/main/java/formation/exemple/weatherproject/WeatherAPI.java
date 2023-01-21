package formation.exemple.weatherproject;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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
    static Context context;
    EditText editText;
    TextView textView;
    TextView textView2;



    public static String getDayShort(JSONObject obj) throws JSONException {
        return obj.getString("day_short");
    }
    public static String getTmp(JSONObject obj) throws JSONException {
        return obj.getString("tmp");
    }
    public static String getCondition(JSONObject obj) throws JSONException {
        return obj.getString("condition");
    }
    public static String getIcon(JSONObject obj) throws JSONException {
        return obj.getString("icon");
    }



}
