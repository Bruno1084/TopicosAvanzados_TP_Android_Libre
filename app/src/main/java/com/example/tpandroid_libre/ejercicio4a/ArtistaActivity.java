package com.example.tpandroid_libre.ejercicio4a;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tpandroid_libre.DBHelper;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Obra;
import java.util.ArrayList;

public class ArtistaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_artista);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Toolbar
        ImageView leftIcon = findViewById(R.id.ej4a_toolbar_left_icon);
        TextView titleToolbar = findViewById(R.id.ej4a_toolbar_title);

        titleToolbar.setOnClickListener(v -> {
            Intent intent = new Intent(ArtistaActivity.this, Ejercicio4aActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        leftIcon.setOnClickListener(v -> finish());

        int idArtista = getIntent().getIntExtra("idArtista", -1);
        String nombreArtista = getIntent().getStringExtra("nombreArtista");

        TextView tv = findViewById(R.id.artista_text);
        tv.setText(nombreArtista);

        DBHelper db = new DBHelper(this);
        ArrayList<Obra> obras = db.getAllObrasFromArtista(idArtista);

        RecyclerView recyclerView = findViewById(R.id.obras_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ObraAdapter adapter = new ObraAdapter(obras, position -> {
            Intent intent = new Intent(this, ObraCompletaActivity.class);
            intent.putExtra("obraId", position);
            intent.putParcelableArrayListExtra("obras", obras);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
