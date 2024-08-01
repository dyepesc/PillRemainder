package com.example.pillremainder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pillremainder.models.Medication;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.AuthProvider;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import dmax.dialog.SpotsDialog;

public class Home extends AppCompatActivity
        implements View.OnClickListener{

    //firebase database-------------------------------------------------------
    AuthProvider mAuthProvider;
    Medication myMedication;
    AlertDialog mDialog;
    DatabaseReference mDatabase;
    FirebaseAuth myAuth;
    AlertDialog myDialog;
    //------------------------------------------------------------------------

    //Views--------------------------------------------------------------------
    TextView txtDay;
    TextView txtUserName;
    ImageButton imageButton;
    TextView txtPillName;
    TextView txtDose;
    TextView txtHour;
    Button btnSchedule;
    Button btnEdit;
    Button btnLogOut;
    //------------------------------------------------------------------------
    //Declare variables-------------------------------------------------------
    String medType;
    String pillName;
    String userName;
    String startTime;
    //------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtDay = findViewById(R.id.DayHome);
        txtUserName = findViewById(R.id.UserName);
        imageButton = findViewById(R.id.imagePill);
        txtPillName = findViewById(R.id.pillName);
        txtDose = findViewById(R.id.dose);
        txtHour = findViewById(R.id.hour);
        btnSchedule = findViewById(R.id.btnSchedule);
        btnEdit = findViewById(R.id.btnEdit);
        btnLogOut = findViewById(R.id.btnLogOut);

        //To print the day (ex: today is Monday)----------------------------------------------
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
        String strToday = today.format(formatter);

        txtDay.setText(strToday);
        //------------------------------------------------------------------------

        //database instance-------------------------------------------------------
        myAuth = FirebaseAuth.getInstance();                        //Instance of the database
        mDatabase = FirebaseDatabase.getInstance().getReference();  //principal node in the database
        //------------------------------------------------------------------------

        //request user med from database-------------------------------------------
        String id = myAuth.getCurrentUser().getUid();   //To save the medications in every user
        //------------------------------------------------------------------------

        //Add a listener for the node Medications in firebase-----------------------
        mDatabase.child("Users").child(id).child("Medications").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    medType = dataSnapshot.child("medType").getValue(String.class);
                    pillName = dataSnapshot.child("pillName").getValue(String.class);
                    startTime = dataSnapshot.child("startTime").getValue(String.class);

                    txtPillName.setText(pillName);
                    txtHour.setText(startTime);

                    if(medType != null && medType.equals("Pills")) {
                        imageButton.setImageResource(R.drawable.img_2);
                    }
                    else if(medType != null && medType.equals("Capsules")) {
                        imageButton.setImageResource(R.drawable.img_3);
                    }
                    else if(medType != null && medType.equals("Syrup")) {
                        imageButton.setImageResource(R.drawable.img_6);
                    }
                    else if(medType != null && medType.equals("Inhaler")) {
                        imageButton.setImageResource(R.drawable.img_5);
                    }
                }
            }
            //------------------------------------------------------------------------
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Home.this, "Database Error:", Toast.LENGTH_SHORT).show();
            }
        });
        //-----------------------------------------------------------------------

        //Add a listener for the node Users in firebase---------------------------------
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    userName = dataSnapshot.child("name").getValue(String.class);
                    txtUserName.setText(userName);
                }
            }
            //------------------------------------------------------------------------
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Home.this, "Database Error:", Toast.LENGTH_SHORT).show();
            }
        });
        //-----------------------------------------------------------------------
        //Set the listener--------------------------------------------------------
        btnSchedule.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        //------------------------------------------------------------------------
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btnSchedule) {
            goToSchedule();
        }
        else if(id == R.id.btnEdit) {
            goToAddMedications();
        }
        else if(id == R.id.btnLogOut) {
            LogOut();

        }


    }

    //Method to go to a different activity-------------------------------------
    private void goToSchedule() {
        android.app.AlertDialog myDialog;
        myDialog = new SpotsDialog.Builder().setContext(Home.this).setMessage("Please wait...").build();
        myDialog.show();
        Intent intent = new Intent(Home.this, Schedule_2.class);
        startActivity(intent);
    }
    //------------------------------------------------------------------------

    //Method to go to a different activity-------------------------------------
    private void goToAddMedications() {
        android.app.AlertDialog myDialog;
        myDialog = new SpotsDialog.Builder().setContext(Home.this).setMessage("Please wait...").build();
        myDialog.show();
        Intent intent = new Intent(Home.this, Start_12_AddMedication.class);
        startActivity(intent);
    }
    //------------------------------------------------------------------------

    //Method to ends Users session---------------------------------------------
    public void LogOut() {
        myAuth.signOut();
        finish();
        Intent intent = new Intent(Home.this, MainActivity.class);
        startActivity(intent);
    }

}