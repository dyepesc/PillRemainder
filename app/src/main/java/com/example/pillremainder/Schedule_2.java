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
    TextView txtHour;
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
        txtHour = findViewById(R.id.hour);
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


//                    if(frequency != null && frequency.equals("4")) {
//                        intFrequency = 3;
//                    }
                }
            }

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