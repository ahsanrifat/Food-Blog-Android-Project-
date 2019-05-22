package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Resturent_Register extends AppCompatActivity {

    Spinner city;
    Spinner area;

    String[] cityS;
    String [] areaS;
    DatabaseReference databaseReference;

    EditText resName;
    EditText resAddress;
    EditText resPassword;
    EditText resPassword2;
    EditText resOwner;
    EditText email;


    //pic_upload
    private static final int PICK_IMAGE_REQUEST=1;
    ImageView imageView;
    Button uploadPic;
     Uri mImageUri;

     Context c;

    StorageReference storageReference;

    String imageURiFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturent__register);
        c=this;
        city=findViewById(R.id.citySpinnerID);
        area=findViewById(R.id.areaSpinnerID);
        cityS=getResources().getStringArray(R.array.city);
        areaS=getResources().getStringArray(R.array.area);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Resturant"); //table name


        //List<String> categories = new ArrayList<String>();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityS);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        city.setAdapter(dataAdapter);


        //List<String> categories = new ArrayList<String>();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, areaS);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        area.setAdapter(dataAdapter2);



        resName=findViewById(R.id.restaurantNameID);
        resAddress=findViewById(R.id.addressID);
        email=findViewById(R.id.lisenceID);
        resOwner=findViewById(R.id.ownerNameID);
        resPassword=findViewById(R.id.resPasswordID);
        resPassword2=findViewById(R.id.resPasswordID2);

        imageView=findViewById(R.id.imageViewRes);

    }

    public void registerRes(View v){


        try {

            String sResName = resName.getText().toString();
            String sResAddress = resAddress.getText().toString();
            String sEmail = email.getText().toString();
            String sOwner = resOwner.getText().toString();
            String sPass1 = resPassword.getText().toString();
            String sPass2 = resPassword2.getText().toString();
            String sCity = city.getSelectedItem().toString();
            String sArea = area.getSelectedItem().toString();


            if (!sResName.equals("") && !sResAddress.equals("") && !sEmail.equals("") && !sOwner.equals("") && !sPass1.equals("") && !sArea.equals("") && !sCity.equals("")) {
                if ((sPass1.equals(sPass2))) {


                    String key = databaseReference.push().getKey();


                    storageReference = FirebaseStorage.getInstance().getReference().child("ResRegister/" + mImageUri.getLastPathSegment());


                    //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
                    //StorageReference riversRef = storageRef.child("images/rivers.jpg");

                    storageReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(c, "Image Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });

                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            setUri(uri.toString());
                            Log.d("ResRegisterAAA", uri.toString());
                        }
                    });
                    Log.d("ResRegisterAAA", imageURiFirebase);
                    ResturantDB resturantDB = new ResturantDB(key, sResName, sResAddress, sEmail, sOwner, sPass1, sCity, sArea, imageURiFirebase);
                    databaseReference.child(key).setValue(resturantDB).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(c, "Register Successful", Toast.LENGTH_SHORT).show();
                            takeToLogin();
                        }
                    });


                } else {
                    Toast.makeText(this, "Password Did Not Matched", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Empty Field Not Accepted", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.d("ResRegisterAAA", e.toString());
        }

    }

    public void Up(View v){

        Log.d("UPLOADT", "up: IMage Up");

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data!= null && data.getData() != null){

            mImageUri=data.getData();
            imageView.setImageURI(mImageUri);

        }
    }

    void setUri(String u){
        imageURiFirebase=u;
    }
    void takeToLogin(){
        Intent intent=new Intent(this,LoginAsResturant.class);
        startActivity(intent);
    }
}
