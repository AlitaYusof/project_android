package com.example.ttx.appmessagerme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mbuButtonLogin;
    TextView mTextViewRegister;
@Override
    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.fragment_login);

        mTextUsername.findViewById(R.id.textemail);
        mTextPassword.findViewById(R.id.textpass);
        mbuButtonLogin.findViewById(R.id.btn_login);
        mTextViewRegister.findViewById(R.id.text_regis);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity this,RegisterActivity.class startActivity(registerIntent))
            }
        });

    }
}