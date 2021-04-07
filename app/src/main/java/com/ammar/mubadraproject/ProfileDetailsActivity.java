package com.ammar.mubadraproject;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ProfileDetailsActivity extends AppCompatActivity {


    CollapsingToolbarLayout collapsingToolbarLayout;


    TextView name;
    TextView mdeetails;
    TextView item_title;

    ImageView imageViewde;

    public String Areaid = "";
    public String MainCatogeryidd = "";
    public String profilename = "";
    public String profiledescriptio = "";
    public String profileimage = "";
    public String title = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);


        collapsingToolbarLayout = findViewById(R.id.collapsing);


        imageViewde = findViewById(R.id.item_profile_food);
        item_title = findViewById(R.id.item_title);

        Areaid = getIntent().getStringExtra("LastArea");
        MainCatogeryidd = getIntent().getStringExtra("LastCateg");
        profilename = getIntent().getStringExtra("profilenameKey");

        profiledescriptio = getIntent().getStringExtra("profiledescriptioKey");
        profileimage = getIntent().getStringExtra("profileimageKey");
        title = getIntent().getStringExtra("titleKey");


//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle(profilename);
//        name.setText(profilename + " - Menus  ");


//        mdeetails.setText(profiledescriptio);
//        Glide.with(this).load(profileimage).into(imageViewde);

//        item_title.setText(title);


    }


//    public void share(View view) {
//
////        Areaid = getIntent().getStringExtra("LastArea");
////        MainCatogeryidd = getIntent().getStringExtra("LastCateg");
////        profilename = getIntent().getStringExtra("profilenameKey");
//        Intent intent = new Intent(com.createegypthatly.hatly.ProfileDetailsActivity.this, UploadOrderActivity.class);
//
//        startActivity(intent);
//    }


}