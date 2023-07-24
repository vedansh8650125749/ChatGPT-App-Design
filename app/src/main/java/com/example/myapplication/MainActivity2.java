package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {


    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    Button button2;

    ImageView imageView;
    Toolbar toolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button2 = findViewById(R.id.button2);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        ImageView profileImage = findViewById(R.id.imageView6);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();

           // Set the user's profile image (if available)
            Uri personPhotoUri = acct.getPhotoUrl();
            if (personPhotoUri != null) {
                // Load the image with Glide and apply a rounded transformation
                Glide.with(this)
                        .load(personPhotoUri)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(profileImage);
            } else {
                // If the user doesn't have a profile image, you can set a default placeholder here
                Glide.with(this)
                        .load(R.drawable.button_background)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(profileImage);
            }

            Menu menu = navigationView.getMenu();

            // Find the home_menu item and update its title with the user's name
            MenuItem homeMenuItem = menu.findItem(R.id.home_menu);
            homeMenuItem.setTitle(personName);
            MenuItem dashboard_menu = menu.findItem(R.id.dashboard_menu);
            dashboard_menu.setTitle(personEmail);



        }



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });
    }

}
