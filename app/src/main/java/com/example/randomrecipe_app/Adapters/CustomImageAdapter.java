package com.example.randomrecipe_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomrecipe_app.Database.StoreImage;
import com.example.randomrecipe_app.Listeners.UriActionListener;
import com.example.randomrecipe_app.R;

import java.util.List;

public class CustomImageAdapter extends RecyclerView.Adapter<CustomImageViewHolder>{
    Context context;
    List<StoreImage> list;
    UriActionListener listener;

    public CustomImageAdapter(Context context, List<StoreImage> list, UriActionListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomImageViewHolder(LayoutInflater.from(context).inflate(R.layout.post_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomImageViewHolder holder, int position) {
        holder.text_uri.setText(list.get(position).getUri());
        holder.text_uri.setSelected(true);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongclick(list.get(holder.getAdapterPosition()), holder.cardView);
                return true;
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(holder.text_uri.getText().toString()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CustomImageViewHolder extends RecyclerView.ViewHolder {
    TextView text_uri;
    CardView cardView;

    public CustomImageViewHolder(@NonNull View itemView) {
        super(itemView);
        text_uri = itemView.findViewById(R.id.text_uri);
        cardView = itemView.findViewById(R.id.card_image);
    }
}
