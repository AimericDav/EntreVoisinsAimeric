package com.openclassrooms.entrevoisins;

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

public class ProfileActivity extends AppCompatActivity {

    /** Neighbour */
    private Neighbour neighbour;

    private TextView testFav;
    private String testFavString;

    /** Init object activity_profile */
    private TextView namePicture;
    private TextView nameInfo;
    private ImageView avatarBackground;
    private FloatingActionButton favoriteButton;

    /** Api service grace à DI */
    private NeighbourApiService neighbourApiService;

    /** test discord*/
    private int imageNumber;
    private int[] images = {R.drawable.ic_star_white_24dp, R.drawable.ic_star_border_white_24dp};


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

        /** Declarate api service with DI */
        neighbourApiService = DI.getNeighbourApiService();

        /** Set Neighbour param of object activity_profile */
        namePicture.setText(neighbour.getName());
        nameInfo.setText(neighbour.getName());
        /** Glide for set picture */
        Glide.with(ProfileActivity.this).load(neighbour.getAvatarUrl()).into(avatarBackground);

        /** Test favorite */
        testFav = findViewById(R.id.test);

        /** Condition get favorite pour affihcer good actionButton au lancement  */
        if(neighbour.getFavorite()){
            favoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        }

        buttonClick();

        /** Click actionButton for  set Favorite or not */
     /**   favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(neighbour.getFavorite()){
                    testFav.setText(String.valueOf(neighbour.getFavorite()));
                    neighbourApiService.setFavoriteNeighbour(neighbour.getId(), false);
                    favoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
                } else {
                    testFav.setText(String.valueOf(neighbour.getFavorite()));
                    neighbourApiService.setFavoriteNeighbour(neighbour.getId(), true);
                    favoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
                }
            }
        }); */
    }
    public void buttonClick() {
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageNumber++;
                imageNumber = imageNumber % images.length;
                favoriteButton.setImageResource(images[imageNumber]);
                if (imageNumber == 0) {
                    neighbour.setFavorite(true);
                    neighbourApiService.setFavoriteNeighbour(neighbour.getId(), true);
                    testFav.setText(String.valueOf(neighbour.getFavorite()));

                } else {
                    neighbour.setFavorite(false);
                    neighbourApiService.setFavoriteNeighbour(neighbour.getId(), false);
                    testFav.setText(String.valueOf(neighbour.getFavorite()));
                }
            }
        });}
}
