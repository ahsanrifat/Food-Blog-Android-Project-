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

public class LoginAsFoodLover extends AppCompatActivity {

    EditText email;
    EditText password;
    static String userKey;
    static String userEmail;
    static String userName;
    static String userPass;
    static String  userProPic;

    DatabaseReference logincheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_food_lover);


        email=findViewById(R.id.logEemailID);
        password=findViewById(R.id.logpasswordID);
    }

    public void login(View v){
        try {
            Log.d("Login_prb", "UFFF");
            String cEmail = email.getText().toString();
            final String ePassword = password.getText().toString();
            logincheck = FirebaseDatabase.getInstance().getReference();
            logincheck.child("User").orderByChild("email").equalTo(cEmail).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        //bus number exists in Database
                        Object some = dataSnapshot.getValue();
                        String value = some.toString();
                        Log.d("LoginEEE", value);
                        for (DataSnapshot usersnap : dataSnapshot.getChildren()) {
                            User user = usersnap.getValue(User.class);

                            String pass = user.getPassword();
                            if (ePassword.equals(pass)) {


                                userName = user.getName();
                                userKey = user.getKey();
                                userEmail = user.getEmail();
                                userPass = user.getPassword();
                                userProPic = user.getImage();
                                Log.d("LoginEEE", userEmail + " " + userProPic + " " + userName);

                                Toast.makeText(LoginAsFoodLover.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                goToProfilePage();
                            } else {

                                Toast.makeText(LoginAsFoodLover.this, "Wrong Password", Toast.LENGTH_SHORT).show();

                            }
                        }
                    } else {
                        Toast.makeText(LoginAsFoodLover.this, "User Does Not Exists", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            Log.d("Login_prb", e.toString());
        }

        }
    public void goToProfilePage(){
        Intent intent=new Intent(this,ProfilePage.class);
        startActivity(intent);
    }
    public void userRegister(View v){

        Intent intent=new Intent(this,UserRegister.class);
        startActivity(intent);

    }
}
