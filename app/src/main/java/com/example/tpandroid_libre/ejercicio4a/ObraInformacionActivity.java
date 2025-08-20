package com.example.tpandroid_libre.ejercicio4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Obra;

public class ObraInformacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_obra_informacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Toolbar buttons
        ImageView leftIcon = findViewById(R.id.ej4a_toolbar_left_icon);
        TextView titleToolbar = findViewById(R.id.ej4a_toolbar_title);

        titleToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ObraInformacionActivity.this, Ejercicio4aActivity.class);
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


        Obra obra = getIntent().getParcelableExtra("obra");

        if (obra == null) {
            Toast.makeText(this, "No se pudo cargar la información de la obra", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView tvNombre = findViewById(R.id.obra_nombre);
        TextView tvDescripcion = findViewById(R.id.obra_descripcion);
        TextView tvAnio = findViewById(R.id.obra_anio);
        TextView tvPrecioEstimado = findViewById(R.id.obra_precio_estimado);

        tvNombre.setText("Nombre: " + obra.getNombre());
        tvDescripcion.setText("Descripción: " + obra.getDescripcion());
        tvAnio.setText("Fecha: " + obra.getFecha());
        tvPrecioEstimado.setText("Precio estimado: " + obra.getPrecioEstimado());
    }
}