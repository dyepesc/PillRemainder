package com.example.pillremainder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.pillremainder.models.Medication;
import com.google.android.material.textfield.TextInputEditText;

public class Start_12_AddMedication extends AppCompatActivity
                                    implements View.OnClickListener {

    //declare variables-------------------------------------------------------
    ImageButton btnImg2;
    ImageButton btnImg3;
    ImageButton btnImg4;
    ImageButton btnImg5;
    private Button btnNext;
    private TextInputEditText txtInputPillName;
    TextInputEditText txtInputPillDose;
    private TextInputEditText txtInputPillTotal;
    private Spinner intInputPillFrequency;


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
//        intInputPillFrequency = findViewById(R.id.spinnerDose);
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
        int id = v.getId();

        if (id == R.id.Pills)
        {
            clickBtn(btnImg2, resID2, resID2_2);
        }
        else if (id == R.id.Capsules)
        {
            clickBtn(btnImg3, resID3, resID3_2);
        }
        else if (id == R.id.Syrup)
        {
            clickBtn(btnImg4, resID6, resID6_2);
        }
        else if (id == R.id.Inhaler)
        {
            clickBtn(btnImg5, resID5, resID5_2);
        }

        if(id == R.id.btnNext)
        {

            goToSelectSchedule();
        }
    }
    //-------------------------------------------------------------------------

    //Method to go save User medication----------------------------------------
//    private void saveUserMedication(ImageButton imageButton) {
//        String medType = imageButton.setImageResource(R.drawable.img_2);
//        String medType = imageButton.getText().toString();
//        String pillName = txtInputPillName.getText().toString();
//        String dose = txtInputPillDose.getText().toString();
//        String pillTotal = txtInputPillTotal.getText().toString();  //it is an int
//        String frequency = intInputPillFrequency.getSelectedItem().toString();   //it is a spinner



        //creating Object Medication----------------------------------------------
//        Medication medication = new Medication();
//        Medication medication = new Medication(String medType, String pillName, String dose, String pillTotal, String frequency) {
//
//            this.medType = medType;
//            this.pillName = pillName;
//            this.dose = dose;
//            this.pillTotal = pillTotal;
//            this.frequency = frequency;
//        }



//    }
    //-------------------------------------------------------------------------
    //Method to go to a different activity-------------------------------------
    private void goToSelectSchedule() {
        Intent intent = new Intent(Start_12_AddMedication.this, Schedule_2.class);
        startActivity(intent);
    }
    //------------------------------------------------------------------------
}