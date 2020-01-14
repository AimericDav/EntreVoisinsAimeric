package com.openclassrooms.entrevoisins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Neighbour neighbour = getIntent().getParcelableExtra("neighbour");

    }
}
