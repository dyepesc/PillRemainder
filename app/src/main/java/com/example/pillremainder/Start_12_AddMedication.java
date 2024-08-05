package com.example.pillremainder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pillremainder.models.Medication;
import com.example.pillremainder.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.AuthProvider;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

public class Start_12_AddMedication extends AppCompatActivity
                                    implements View.OnClickListener {
    //firebase database-------------------------------------------------------
    AuthProvider mAuthProvider;
    Medication myMedication;
    AlertDialog mDialog;
    DatabaseReference mDatabase;
    FirebaseAuth myAuth;
    //------------------------------------------------------------------------

    //declare variables-------------------------------------------------------
    ImageButton btnImg2;
    ImageButton btnImg3;
    ImageButton btnImg4;
    ImageButton btnImg5;
    Button btnNext;
    TextInputEditText txtInputPillName;
    TextInputEditText txtInputPillDose;
    TextInputEditText txtInputPillTotal;
    Spinner txtInputPillFrequency;
    TimePicker timePicker;
    //------------------------------------------------------------------------

    //creating the id variables for every ImageButton-------------------------
    int resID2 = R.drawable.img_2;
    int resID2_2 = R.drawable.img_2_2;
    int resID3 = R.drawable.img_3;
    int resID3_2 = R.drawable.img_3_2;
    int resID4 = R.drawable.img_4;
    int resID4_2 = R.drawable.img_4_2;
    int resID5 = R.drawable.img_5;
    int resID5_2 = R.drawable.img_5_2;
    int resID6 = R.drawable.img_6;
    int resID6_2 = R.drawable.img_6_2;
    String medType;
    //------------------------------------------------------------------------

    //creating the variables for every ImageButton and inputText---------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start12_add_medication);

        btnImg2 = findViewById(R.id.Pills);
        btnImg3 = findViewById(R.id.Capsules);
        btnImg4 = findViewById(R.id.Syrup);
        btnImg5 = findViewById(R.id.Inhaler);
        btnNext = findViewById(R.id.btnNext);
        txtInputPillName = findViewById(R.id.textInputMed);
        txtInputPillDose = findViewById(R.id.textInputDose);
        txtInputPillTotal = findViewById(R.id.textInputTotal);
        txtInputPillFrequency = findViewById(R.id.spinnerDose);
        timePicker = findViewById(R.id.timePicker);

        myAuth = FirebaseAuth.getInstance();                        //Instance of the database
        mDatabase = FirebaseDatabase.getInstance().getReference();  //principal node in the database
    //------------------------------------------------------------------------

    //Set the listener--------------------------------------------------------
        btnImg2.setOnClickListener(this);
        btnImg3.setOnClickListener(this);
        btnImg4.setOnClickListener(this);
        btnImg5.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    //------------------------------------------------------------------------


    }
    //Method to call the ImageButton when is clicked 2------------------------
    private void clickBtn(ImageButton imageButton, int resID1, int resID2) {

        imageButton.setImageResource(resID2);

        if(imageButton == btnImg2) {
            btnImg3.setImageResource(R.drawable.img_3);
            btnImg4.setImageResource(R.drawable.img_6);
            btnImg5.setImageResource(R.drawable.img_5);
        }
        else if(imageButton == btnImg3) {
            btnImg2.setImageResource(R.drawable.img_2);
            btnImg4.setImageResource(R.drawable.img_6);
            btnImg5.setImageResource(R.drawable.img_5);
        }
        else if(imageButton == btnImg4) {
            btnImg3.setImageResource(R.drawable.img_3);
            btnImg2.setImageResource(R.drawable.img_2);
            btnImg5.setImageResource(R.drawable.img_5);
        }
        else if(imageButton == btnImg5) {
            btnImg3.setImageResource(R.drawable.img_3);
            btnImg4.setImageResource(R.drawable.img_6);
            btnImg2.setImageResource(R.drawable.img_2);
        }

    }
    //------------------------------------------------------------------------

    //Method to call the ImageButton when is clicked 1------------------------
    @Override
    public void onClick(View v) {

        String pillName = txtInputPillName.getText().toString();
        String pillDose = txtInputPillDose.getText().toString();
        String pillTotal = txtInputPillTotal.getText().toString();
        String frequency = txtInputPillFrequency.getSelectedItem().toString();
    //Working with TimePicker--------------------------------------------------
        int hour = timePicker.getHour(); // API 23+
        int minute = timePicker.getMinute(); // API 23+
    //Create an object Calendar------------------------------------------------
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());

        String timeStart = sdf.format(calendar.getTime());
    //------------------------------------------------------------------------
        int id = v.getId();

        if (id == R.id.Pills)
        {
            clickBtn(btnImg2, resID2, resID2_2);
            medType = "Pills";
        }
        else if (id == R.id.Capsules)
        {
            clickBtn(btnImg3, resID3, resID3_2);
            medType = "Capsules";
        }
        else if (id == R.id.Syrup)
        {
            clickBtn(btnImg4, resID6, resID6_2);
            medType = "Syrup";
        }
        else if (id == R.id.Inhaler)
        {
            clickBtn(btnImg5, resID5, resID5_2);
            medType = "Inhaler";
        }

        if(id == R.id.btnNext)
        {

            saveUserMedication(medType, pillName, pillDose, pillTotal, frequency, timeStart);
//            goToSelectSchedule();
        }
    }
    //-------------------------------------------------------------------------

    //Method to go save User medication----------------------------------------
    private void saveUserMedication(String medType, String pillName, String dose, String pillTotal, String frequency, String timeStart) {
        Medication medication = new Medication();

        medication.setMedType(medType);
        medication.setPillName(pillName);
        medication.setDose(dose);
        medication.setPillTotal(pillTotal);
        medication.setFrequency(frequency);
        medication.setStartTime(timeStart);

        String id = myAuth.getCurrentUser().getUid();   //To save the medications in every user

        mDatabase.child("Users").child(id).child("Medications").setValue(medication).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(Start_12_AddMedication.this, "User Medication saved", Toast.LENGTH_SHORT).show();
                    goToSelectSchedule();
                }
                else {
                    Toast.makeText(Start_12_AddMedication.this, "User Medication can not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //-------------------------------------------------------------------------
    //Method to go to a different activity-------------------------------------
    private void goToSelectSchedule() {
        android.app.AlertDialog myDialog;
        myDialog = new SpotsDialog.Builder().setContext(Start_12_AddMedication.this).setMessage("Please wait...").build();
        myDialog.show();
        Intent intent = new Intent(Start_12_AddMedication.this, Schedule_2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    //------------------------------------------------------------------------
}