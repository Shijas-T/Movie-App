package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder>{
    //Initialise the list item here
    private ArrayList<CastModel> arrayListAllCast;
    //Creating context for toast
    private Context context;

    public CastAdapter(ArrayList<CastModel> arrayListAllCast, Context context) {
        this.arrayListAllCast = arrayListAllCast;
        this.context = context;
    }

    //View holder(it calls the created recycler View)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cast,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //All the click listener is done here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewCastName.setText(arrayListAllCast.get(position).getCastName());
        Picasso.with(context).load(arrayListAllCast.get(position).getCastImageUrl()).into(holder.imageViewCast);

    }

    @Override
    public int getItemCount() {
        return arrayListAllCast.size();
    }

    //Every view inside the recycler view is declared and initialised here
    public class ViewHolder extends RecyclerView.ViewHolder{
        //Declaration
        private TextView textViewCastName;
        private ImageView imageViewCast;
        private LinearLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCastName = itemView.findViewById(R.id.tv_cast_name);
            imageViewCast =  itemView.findViewById(R.id.ic_cast_image);
            //The container
            parent = itemView.findViewById(R.id.single_cast);
        }
    }
}
