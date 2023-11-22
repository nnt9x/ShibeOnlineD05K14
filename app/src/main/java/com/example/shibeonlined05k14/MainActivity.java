package com.example.shibeonlined05k14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvAnimals;
    private List<Animal> dataSource;
    private AnimalAdapter animalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAnimals = findViewById(R.id.lvAnimals);
        // Tao du lieu - fake de kiem tra
        dataSource = new ArrayList<>();

        // Tao adapter
        animalAdapter = new AnimalAdapter(this, dataSource);

        // Set Adapter cho listview
        lvAnimals.setAdapter(animalAdapter);
    }

    public void randomImages(View view) {
        // Khi click vao button, ham nay se chay
        // Lay du lieu interntet -> thay the dataSource
        // Render listview

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://shibe.online/api/shibes?count=10";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Co du lieu
                dataSource.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Animal animal = new Animal("Shiba " + i, response.getString(i));
                        dataSource.add(animal);
                        // Thong bao cho adapter biet du lieu da thay doi
                        animalAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "API Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Thuc thi request
        queue.add(jsonArrayRequest);


    }
}