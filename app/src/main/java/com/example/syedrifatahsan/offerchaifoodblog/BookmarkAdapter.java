package com.example.syedrifatahsan.offerchaifoodblog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookmarkAdapter extends
        RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {


    private List<ModelViewAdd> mContacts;
    Context context;


    public BookmarkAdapter(List<ModelViewAdd> contacts,Context context) {

        try {


            Log.d("ArrayListView", "Adapter Constructor");
            mContacts = contacts;
            this.context=context;


            int a = mContacts.size();
            String b = String.valueOf(a);
            Log.d("ArrayListView", b);


            for (int i = 0; i < mContacts.size(); i++) {
                Log.d("ArrayListView", mContacts.get(i).getTitle());
            }

        }catch (Exception e){
            Log.d("ArrayListView", e.toString());
        }

    }



    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public BookmarkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d("ArrayListView", "onCreateViewHolder");

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.add_view_bookmark, parent, false);

        // Return a new holder instance
        BookmarkAdapter.ViewHolder viewHolder = new BookmarkAdapter.ViewHolder(contactView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(BookmarkAdapter.ViewHolder viewHolder, int position) {

        try {

            Log.d("ArrayListView", "onBindViewHolder");

            final ModelViewAdd contact = mContacts.get(position);


            TextView textViewName = viewHolder.name;
            textViewName.setText(contact.getName());
            TextView textViewTitle = viewHolder.title;
            textViewTitle.setText(contact.getTitle());
            TextView textViewTitlePrice = viewHolder.price;
            textViewTitlePrice.setText(contact.getPrice());
            ImageView imageViewAddImage = viewHolder.image;
//            Glide
//                    .with(context)
//                    .load(Uri.parse(contact.getImage())) // the uri you got from Firebase
//
//                    .into(imageViewAddImage); //Your imageView variable


            Picasso.with(context).load(Uri.parse(contact.getImage())).into(imageViewAddImage);

            Button details = viewHolder.details;

            try {

                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, EachAddView.class);
                        intent.putExtra("key", contact.getKey());
                        context.startActivity(intent);
                    }
                });




            } catch (Exception e) {

                Log.d("SingleAddShow", e.toString());
            }
        }catch (Exception e) {

            Log.d("ArrayListView", e.toString());
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;

        TextView name;
        TextView key;
        TextView title;
        TextView price;
        ImageView image;
        Button details;
        Button bookmark;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {




            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);



            Log.d("ArrayListView", "ViewHolder");


            name=itemView.findViewById(R.id.resturantNameViewID);
            title=itemView.findViewById(R.id.resturantOfferTitleViewID);
            price=itemView.findViewById(R.id.resturantOfferPriceViewID);
            image=itemView.findViewById(R.id.resturantImageViewID);
            details=itemView.findViewById(R.id.deailsID);

        }
    }


}
