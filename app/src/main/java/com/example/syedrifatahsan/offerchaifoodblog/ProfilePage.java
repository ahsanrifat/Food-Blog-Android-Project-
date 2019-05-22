package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfilePage extends AppCompatActivity {

    TextView textView;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        textView=findViewById(R.id.proPageNameID);
        imageView=findViewById(R.id.proPageImageID);

        textView.setText(LoginAsFoodLover.userName);
        Glide
                .with(this)
                .load(Uri.parse(LoginAsFoodLover.userProPic)) // the uri you got from Firebase

                .into(imageView); //Your imageView variable
    }

    public void blogPost(View view) {
        Intent intent=new Intent(this,Blog_Home.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent=new Intent(this,LoginAsFoodLover.class);
        startActivity(intent);
    }

    public void viewBookmark(View view) {
        Intent intent=new Intent(this,BookMarkAddView.class);
        startActivity(intent);
    }
}
