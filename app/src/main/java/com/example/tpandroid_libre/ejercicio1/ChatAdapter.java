package com.example.tpandroid_libre.ejercicio1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tpandroid_libre.R;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<String> nombres;
    private List<String> descripciones;

    public ChatAdapter(List<String> nombres, List<String> descripciones) {
        this.nombres = nombres;
        this.descripciones = descripciones;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView descripcion;

        public ChatViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.txt_name);
            descripcion = v.findViewById(R.id.txt_description);
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app, parent, false);
        return new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.nombre.setText(nombres.get(position));
        holder.descripcion.setText(descripciones.get(position));
    }

    @Override
    public int getItemCount() {
        return nombres.size();
    }
}