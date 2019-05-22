package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.circularreveal.cardview.CircularRevealCardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.UploadTask;

public class UserRegister extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText password;
    EditText password2;
    Button signup;

    String uriStringProPic;

    DatabaseReference databaseReference;
    DatabaseReference emailCheck;
    StorageReference storageReference;


    private static final int PICK_IMAGE_REQUEST=1;
    ImageView propic;
    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        try {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_register);

            signup=findViewById(R.id.signupID);
            name = findViewById(R.id.nameID);
            email = findViewById(R.id.EemailID);
            password = findViewById(R.id.passwordID);
            password2 = findViewById(R.id.passwordID2);
            signup = findViewById(R.id.signupID);
            propic=findViewById(R.id.propicID);
            databaseReference = FirebaseDatabase.getInstance().getReference().child("User"); //table name

        }catch (Exception e){

            Log.d("User_Register",e.toString());
        }
    }


        public void register (View v){

            Log.d("User_Register222","Register");

        try {

            String sname = name.getText().toString();
            String spas = password.getText().toString();
            String spas2 = password2.getText().toString();
            String semail = email.getText().toString();

            if (!sname.equals("")) {
                if (!semail.equals("")) {
                        //checkExistingUser(semail);
                    if (!spas.equals("")) {

                        if (spas.equals(spas2)) {


                            storageReference = FirebaseStorage.getInstance().getReference().child("ProfileImage/" + mImageUri.getLastPathSegment());
                            storageReference.putFile(mImageUri);




                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    setUri(uri);
                                    Log.d("User_Register222",uri.toString());
                                }
                            });


                            String ID = databaseReference.push().getKey();
                            Log.d("User_Register222",ID);


                            Log.d("User_Register222",uriStringProPic);



                            User user = new User(name.getText().toString(), email.getText().toString(), password.getText().toString(), ID, uriStringProPic);
                            Log.d("User_Register222",user.toString());
                            databaseReference.child(ID).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(UserRegister.this, "Registration Successful now login", Toast.LENGTH_SHORT).show();
                                }
                            });

                            Intent intent=new Intent(this,LoginAsFoodLover.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(this, "Password Did Not Matched", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "Password Empty!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Name Empty!", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Log.d("User_Register", e.toString());
        }
    }

    public void setUri(Uri uri){
        uriStringProPic=uri.toString();
    }


        public void Up (View v){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            mImageUri = data.getData();
            propic.setImageURI(mImageUri);

        }
    }
    public void checkExistingUser(String cEmail){



        emailCheck=FirebaseDatabase.getInstance().getReference();
        emailCheck.child("User").orderByChild("email").equalTo(cEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    Toast.makeText(UserRegister.this, "User Exists. Try with another Email Address!", Toast.LENGTH_SHORT).show();
                    returnToLogin();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void returnToLogin(){
        Intent intent=new Intent(this,LoginAsFoodLover.class);
        startActivity(intent);
    }



}
