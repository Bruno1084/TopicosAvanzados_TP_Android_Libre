package com.example.tpandroid_libre.ejercicio4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tpandroid_libre.DBHelper;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Obra;

public class ObraDetalleActivity extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obra_detalle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Toolbar buttons
        ImageView leftIcon = findViewById(R.id.ej4a_toolbar_left_icon);
        TextView titleToolbar = findViewById(R.id.ej4a_toolbar_title);

        titleToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ObraDetalleActivity.this, Ejercicio4aActivity.class);
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

        int obraId = getIntent().getIntExtra("obraId", -1);
        db = new DBHelper(this);
        Obra obra = db.getObraById(obraId);

        ImageView imageView = findViewById(R.id.obra_imagen);
        TextView titulo = findViewById(R.id.obra_titulo);
        Button btnInfo = findViewById(R.id.btn_info);
        Button btnOfertar = findViewById(R.id.btn_ofertar);
        Button btnOfertasObra = findViewById(R.id.btn_ofertasObra);

        imageView.setImageResource(obra.getPath());
        titulo.setText(obra.getNombre());

        btnInfo.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Información de la Obra")
                    .setMessage("Título: " + obra.getNombre() + "\n" +
                            "Fecha: " + obra.getFecha() + "\n" +
                            "Descripción: " + obra.getDescripcion() + "\n" +
                            "Precio estimado: " + obra.getDescripcion() + "\n" +
                            "Dueño actual: " + obra.getDuenio() + "\n")
                    .setPositiveButton("Aceptar", null)
                    .show();
        });

        btnOfertar.setOnClickListener(v -> {
            Intent intent = new Intent(this, OfertarActivity.class);
            intent.putExtra("idObra", obra.getId());
            intent.putExtra("nombreObra", obra.getNombre());
            startActivity(intent);
        });

        btnOfertasObra.setOnClickListener(v -> {
            Intent intent = new Intent(this, OfertasObraActivity.class);
            intent.putExtra("idObra", obra.getId());
            startActivity(intent);
        });
    }
}
