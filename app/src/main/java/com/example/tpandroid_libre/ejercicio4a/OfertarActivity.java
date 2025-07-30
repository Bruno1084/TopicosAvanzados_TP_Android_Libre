package com.example.tpandroid_libre.ejercicio4a;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tpandroid_libre.DBHelper;
import com.example.tpandroid_libre.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OfertarActivity extends AppCompatActivity {
    private int idObra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Toolbar buttons
        ImageView leftIcon = findViewById(R.id.ej4a_toolbar_left_icon);
        TextView titleToolbar = findViewById(R.id.ej4a_toolbar_title);

        titleToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfertarActivity.this, Ejercicio4aActivity.class);
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

        idObra = getIntent().getIntExtra("idObra", -1);
        if (idObra == -1) {
            Toast.makeText(this, "Error: obra no encontrada", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        EditText etNombre = findViewById(R.id.et_nombre);
        EditText etCorreo = findViewById(R.id.et_correo);
        EditText etTelefono = findViewById(R.id.et_telefono);
        EditText etOferta = findViewById(R.id.et_oferta);
        Button btnEnviar = findViewById(R.id.btn_enviar_oferta);

        btnEnviar.setOnClickListener(view -> {
            String nombre = etNombre.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();
            String ofertaStr = etOferta.getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || ofertaStr.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            float ofertaValor;
            try {
                ofertaValor = Float.parseFloat(ofertaStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "El valor de la oferta no es válido", Toast.LENGTH_SHORT).show();
                return;
            }

            // Fecha actual
            String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            DBHelper db = new DBHelper(this);
            long resultado = db.insertOferta(idObra, fecha, nombre + " | " + correo + " | " + telefono, ofertaValor);

            if (resultado != -1) {
                Toast.makeText(this, "Oferta registrada con éxito", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Error al registrar la oferta", Toast.LENGTH_LONG).show();
            }
        });
    }
}
