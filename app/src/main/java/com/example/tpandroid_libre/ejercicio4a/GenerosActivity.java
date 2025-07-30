package com.example.tpandroid_libre.ejercicio4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.tpandroid_libre.DBHelper;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Genero;
import java.util.List;

public class GenerosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_generos);
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
                Intent intent = new Intent(GenerosActivity.this, Ejercicio4aActivity.class);
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

        LinearLayout linearLayout = findViewById(R.id.ej4a_linearLayout);

        DBHelper dbHelper = new DBHelper(this);
        List<Genero> generos = dbHelper.getAllGeneros();
        System.out.println(generos);

        for (Genero genero : generos) {
            Button button = getButton(genero.getNombre());
            linearLayout.addView(button);
        }
    }

    @NonNull
    private Button getButton(String genero) {
        Button button = new Button(this);
        button.setText(genero);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        button.setOnClickListener(v -> Toast.makeText(this, genero, Toast.LENGTH_SHORT).show());

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, ArtistasPorGeneroActivity.class);
            intent.putExtra("genero", genero);
            startActivity(intent);
        });
        return button;
    }
}