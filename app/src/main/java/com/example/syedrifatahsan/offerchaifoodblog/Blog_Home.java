package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Blog_Home extends AppCompatActivity {


    List<ModelViewAdd> list =new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Context c;



    DatabaseReference databaseReference;
    RecyclerView.Adapter viewAdapterClass;





    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog__home);

        try {

            c=this;

            recyclerView=findViewById(R.id.rvContacts);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter=new ContactsAdapter(list,c);

            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();



            databaseReference = FirebaseDatabase.getInstance().getReference("ResturantAddTable");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        //bus number exists in Database
                        for (DataSnapshot eachAdd : dataSnapshot.getChildren()) {
                            ResAddPostDB resAdd = eachAdd.getValue(ResAddPostDB.class);



                            ModelViewAdd modelViewAdd = new ModelViewAdd(resAdd.getrName(), resAdd.getTitle(), resAdd.getPrice(), resAdd.getImageUrl(), resAdd.key);

                            list.add(modelViewAdd);
                            adapter.notifyDataSetChanged();


                        }



                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }catch (Exception e){
            Log.d("ArrayListView", e.toString());
        }
    }
    public void profile(View view) {
        Intent intent=new Intent(this,ProfilePage.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent=new Intent(this,LoginAsFoodLover.class);
        startActivity(intent);
    }
}
