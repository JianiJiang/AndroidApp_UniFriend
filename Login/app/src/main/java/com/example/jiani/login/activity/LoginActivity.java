package com.example.jiani.login.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jiani.login.R;

import java.util.HashMap;
import java.util.Map;

import entity.User;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {



    // UI references.
    private EditText etEmailView;
    private EditText etPasswordView;
    private Button btnSignIn;
    private TextView tvRegister;
    private RequestQueue myQueue;
    private int id;
    private String username;
    private String password;
    private String email;
    private String uuid;
    public static User user;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myQueue = Volley.newRequestQueue(LoginActivity. this);

        // Set up the login form.
        etEmailView = (EditText) findViewById(R.id.email);
        etPasswordView = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
        tvRegister = (TextView) findViewById(R.id.link_signup);


        btnSignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://213.89.22.179:8080/api/rest/user/login";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response",response);
                        user = JSON.parseObject(response, User.class);
                        id = user.getId();
                        password = user.getPassword();
                        email = user.getEmail();
                        uuid = user.getUuid();
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("password", password);
                        intent.putExtra("email" , email);
                        intent.putExtra("uuid", uuid);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(LoginActivity.this, "Wrong username or password!", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        User test = new User();
                        test.setEmail(etEmailView.getText().toString());
                        test.setPassword(etPasswordView.getText().toString());
                        return JSON.toJSONString(test).getBytes();
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/json");
                        return params;
                    }
                };
                myQueue.add(stringRequest);


            }


        });
        tvRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }


        });

    }













}

