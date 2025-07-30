package com.example.tpandroid_libre.ejercicio3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.bumptech.glide.Glide;
import com.example.tpandroid_libre.R;

public class Adapter extends RecyclerView.Adapter<Adapter.RecyclerViewHolder> {

    private final Context context;
    private final ArrayList<String> imagePathArrayList;

    public Adapter(Context context, ArrayList<String> imagePathArrayList) {
        this.context = context;
        this.imagePathArrayList = imagePathArrayList;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageIV;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIV = itemView.findViewById(R.id.idIVImage);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String path = imagePathArrayList.get(position);
        Uri imageUri = Uri.parse(path);
        Glide.with(context).load(imageUri).into(holder.imageIV);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ImageDetailActivity.class);
            intent.putStringArrayListExtra("imageList", imagePathArrayList);
            intent.putExtra("position", holder.getAdapterPosition());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return imagePathArrayList.size();
    }
}