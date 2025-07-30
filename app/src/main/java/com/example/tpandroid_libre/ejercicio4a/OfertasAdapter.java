package com.example.tpandroid_libre.ejercicio4a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Oferta;
import java.util.List;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.ViewHolder> {

    private final List<Oferta> lista;

    public OfertasAdapter(List<Oferta> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oferta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Oferta oferta = lista.get(position);
        holder.txtInfo.setText("Obra ID: " + oferta.getIdObra() +
                "\nComprador: " + oferta.getComprador() +
                "\nFecha: " + oferta.getFechaOferta() +
                "\nPrecio: $" + oferta.getMonto());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtInfo = itemView.findViewById(R.id.txt_oferta_info);
        }
    }
}
