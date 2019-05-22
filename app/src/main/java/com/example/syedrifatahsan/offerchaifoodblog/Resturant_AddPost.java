package com.example.syedrifatahsan.offerchaifoodblog;




import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URL;

public class Resturant_AddPost extends AppCompatActivity {



    //banner pic up

    private static final int PICK_IMAGE_REQUEST=1;
    ImageView imageView;
    Button uploadPic;
     Uri mImageView;
    DatabaseReference ResturantAddTaable;
    StorageReference storageReference;

    String url;
    //elemnts

    EditText title;
    EditText price;
    EditText description;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant__add_post);

         c=this;
        imageView=findViewById(R.id.addBannerID);
        title=findViewById(R.id.addTitleID);
        description=findViewById(R.id.addDescriptionID);
        price=findViewById(R.id.addPriceID);


        ResturantAddTaable=FirebaseDatabase.getInstance().getReference("ResturantAddTable"); //table name


    }


    public void addPublish(View v){


        try {

            url="";

        Log.d("addPublishzz", "Suru");


        String aTitle=title.getText().toString();
        String aDescription=description.getText().toString();
        String aPrice=price.getText().toString();
        String aName=LoginAsResturant.sResName;
        String resKey=LoginAsResturant.key;
            Log.d("addPublishzz", "Suru");

        storageReference=FirebaseStorage.getInstance().getReference().child("ResAddImage/"+mImageView.getLastPathSegment());
        storageReference.putFile(mImageView).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(c, "Image Uploaded", Toast.LENGTH_SHORT).show();
            }
        });
            Log.d("addPublishzz", "Suru");
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                setUri(uri.toString());
                Log.d("addPublishzz", "Suru");
            }
        });


        String key= ResturantAddTaable.push().getKey();
            Log.d("addPublishzz", key);
            Log.d("addPublishzz", resKey);
        ResAddPostDB resturantAddDB=new ResAddPostDB(aName,aDescription,aTitle,aPrice,url,key,resKey);
        ResturantAddTaable.child(key).setValue(resturantAddDB);
            Toast.makeText(this, "Add Posted Successfully", Toast.LENGTH_SHORT).show();
            takeToHome();



        }catch (Exception e){

            Log.d("addPublishzz", e.toString());
        }

    }


    public void Up(View v){

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data!= null && data.getData() != null){

            mImageView=data.getData();
            imageView.setImageURI(mImageView);

        }
    }

    void setUri(String u){
        url=u;
    }
    void takeToHome(){
        Intent intent=new Intent(this,ResProfilePage.class);
        startActivity(intent);
    }
}
