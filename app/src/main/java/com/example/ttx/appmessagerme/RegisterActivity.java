package com.example.ttx.appmessagerme;

import android.app.Activity;
import android.os.Bundle;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTextUsername.findViewById(R.id.textemail);
        mTextPassword.findViewById(R.id.textpass);
        mbuButtonLogin.findViewById(R.id.btn_login);
        mTextViewRegister.findViewById(R.id.text_regis);
    }
}
