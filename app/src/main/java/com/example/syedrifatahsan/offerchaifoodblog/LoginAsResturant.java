package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAsResturant extends AppCompatActivity {

    EditText email;
    EditText password;

    static String key;
    static String sResName;
    static String sResAddress;
    static String sResEmail;
    static String sOwner;
    static String sPass;
    static String sCity;
    static String sArea;
    static String image;

    DatabaseReference logincheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_resturant);


        email=findViewById(R.id.logEemailID);
        password=findViewById(R.id.logpasswordID);
    }
    public void login(View v){

        Log.d("LoginEEE",  "ResLogin");


        String cEmail=email.getText().toString();
        Log.d("LoginEEE",  cEmail);
        final String ePassword=password.getText().toString();
        logincheck=FirebaseDatabase.getInstance().getReference();
        logincheck.child("Resturant").orderByChild("sResAddress").equalTo(cEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //bus number exists in Database
                    Object some = dataSnapshot.getValue();
                    String value = some.toString();

                    for(DataSnapshot usersnap : dataSnapshot.getChildren()) {
                        ResturantDB user = usersnap.getValue(ResturantDB.class);

                        String pass= user.sPass;
                        if(ePassword.equals(pass)){
                            key=user.key;
                            sResName=user.sResName;
                            sResAddress=user.sResAddress;
                            sResEmail=user.sResEmail;
                            sOwner=user.sOwner;
                            sPass=user.sPass;
                            sCity=user.sCity;
                            sArea=user.sArea;
                            image=user.image;


                            Log.d("LoginEEE",  key);

                            Toast.makeText(LoginAsResturant.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            goToProfilePage();
                        }else{

                            Toast.makeText(LoginAsResturant.this, "Wrong Password", Toast.LENGTH_SHORT).show();

                        }
                    }
                } else {
                    Toast.makeText(LoginAsResturant.this, "User Does Not Exists", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });}
    public void goToProfilePage(){
        Intent intent=new Intent(this,ResProfilePage.class);
        startActivity(intent);
    }
    public void ResRegister(View v){

        Intent intent=new Intent(this,Resturent_Register.class);
        startActivity(intent);

    }
}
