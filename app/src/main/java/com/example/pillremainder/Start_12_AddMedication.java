package com.example.pillremainder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Start_12_AddMedication extends AppCompatActivity
                                    implements View.OnClickListener {

    //declare variables-------------------------------------------------------
    private ImageButton btnImg2;
    private ImageButton btnImg3;
    private ImageButton btnImg4;
    private ImageButton btnImg5;
    private Button btnNext;

    public boolean isImage2 = true;
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

    //creating thevariables for every ImageButton-----------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start12_add_medication);

        btnImg2 = findViewById(R.id.imageView2);
        btnImg3 = findViewById(R.id.imageView3);
        btnImg4 = findViewById(R.id.imageView4);
        btnImg5 = findViewById(R.id.imageView5);
        btnNext = findViewById(R.id.btnNext);
    //------------------------------------------------------------------------

    //Set the listener--------------------------------------------------------
        btnImg2.setOnClickListener(this);
        btnImg3.setOnClickListener(this);
        btnImg4.setOnClickListener(this);
        btnImg5.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    //------------------------------------------------------------------------

    //Method to call the button when is clicked----Original and working

//        btnImg2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isImage2) {
//                    btnImg2.setImageResource(R.drawable.img_2_2);
//                } else {
//                    btnImg2.setImageResource(R.drawable.img_2);
//                }
//                isImage2 = !isImage2;
//            }
//        });

    }
    //Method to call the ImageButton when is clicked-------------------------
    private void clickBtn(ImageButton imageButton, int resID1, int resID2) {

            imageButton.setImageResource(resID2);

//        if (isImage2) {
//            imageButton.setImageResource(resID1);
//        } else {
//            imageButton.setImageResource(resID2);
//        }
//        isImage2 = !isImage2;

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
    //Method to go to a different activity-------------------------------------
    private void goToSelectAuth() {
        Intent intent = new Intent(Start_12_AddMedication.this, Schedule_2.class);
        startActivity(intent);
    }
    //------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.imageView2)
        {
            clickBtn(btnImg2, resID2, resID2_2);
        }
        else if (id == R.id.imageView3)
        {
            clickBtn(btnImg3, resID3, resID3_2);
        }
        else if (id == R.id.imageView4)
        {
            clickBtn(btnImg4, resID6, resID6_2);
        }
        else if (id == R.id.imageView5)
        {
            clickBtn(btnImg5, resID5, resID5_2);
        }

        if(id == R.id.btnNext)
        {
            goToSelectAuth();
        }
    }
}