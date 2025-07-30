package com.example.tpandroid_libre.ejercicio4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.tpandroid_libre.R;

public class Ejercicio4aActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio4a);
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
                Intent intent = new Intent(Ejercicio4aActivity.this, Ejercicio4aActivity.class);
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

        // Cover img
        ImageView imageView = findViewById(R.id.ej4a_imgCover);
        imageView.setImageResource(R.drawable.img_nikke);

        Button btnEj4Generos = findViewById(R.id.ej4a_btnGeneros);
        btnEj4Generos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a GenerosActivity
                Intent intent = new Intent(Ejercicio4aActivity.this, GenerosActivity.class);
                startActivity(intent);
            }
        });

        Button btnEj4Ofertas = findViewById(R.id.ej4a_btnOfertas);
        btnEj4Ofertas.setOnClickListener(v -> {
            Intent intent = new Intent(Ejercicio4aActivity.this, OfertasActivity.class);
            startActivity(intent);
        });
    }

}