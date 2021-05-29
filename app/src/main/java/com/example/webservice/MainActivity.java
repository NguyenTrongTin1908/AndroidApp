package com.example.webservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView lvSinhVien;
    ArrayList<SinhVien> arraySinhVien;
    SinhVienAdapter adapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSinhVien = (ListView) findViewById(R.id.listSinhVien);
        arraySinhVien = new ArrayList<>();

        adapter = new SinhVienAdapter(this ,R.layout.dong_sinh_vien,arraySinhVien);
        lvSinhVien.setAdapter(adapter);

        ReadJSON("https://oginal.000webhostapp.com/getdata.php");



    }

    private void ReadJSON(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0;i<response.length();i++){
                            try {
                                JSONObject object =response.getJSONObject(i);
                                Toast.makeText(MainActivity.this, "CC"+object, Toast.LENGTH_SHORT).show();
                                arraySinhVien.add(new SinhVien(
                                        object.getInt("ID"),
                                        object.getString("HoTen"),
                                        object.getInt("NamSinh"),
                                        object.getString("DiaChi")

                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        Toast.makeText(MainActivity.this, ""+arraySinhVien, Toast.LENGTH_SHORT).show();

                        adapter.notifyDataSetChanged();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonArrayRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_sinhvien,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menuAddStudent){
            startActivity(new Intent(MainActivity.this, AddsvActivity.class));
//            Toast.makeText(this, "rA CC", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
