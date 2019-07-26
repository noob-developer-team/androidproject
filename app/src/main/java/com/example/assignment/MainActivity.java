package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Controller.RetrofitController;
import com.example.assignment.Controller.RetrofitInstance;
import com.example.assignment.Model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static int userId;
    private List<User> addUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TextView textSignUp;
        final CheckBox showPassword;
        final EditText textUsername, textPassword;
        final Button buttonLogin;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSignUp = findViewById(R.id.txtsignup);
        showPassword = findViewById(R.id.checkboxShowPass);
        buttonLogin = findViewById(R.id.buttonLogin);
        textUsername = findViewById(R.id.txtUsername);
        textPassword= findViewById(R.id.txtPassword);


        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,studentSignupPage.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllUser(textUsername.getText().toString(), textPassword.getText().toString());
                if(!addUser.isEmpty())
                {
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    textPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else {
                    textPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    private void getAllUser(String username, String password){
        RetrofitController retrofit = RetrofitInstance.getService();

        Call<List<User>> call = retrofit.getUser(username, password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@Nullable Call<List<User>> call,@NonNull Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<User> users = response.body();
                assert users != null;
                for(User user: users){
                    addUser = new ArrayList<>();
                    addUser.add(user);
                    userId  = user.getId();
                }
            }

            @Override
            public void onFailure(@Nullable Call<List<User>> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
