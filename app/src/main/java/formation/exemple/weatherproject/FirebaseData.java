package formation.exemple.weatherproject;




import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseData {
   static private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public static ArrayList<String> getCities(){
        ArrayList<String> cities = new ArrayList<>();
        DatabaseReference refCities = mDatabase.getReference("Cities");
        refCities.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                   for(DataSnapshot citySnapshot : task.getResult().getChildren()){
                       City city = new City();
                       city.setName(citySnapshot.child("city").getValue().toString());
                       cities.add(city.getName());
                   }
                } else {
                    Log.d("ERR : ", task.getException().getMessage());
                }
            }
        });
        return cities;

    }
}

