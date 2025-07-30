package com.example.tpandroid_libre.ejercicio1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tpandroid_libre.R;
import java.util.ArrayList;
import java.util.List;

public class AppGridAdapter extends RecyclerView.Adapter<AppGridAdapter.ViewHolder> {

    private List<AppItem> originalList;
    private List<AppItem> filteredList;

    public AppGridAdapter(List<AppItem> apps) {
        this.originalList = apps;
        this.filteredList = new ArrayList<>(apps);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppItem item = filteredList.get(position);
        holder.icon.setImageResource(item.getIconRes());
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            for (AppItem item : originalList) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.img_app_icon);
            name = itemView.findViewById(R.id.txt_app_name);
        }
    }
}