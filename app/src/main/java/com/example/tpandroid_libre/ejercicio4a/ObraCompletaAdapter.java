package com.example.tpandroid_libre.ejercicio4a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Obra;
import java.util.ArrayList;

public class ObraCompletaAdapter extends RecyclerView.Adapter<ObraCompletaAdapter.ViewHolder> {
    private ArrayList<Obra> obras;

    public ObraCompletaAdapter(ArrayList<Obra> obras) {
        this.obras = obras;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_obra_completa_imagen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Obra obra = obras.get(position);
        Glide.with(holder.imageView.getContext())
                .load(obra.getPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fullscreenImage);
        }
    }
}
