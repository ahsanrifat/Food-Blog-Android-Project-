package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ResProfilePage extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_profile_page);


        textView=findViewById(R.id.resNameProfileID);
        imageView=findViewById(R.id.resPicPofileID);

        textView.setText(LoginAsResturant.sResName);
        //imageView.setImageURI();

        Glide
                .with(this)
                .load(Uri.parse(LoginAsResturant.image)) // the uri you got from Firebase

                .into(imageView); //Your imageView variable
    }

    public void postAdd(View view) {
        Intent intent=new Intent(this,Resturant_AddPost.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
