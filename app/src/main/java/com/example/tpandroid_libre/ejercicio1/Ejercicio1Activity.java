package com.example.tpandroid_libre.ejercicio1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.tpandroid_libre.R;

public class Ejercicio1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Btn Fig. 1 Formulario
        Button btnFig1 = findViewById(R.id.btn_ej1_fig1);
        btnFig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a la figura 1
                Intent intent = new Intent(Ejercicio1Activity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        //Btn Fig. 2 Tabla
        Button btnFig2 = findViewById(R.id.btn_ej1_fig2);
        btnFig2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a la figura 2
                Intent intent = new Intent(Ejercicio1Activity.this, TablaActivity.class);
                startActivity(intent);
            }
        });

        //Btn Fig. 3 Lista
        Button btnFig3 = findViewById(R.id.btn_ej1_fig3);
        btnFig3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a la figura 3
                Intent intent = new Intent(Ejercicio1Activity.this, ListaActivity.class);
                startActivity(intent);
            }
        });

    }
}