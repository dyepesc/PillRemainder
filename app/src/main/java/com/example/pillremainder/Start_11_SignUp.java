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

    //declare variables-------------------------------------------------------
    Toolbar toolbar_SignUp;     // this is the navBar in the top of the page
    FirebaseAuth myAuth;  //to send user info to firebase database
    DatabaseReference myDatabase;

    //Views-------------------------------------------------------------------
    Button myButonSignUp;
    TextInputEditText myTextInputName;
    TextInputEditText myTextInputLastName;
    TextInputEditText myTextInputDate;
    TextInputEditText myTextInputEmail;
    TextInputEditText myTextInputPass;

    AlertDialog myDialog;
    //------------------------------------------------------------------------
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
        final String name = myTextInputName.getText().toString();
        final String lastName = myTextInputLastName.getText().toString();
        final String date = myTextInputDate.getText().toString();
        final String email = myTextInputEmail.getText().toString();
        final String password = myTextInputPass.getText().toString();

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
                            saveUser(id, name, lastName,email);
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

    private void saveUser(String id, String name, String lastName, String email) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        myDatabase.child("Users").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(Start_11_SignUp.this, "User saved", Toast.LENGTH_SHORT).show();
                    goToAddMedication();
                }
                else {
                    Toast.makeText(Start_11_SignUp.this, "User can not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToAddMedication() {
        Intent intent = new Intent(Start_11_SignUp.this, Start_12_AddMedication.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}