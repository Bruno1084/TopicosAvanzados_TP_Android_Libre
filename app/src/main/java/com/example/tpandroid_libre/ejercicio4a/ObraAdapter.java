package com.example.tpandroid_libre.ejercicio4a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Obra;
import java.util.ArrayList;

public class ObraAdapter extends RecyclerView.Adapter<ObraAdapter.ObraViewHolder> {
    private ArrayList<Obra> obras;
    private OnObraClickListener listener;

    public interface OnObraClickListener {
        void onObraClick(int position);
    }

    public ObraAdapter(ArrayList<Obra> obras, OnObraClickListener listener) {
        this.obras = obras;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ObraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_obra, parent, false);
        return new ObraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObraViewHolder holder, int position) {
        Obra obra = obras.get(position);
        holder.img.setImageResource(obra.getPath());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onObraClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    static class ObraViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ObraViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.obra_img);
        }
    }
}
