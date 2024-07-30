package com.example.pillremainder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pillremainder.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import dmax.dialog.SpotsDialog;

public class Start_11_SignUp extends AppCompatActivity {

    Toolbar toolbar_SignUp;     // this is the navBar in the top of the page
    FirebaseAuth myAuth;  //to send user info to firebase database
    DatabaseReference myDatabase;

    //Views
    Button myButonSignUp;
    TextInputEditText myTextInputName;
    TextInputEditText myTextInputLastName;
    TextInputEditText myTextInputDate;
    TextInputEditText myTextInputEmail;
    TextInputEditText myTextInputPass;

    AlertDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start11_sign_up);

        toolbar_SignUp = findViewById(R.id.toolbarSignUp);
        setSupportActionBar(toolbar_SignUp);
        getSupportActionBar().setTitle("Hello!");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myButonSignUp = findViewById(R.id.btnSignUp2);

        myAuth = FirebaseAuth.getInstance();

        myDatabase = FirebaseDatabase.getInstance().getReference();  //principal node in the database

        myDialog = new SpotsDialog.Builder().setContext(Start_11_SignUp.this).setMessage("Please wait...").build();

        myTextInputName = findViewById(R.id.textInputName);
        myTextInputLastName = findViewById(R.id.textInputLastName);
        myTextInputDate = findViewById(R.id.textInputDate);
        myTextInputEmail = findViewById(R.id.textInputEmail2);
        myTextInputPass = findViewById(R.id.textInputPass2);

        myButonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String name = myTextInputName.getText().toString();
        String lastName = myTextInputLastName.getText().toString();
        String date = myTextInputDate.getText().toString();
        String email = myTextInputEmail.getText().toString();
        String password = myTextInputPass.getText().toString();

        if(!name.isEmpty() && !lastName.isEmpty() && !date.isEmpty() && !email.isEmpty() && !password.isEmpty())
        {
            if (password.length() >= 6)
            {
                myDialog.show();
                myAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        myDialog.hide();
                        if (task.isSuccessful())
                        {
                            String id = myAuth.getCurrentUser().getUid();
                            saveUser(id, name, email);
                        }
                        else {
                            Toast.makeText(Start_11_SignUp.this, "The user can not register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                Toast.makeText(Start_11_SignUp.this, "Password needs 6 characters", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(Start_11_SignUp.this, "All data fields are required", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUser(String id, String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        myDatabase.child("Users").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(Start_11_SignUp.this, "User saved", Toast.LENGTH_SHORT).show();
                    goToSelectAuth();
                }
                else {
                    Toast.makeText(Start_11_SignUp.this, "User can not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(Start_11_SignUp.this, Start_12_AddMedication.class);
        startActivity(intent);
    }
}