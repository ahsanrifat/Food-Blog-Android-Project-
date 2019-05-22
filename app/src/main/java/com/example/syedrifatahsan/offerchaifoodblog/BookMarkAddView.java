package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookMarkAddView extends AppCompatActivity {


    List<ModelViewAdd> list =new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Context c;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mark_add_view);

        try {

            c=this;
            Log.d("ArrayListView", "On create e aslam");
            recyclerView=findViewById(R.id.rvContacts);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter=new BookmarkAdapter(list,c);

            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();

            final DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("ResturantAddTable");

            databaseReference = FirebaseDatabase.getInstance().getReference("Bookmark");
            Log.d("ArrayListView", "Bookmark Reference banalam");
            databaseReference.orderByChild("userKey").equalTo(LoginAsFoodLover.userKey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        //bus number exists in Database


                        for (DataSnapshot eachAdd : dataSnapshot.getChildren()) {
                            BookmarkAdd resAdd = eachAdd.getValue(BookmarkAdd.class);
//                            Log.d("ArrayListView", LoginAsFoodLover.userKey+" user key");
//                            Log.d("ArrayListView", resAdd.addKey+" add key");

                            databaseReference2.orderByChild("key").equalTo(resAdd.addKey).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Object some = dataSnapshot.getValue();
                                    String value = some.toString();
                                    Log.d("ArrayListViewrrr", value);
                                    for (DataSnapshot eachAdd : dataSnapshot.getChildren()) {
                                        ResAddPostDB resAdd = eachAdd.getValue(ResAddPostDB.class);



                                        ModelViewAdd modelViewAdd = new ModelViewAdd(resAdd.getrName(), resAdd.getTitle(), resAdd.getPrice(), resAdd.getImageUrl(), resAdd.key);
                                        Log.d("ArrayListViewrrr", modelViewAdd.toString());
                                        list.add(modelViewAdd);
                                        adapter.notifyDataSetChanged();


                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Log.d("ArrayListViewrrr", databaseError.toString());

                                }
                            });


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
}
