package com.example.jiani.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jiani.login.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;
    private EditText etConfirmPass;
    private Button btnRegitser;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


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
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }

        });
    }
}
