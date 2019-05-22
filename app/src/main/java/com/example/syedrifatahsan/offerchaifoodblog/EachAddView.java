package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EachAddView extends AppCompatActivity {


    TextView addBundleT,resBundleT,addTitle,resNameT;
    ImageView addIMG,resIMG;




    String addKey;
    String resKey;


    //each add info
    String title=""
            ,description="",price="",Addimage="",addBundle="";
    //resinfo
    String resName,addres,city,area,Resimage,resBundle;

    DatabaseReference eachAdd;
    DatabaseReference addResInfo;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        try {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_add_view);


            context=this;

            addTitle=findViewById(R.id.eachAddViewOfferTitleID);
            addBundleT=findViewById(R.id.eachAddViewBundleID);
            addIMG=findViewById(R.id.eachAddViewImageID);

            resNameT=findViewById(R.id.eachAddViewResOfferTitleID);
            resBundleT=findViewById(R.id.eachAddViewResBundleID);
            resIMG=findViewById(R.id.eachAddViewResImageID);


            Intent getIntent = getIntent();
            addKey = getIntent.getStringExtra("key");



            eachAdd=FirebaseDatabase.getInstance().getReference("ResturantAddTable");
            eachAdd.orderByChild("key").equalTo(addKey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot eachAdd : dataSnapshot.getChildren()) {
                        ResAddPostDB resAdd = eachAdd.getValue(ResAddPostDB.class);
                        Addimage=resAdd.imageUrl;
                        Log.d("SingleAddShow", Addimage);
                        resKey=resAdd.resKey;
                        Log.d("SingleAddShow", resKey);
                        title=resAdd.title;
                        addBundle=resAdd.texts+"\n"+resAdd.price;
                        Log.d("SingleAddShow", addBundle);

                    }
                    addTitle.setText(title);

                    if(!Addimage.equals("")) {
                        Picasso.with(context).load(Uri.parse(Addimage)).into(addIMG);
                    }
                    addBundleT.setText(addBundle);
                    addResInfo=FirebaseDatabase.getInstance().getReference("Resturant");
                    addResInfo.orderByChild("key").equalTo(resKey).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Log.d("SingleAddShow", "Res e dhuklam");
                            for (DataSnapshot eachAdd : dataSnapshot.getChildren()) {
                                ResturantDB res = eachAdd.getValue(ResturantDB.class);
                                resName=res.sResName;
                                Log.d("SingleAddShow", resName);
                                Resimage=res.image;
                                Log.d("SingleAddShow", Resimage);
                                resBundle=res.sResAddress+"\n"+res.sCity+"\n"+res.sArea+"\n"+res.sResEmail;
                                Log.d("SingleAddShow", resBundle);

                            }

                            resNameT.setText(resName);
                            Picasso.with(context).load(Uri.parse(Resimage)).into(resIMG);
                            resBundleT.setText(resBundle);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d("SingleAddShow", "Resturant pai na");

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });












        }catch (Exception e){
            Log.d("SingleAddShow", e.toString());
        }
    }

}
