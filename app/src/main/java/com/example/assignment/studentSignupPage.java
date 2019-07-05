package com.example.assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class studentSignupPage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final EditText txtname,txtpassword,txtemail,txtpid;
        final Button btnsignup;
        final Button btnback;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_signup_page);
        txtname = findViewById(R.id.txt_name);
        txtpassword = findViewById(R.id.txt_password);
        txtemail = findViewById(R.id.txt_email);
        txtpid = findViewById(R.id.txt_phonenumber);
        btnsignup = findViewById(R.id.buttonsignup);
        btnback = findViewById(R.id.buttonback);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.43.60:9090/androidToMySQL/insert.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("failed"))
                        {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<>();
                        params.put("username", txtname.getText().toString());
                        params.put("email", txtemail.getText().toString());
                        params.put("password", txtpassword.getText().toString());
                        params.put("pid", txtpid.getText().toString());
                        return params;
                    }
                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
            }


}
