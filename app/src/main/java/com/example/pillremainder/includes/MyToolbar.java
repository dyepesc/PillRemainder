package com.example.pillremainder.includes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pillremainder.R;

public class MyToolbar {
    public static void show(AppCompatActivity activity, String title, Boolean upButton) {

        Toolbar toolbar = activity.findViewById(R.id.toolbarLogin);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
