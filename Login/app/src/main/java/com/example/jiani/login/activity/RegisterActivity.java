package com.example.jiani.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;
    private EditText etConfirmPass;
    private Button btnRegitser;
    private TextView tvLogin;
    private RequestQueue myQueue;
    private String password;
    private String comfirmPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myQueue = Volley.newRequestQueue(RegisterActivity. this);

        etName = (EditText) findViewById(R.id.et_rrgister_activity_input_email);
        etPassword = (EditText) findViewById(R.id.et_input_password);
        etConfirmPass = (EditText) findViewById(R.id.input_reEnterPassword);
        btnRegitser = (Button) findViewById(R.id.btn_signup);
        tvLogin = (TextView) findViewById(R.id.link_login);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }

        });
        btnRegitser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = etPassword.getText().toString();
                comfirmPass = etConfirmPass.getText().toString();
                if (password.equals(comfirmPass)){
                    String url ="http://213.89.22.179:8080/api/rest/user/register";
                    StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("response",response);
                            Toast.makeText(RegisterActivity.this, "Register sucessful! Go to your kth email to verify!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(RegisterActivity.this, "Register Failed! The user already exists or you should use a kth email!", Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            User register = new User();
                            register.setUsername(etName.getText().toString());
                            register.setEmail(etName.getText().toString());
                            register.setPassword(etPassword.getText().toString());
                            Log.i("JSON", JSON.toJSONString(register));
                            return JSON.toJSONString(register).getBytes();
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("Content-Type","application/json");
                            return params;
                        }
                    };
                    myQueue.add(stringRequest);
                }else{
                    Toast.makeText(RegisterActivity.this, "The password and comfirmed password don't match!", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}
