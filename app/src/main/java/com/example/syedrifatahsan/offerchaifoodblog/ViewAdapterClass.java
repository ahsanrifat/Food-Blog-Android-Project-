package com.example.syedrifatahsan.offerchaifoodblog;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.syedrifatahsan.offerchaifoodblog.ModelViewAdd;
import com.example.syedrifatahsan.offerchaifoodblog.R;

import java.util.List;

public class ViewAdapterClass extends RecyclerView.Adapter<ViewAdapterClass.Viewholder> {



    List <ModelViewAdd> modelViewAdds;

    public ViewAdapterClass(List<ModelViewAdd> modelViewAdds) {
        Log.d("mmmHomeBlog", "ViewAdapter Created");
        this.modelViewAdds = modelViewAdds;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_view_layout,viewGroup,false);
        Viewholder v=new Viewholder(view);
        Log.d("mmmHomeBlog", "ViewHolderOncreate Created");
        return v;
    }



    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {


        Log.d("mmmHomeBlog", "ViewHolderBuinding Created");

        String name=modelViewAdds.get(i).getName();
        String title=modelViewAdds.get(i).getTitle();
        String price=modelViewAdds.get(i).getPrice();
        String image=modelViewAdds.get(i).getImage();
        String key=modelViewAdds.get(i).getKey();

        viewholder.setData(name,title,price,image,key);

    }

    @Override
    public int getItemCount() {
        return modelViewAdds.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        TextView name;
        TextView key;
        TextView title;
        TextView price;
        ImageView image;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Log.d("mmmHomeBlog", "Viewholder class's constructor");
            name=itemView.findViewById(R.id.resturantNameViewID);
            title=itemView.findViewById(R.id.resturantOfferTitleViewID);
            price=itemView.findViewById(R.id.resturantOfferPriceViewID);
            image=itemView.findViewById(R.id.resturantImageViewID);
            //key=itemView.findViewById(R.id.key);
        }


        public  void setData(String Aname,String Atitle,String Aprice,String Aimage,String Akey){

            name.setText(Aname);
            title.setText(Atitle);
            price.setText(Aprice);
            image.setImageURI(Uri.parse(Aimage));
            key.setText(Akey);

        }
    }
}
