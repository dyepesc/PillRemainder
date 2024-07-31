package com.example.pillremainder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin1);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToSelectAuth();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToSignUp();
            }
        });
    }

    private void goToSignUp() {
        Intent intent = new Intent(MainActivity.this, Start_11_SignUp.class);
        startActivity(intent);
    }

    //this is the original

//    private void goToSelectAuth() {
//        Intent intent = new Intent(MainActivity.this, Start_20_Login.class);
//        startActivity(intent);
//    }

    //this is only for test, don forget delete (Backdoor)
    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, Start_12_AddMedication.class);
        startActivity(intent);
    }

}