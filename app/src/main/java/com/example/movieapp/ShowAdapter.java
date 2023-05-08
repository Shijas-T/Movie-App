package com.example.movieapp;

import android.content.Context;
import android.content.Intent;
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

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder>{

    //Initialise the list item here
    private ArrayList<ShowModel> arrayListAllShow;
    //Creating context for toast
    private Context context;

    public ShowAdapter(ArrayList<ShowModel> arrayListAllShow, Context context) {
        this.arrayListAllShow = arrayListAllShow;
        this.context = context;
    }

    //View holder(it calls the created recycler View)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_show,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //All the click listener is done here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewShowName.setText(arrayListAllShow.get(position).getShowName());
        Picasso.with(context).load(arrayListAllShow.get(position).getShowImageUrl()).into(holder.imageViewShow);

        //On click listener
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetailActivity.class);
                intent.putExtra("show_id",arrayListAllShow.get(position).getShowId());
                intent.putExtra("show_language",arrayListAllShow.get(position).getShowLanguage());
                intent.putExtra("show_premiered",arrayListAllShow.get(position).getShowPremiered());
                intent.putExtra("show_summary",arrayListAllShow.get(position).getShowSummary());
                intent.putExtra("show_img_url",arrayListAllShow.get(position).getShowImageUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListAllShow.size();
    }

    //Every view inside the recycler view is declared and initialised here
    public class ViewHolder extends RecyclerView.ViewHolder{
        //Declaration
        private TextView textViewShowName;
        private ImageView imageViewShow;
        private LinearLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewShowName = itemView.findViewById(R.id.tv_show_name);
            imageViewShow =  itemView.findViewById(R.id.ic_show_image);
            //The container
            parent = itemView.findViewById(R.id.single_show);
        }
    }
}
