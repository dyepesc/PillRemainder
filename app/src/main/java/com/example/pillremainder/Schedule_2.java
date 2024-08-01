package com.example.pillremainder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pillremainder.R;
import com.example.pillremainder.models.Medication;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.AuthProvider;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Schedule_2 extends AppCompatActivity
        implements View.OnClickListener {

    //firebase database-------------------------------------------------------
    AuthProvider mAuthProvider;
    Medication myMedication;
    AlertDialog mDialog;
    DatabaseReference mDatabase;
    FirebaseAuth myAuth;
    AlertDialog myDialog;
    //------------------------------------------------------------------------

    //declare variables-------------------------------------------------------
    ImageButton imageButton;
    TextView txtPillName;
//    TextView txtDose;
    TextView txtDose2;
    TextView txtDose3;
    TextView txtDose4;
    TextView txtDose5;
    TextView txtDose6;
    TextView txtHour;
    TextView txtHour2;
    TextView txtHour3;
    TextView txtHour4;
    TextView txtHour5;
    TextView txtHour6;
    Switch txtSwitch;
    Button btnDone;
    String medType;
    //------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule2);

        //Objects-----------------------------------------------------------------
        imageButton = findViewById(R.id.imagePill);
        txtPillName = findViewById(R.id.pillName);

        txtDose2 = findViewById(R.id.pillSchedule2);
        txtDose3 = findViewById(R.id.pillSchedule3);
        txtDose4 = findViewById(R.id.pillSchedule4);
        txtDose5 = findViewById(R.id.pillSchedule5);
        txtDose6 = findViewById(R.id.pillSchedule6);

        txtHour = findViewById(R.id.hour);
        txtHour2 = findViewById(R.id.hour2);
        txtHour3 = findViewById(R.id.hour3);
        txtHour4 = findViewById(R.id.hour4);
        txtHour5 = findViewById(R.id.hour5);
        txtHour6 = findViewById(R.id.hour6);

        txtSwitch = findViewById(R.id.mySwitch);
        btnDone = findViewById(R.id.btnDone);
        //------------------------------------------------------------------------

        //database instance-------------------------------------------------------
        myAuth = FirebaseAuth.getInstance();                        //Instance of the database
        mDatabase = FirebaseDatabase.getInstance().getReference();  //principal node in the database
        //------------------------------------------------------------------------

        //request user med from database-------------------------------------------
        String id = myAuth.getCurrentUser().getUid();   //To save the medications in every user

        //Add a listener------------------------------------------------------------
        mDatabase.child("Users").child(id).child("Medications").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    medType = dataSnapshot.child("medType").getValue(String.class);
                    String pillName = dataSnapshot.child("pillName").getValue(String.class);
                    String frequency = dataSnapshot.child("frequency").getValue(String.class);
                    String startTime = dataSnapshot.child("startTime").getValue(String.class);

                    int intFrequency;       //for store the frequency that comes from firebase
                    String newTimeDose;     //for store the new hour in string
                    String newTimeDose2;     //for store the new hour in string
                    String newTimeDose3;     //for store the new hour in string
                    String newTimeDose4;     //for store the new hour in string
                    String newTimeDose5;     //for store the new hour in string
                    String newTimeDose6;     //for store the new hour in string

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

                    txtPillName.setText(pillName);
                    txtHour.setText(startTime);

        //For work with hours------------------------------------------------------------
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a"); //to convert the string hour (startTime into hour
                    LocalTime time = LocalTime.parse(startTime, formatter);


                    if(frequency != null && frequency.equals("4")) {
                        intFrequency = 4;
                        time = time.plus(intFrequency, ChronoUnit.HOURS);       //add the frequency time to the hours
                        newTimeDose = time.format(formatter);
                        txtHour2.setText(newTimeDose);
                        LocalTime time2 = LocalTime.parse(newTimeDose, formatter);
                        time2 = time2.plus(intFrequency, ChronoUnit.HOURS);
                        newTimeDose2 = time2.format(formatter);
                        txtHour3.setText(newTimeDose2);
                        LocalTime time3 = LocalTime.parse(newTimeDose2, formatter);
                        time3 = time3.plus(intFrequency, ChronoUnit.HOURS);
                        newTimeDose3 = time3.format(formatter);
                        txtHour4.setText(newTimeDose3);
                        LocalTime time4 = LocalTime.parse(newTimeDose3, formatter);
                        time4 = time4.plus(intFrequency, ChronoUnit.HOURS);
                        newTimeDose4 = time4.format(formatter);
                        txtHour5.setText(newTimeDose4);
                        LocalTime time5 = LocalTime.parse(newTimeDose4, formatter);
                        time5 = time5.plus(intFrequency, ChronoUnit.HOURS);
                        newTimeDose5 = time5.format(formatter);
                        txtHour6.setText(newTimeDose5);
                    }
                    if(frequency != null && frequency.equals("6")) {
                        intFrequency = 6;
                        time = time.plus(intFrequency, ChronoUnit.HOURS);       //add the frequency time to the hours
                        newTimeDose = time.format(formatter);
                        txtHour2.setText(newTimeDose);
                        LocalTime time2 = LocalTime.parse(newTimeDose, formatter);
                        time2 = time2.plus(intFrequency, ChronoUnit.HOURS);
                        newTimeDose2 = time2.format(formatter);
                        txtHour3.setText(newTimeDose2);
                        LocalTime time3 = LocalTime.parse(newTimeDose2, formatter);
                        time3 = time3.plus(intFrequency, ChronoUnit.HOURS);
                        newTimeDose3 = time3.format(formatter);
                        txtHour4.setText(newTimeDose3);
                        txtHour5.setText("");
                        txtHour6.setText("");
                        txtDose5.setText("");
                        txtDose6.setText("");
                    }
                    if(frequency != null && frequency.equals("8")) {
                        intFrequency = 8;
                        time = time.plus(intFrequency, ChronoUnit.HOURS);       //add the frequency time to the hours
                        newTimeDose = time.format(formatter);
                        txtHour2.setText(newTimeDose);
                        LocalTime time2 = LocalTime.parse(newTimeDose, formatter);
                        time2 = time2.plus(intFrequency, ChronoUnit.HOURS);
                        newTimeDose2 = time2.format(formatter);
                        txtHour3.setText(newTimeDose2);
                        txtHour4.setText("");
                        txtHour5.setText("");
                        txtHour6.setText("");
                        txtDose4.setText("");
                        txtDose5.setText("");
                        txtDose6.setText("");
                    }
                    if(frequency != null && frequency.equals("12")) {
                        intFrequency = 12;
                        time = time.plus(intFrequency, ChronoUnit.HOURS);       //add the frequency time to the hours
                        newTimeDose = time.format(formatter);
                        txtHour2.setText(newTimeDose);
                        txtHour3.setText("");
                        txtHour4.setText("");
                        txtHour5.setText("");
                        txtHour6.setText("");
                        txtDose3.setText("");
                        txtDose4.setText("");
                        txtDose5.setText("");
                        txtDose6.setText("");
                    }
                    if(frequency != null && frequency.equals("24")) {
                        txtHour2.setText("");
                        txtHour3.setText("");
                        txtHour4.setText("");
                        txtHour5.setText("");
                        txtHour6.setText("");
                        txtDose2.setText("Your next dose is tomorrow");
                        txtDose3.setText("");
                        txtDose4.setText("");
                        txtDose5.setText("");
                        txtDose6.setText("");
                    }
                }
            }
        //------------------------------------------------------------------------
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Schedule_2.this, "Database Error:", Toast.LENGTH_SHORT).show();
            }
        });
        //-----------------------------------------------------------------------

        //Set the listener--------------------------------------------------------
        btnDone.setOnClickListener(this);
        //------------------------------------------------------------------------
    }

    @Override
    public void onClick(View view) {

    }

}