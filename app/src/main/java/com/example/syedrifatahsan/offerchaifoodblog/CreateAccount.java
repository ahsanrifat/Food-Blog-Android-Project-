package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }
    public  void registerUser(View v){

        Intent i=new Intent(this,UserRegister.class);
        startActivity(i);

    }
    public void registerResturant(View v){

        Intent i=new Intent(this,Resturent_Register.class);
        startActivity(i);

    }

    public void Resturant(View v){

        Toast.makeText(this, "Duklam", Toast.LENGTH_SHORT).show();

        try {
            Intent i = new Intent(this, Blog_Home.class);
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public void Resturantt(View v){

        Toast.makeText(this, "Duklam", Toast.LENGTH_SHORT).show();

        try {
            Intent i = new Intent(this, Resturant_AddPost.class);
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


}
