package com.example.randomrecipe_app.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomrecipe_app.Models.Recipe;
import com.example.randomrecipe_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>{
    Context context;
    List<Recipe> list;

    public CustomAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text_title.setText(list.get(position).title);
        holder.text_title.setSelected(true);
        holder.text_servings.setText(list.get(position).servings + " Servings");
        holder.text_like.setText(list.get(position).aggregateLikes + " Likes");
        holder.text_time.setText(list.get(position).readyInMinutes + " Minutes");
        Picasso.get().load(list.get(position).image).into(holder.image_food);

        holder.image_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sourceUrl = list.get(position).sourceUrl;
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sourceUrl));
                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView text_title, text_servings, text_like, text_time;
    ImageView image_food;
    CardView list_container;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_title = itemView.findViewById(R.id.text_title);
        text_servings = itemView.findViewById(R.id.text_servings);
        text_like = itemView.findViewById(R.id.text_like);
        text_time = itemView.findViewById(R.id.text_time);
        image_food = itemView.findViewById(R.id.image_food);
        list_container = itemView.findViewById(R.id.list_container);
    }
}
