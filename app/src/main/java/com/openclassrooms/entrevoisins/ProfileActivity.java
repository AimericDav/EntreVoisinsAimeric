package com.openclassrooms.entrevoisins;

import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

public class ProfileActivity extends AppCompatActivity {

    /** Neighbour */
    private Neighbour neighbour;

    /** Init object activity_profile */
    private TextView namePicture;
    private TextView nameInfo;
    private ImageView avatarBackground;
    private FloatingActionButton favoriteButton;
    private ImageView backArrow;

    /** Api service grace à DI */
    private NeighbourApiService neighbourApiService;

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
        favoriteButton = findViewById(R.id.favorite_button);
        backArrow = findViewById(R.id.arrow_back);

        /** Declarate api service with DI */
        neighbourApiService = DI.getNeighbourApiService();

        /** Set Neighbour param of object activity_profile */
        namePicture.setText(neighbour.getName());
        nameInfo.setText(neighbour.getName());

        /** Glide for set picture */
        Glide.with(ProfileActivity.this).load(neighbour.getAvatarUrl()).into(avatarBackground);

        /** Condition get favorite pour afficher good actionButton au lancement  */
        if(neighbourApiService.getFavoriteNeighbour(neighbour.getId())){
            favoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        }

        /** Click actionButton for  set Favorite or not */
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(neighbourApiService.getFavoriteNeighbour(neighbour.getId())){
                    favoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
                    neighbourApiService.setFavoriteNeighbour(neighbour.getId(), false);
                } else {
                    favoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
                    neighbourApiService.setFavoriteNeighbour(neighbour.getId(), true);
                }
            }
        });

        /**Return back with Arrow */
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.this.finish();
            }
        });

    }
}
