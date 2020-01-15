package com.openclassrooms.entrevoisins;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class ProfileActivity extends AppCompatActivity {

    /** Neighbour */
    private Neighbour neighbour;

    /** Init object activity_profile */
    private TextView namePicture;
    private TextView nameInfo;
    private ImageView avatarBackground;
    private FloatingActionButton favoriteButton;

    /** Boolean for favorite button action */
    private boolean pushFavoriteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /** Get neighbour with Parcelable and Intent */
        neighbour = getIntent().getParcelableExtra("neighbour");

        /** FindView by id */
        namePicture = findViewById(R.id.name_neighbour_picture);
        nameInfo = findViewById(R.id.name_neighbour_info);
        avatarBackground = findViewById(R.id.avatar_neighbour);

        /** Set Neighbour param of object activity_profile */
        namePicture.setText(neighbour.getName());
        nameInfo.setText(neighbour.getName());
        /** Glide for set picture */
        Glide.with(ProfileActivity.this).load(neighbour.getAvatarUrl()).into(avatarBackground);

        /** Condition pour set favorite Neighbour avec button */

    }
}
