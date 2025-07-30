package com.example.tpandroid_libre.ejercicio4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tpandroid_libre.DBHelper;
import com.example.tpandroid_libre.R;
import java.util.List;

public class ArtistasPorGeneroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistas_por_genero);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Toolbar buttons
        ImageView leftIcon = findViewById(R.id.ej4a_toolbar_left_icon);
        TextView titleToolbar = findViewById(R.id.ej4a_toolbar_title);

        titleToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtistasPorGeneroActivity.this, Ejercicio4aActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                // finish();
            }
        });

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout layout = findViewById(R.id.obras_linear_layout);

        String generoSeleccionado = getIntent().getStringExtra("genero");

        DBHelper dbHelper = new DBHelper(this);
        List<String> artistas = dbHelper.getAllArtistasFromGenero(generoSeleccionado);


        for (String artista : artistas) {
            String[] partes = artista.split(" - ");
            if (partes.length < 1) continue;
            int idArtista = Integer.parseInt(partes[0]);
            String nombreArtista = partes[1];
            String nacionalidadArtista = partes[2];

            Button btn = new Button(this);
            btn.setText(nombreArtista);
            btn.setOnClickListener(v -> {
                Intent intent = new Intent(ArtistasPorGeneroActivity.this, ArtistaActivity.class);
                intent.putExtra("idArtista", idArtista);
                intent.putExtra("nombreArtista", nombreArtista);
                intent.putExtra("nacionalidadArtista", nacionalidadArtista);
                startActivity(intent);
            });
            layout.addView(btn);
        }

    }
}
