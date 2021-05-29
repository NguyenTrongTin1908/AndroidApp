package com.example.webservice;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AddsvActivity extends AppCompatActivity {



    private String urlInsert = "http://192.168.1.103:8080/androidWebservice/insert.php";
    EditText txtHoten , txtNamSinh , txtDiaChi;
    Button btnHuy_add ,btnThem_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsv);
        AnhXa();


        btnThem_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ThemSinhVien(urlInsert);


            }
        });

        btnHuy_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    private void ThemSinhVien(String url){


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.trim().equals("success")){
                            Toast.makeText(AddsvActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddsvActivity.this,MainActivity.class));
                        }else {
                            Toast.makeText(AddsvActivity.this, "LoiThem", Toast.LENGTH_SHORT).show();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddsvActivity.this, "Xay ra loi", Toast.LENGTH_SHORT).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();

                params.put("hotenSV",txtHoten.getText().toString().trim());
                params.put("namsinhSV",txtNamSinh.getText().toString().trim());
                params.put("diachiSV",txtDiaChi.getText().toString().trim());


                return params;
            }
        };
        requestQueue.add(stringRequest);


    }


    private void AnhXa(){
        txtHoten  = (EditText) findViewById(R.id.edtHoTen);
        txtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        txtNamSinh=(EditText)   findViewById(R.id.edtNamSinh);
        btnHuy_add = (Button)      findViewById(R.id.buttonHuy_add);
        btnThem_add = (Button)      findViewById(R.id.buttonThem_add);


    }
}
