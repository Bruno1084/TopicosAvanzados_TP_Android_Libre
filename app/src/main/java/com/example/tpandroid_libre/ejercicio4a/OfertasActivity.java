package com.example.tpandroid_libre.ejercicio4a;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tpandroid_libre.DBHelper;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Oferta;
import java.util.List;

public class OfertasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OfertasAdapter adapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Toolbar buttons
        ImageView leftIcon = findViewById(R.id.ej4a_toolbar_left_icon);
        TextView titleToolbar = findViewById(R.id.ej4a_toolbar_title);

        recyclerView = findViewById(R.id.recycler_ofertas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
        List<Oferta> ofertas = dbHelper.getAllOfertas();

        adapter = new OfertasAdapter(ofertas);
        recyclerView.setAdapter(adapter);
    }
}
