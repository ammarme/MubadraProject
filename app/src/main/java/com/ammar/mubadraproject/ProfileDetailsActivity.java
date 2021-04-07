package com.ammar.mubadraproject;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ProfileDetailsActivity extends AppCompatActivity {


    CollapsingToolbarLayout collapsingToolbarLayout;

    TextView item_title;
    TextView descriptionTextView;
    TextView name;
    ImageView imageViewde;


    public String title = "";
    public String description = "";
    public String profileimage = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);


        collapsingToolbarLayout = findViewById(R.id.collapsing);

        item_title = findViewById(R.id.item_title_view);
        name = findViewById(R.id.item_title_view);
        descriptionTextView = findViewById(R.id.item_title);
        imageViewde = findViewById(R.id.item_profile_food);

        title = getIntent().getStringExtra("getTitle");
        description = getIntent().getStringExtra("getDescription");
        profileimage = getIntent().getStringExtra("getImage");

        item_title.setText(title);
        descriptionTextView.setText(description);

        Glide.with(this).load(profileimage).into(imageViewde);

//
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        name.setText(title );


//        mdeetails.setText(profiledescriptio);

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