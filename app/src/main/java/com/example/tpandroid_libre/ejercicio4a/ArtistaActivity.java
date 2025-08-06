package com.example.tpandroid_libre.ejercicio4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

        // Toolbar buttons
        ImageView leftIcon = findViewById(R.id.ej4a_toolbar_left_icon);
        TextView titleToolbar = findViewById(R.id.ej4a_toolbar_title);

        titleToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtistaActivity.this, Ejercicio4aActivity.class);
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

        int idArtista = getIntent().getIntExtra("idArtista", -1);
        String nombreArtista = getIntent().getStringExtra("nombreArtista");

        LinearLayout obrasContainer = findViewById(R.id.obras_container);
        TextView tv = findViewById(R.id.artista_text);
        tv.setText(nombreArtista);

        DBHelper db = new DBHelper(this);
        ArrayList<Obra> obras = db.getAllObrasFromArtista(idArtista);

        for (Obra obra : obras) {
            LinearLayout contenedor = new LinearLayout(this);
            contenedor.setOrientation(LinearLayout.VERTICAL);
            contenedor.setPadding(0, 0, 0, 24);

            ImageView img = new ImageView(this);
            img.setImageResource(obra.getPath());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(350, 350);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            img.setLayoutParams(params);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // Intent a imagen completa
//            img.setOnClickListener(v -> {
//                Intent intent = new Intent(this, ObraDetalleActivity.class);
//                intent.putExtra("obraId", obra.getId());
//                startActivity(intent);
//            });

            // Version 2
            img.setOnClickListener(v -> {
                Intent intent = new Intent(this, ObraCompletaActivity.class);
                int position = obras.indexOf(obra);
                intent.putExtra("obraId", position);
                intent.putParcelableArrayListExtra("obras", obras);
                startActivity(intent);
            });

            TextView obraInfo = new TextView(this);
            obraInfo.setText(obra.getNombre());
            obraInfo.setTextSize(16);
            obraInfo.setGravity(Gravity.CENTER_HORIZONTAL);
            obraInfo.setPadding(0, 8, 0, 0);

            contenedor.addView(img);
            contenedor.addView(obraInfo);
            obrasContainer.addView(contenedor);
        }
    }
}