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
import androidx.viewpager2.widget.ViewPager2;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Obra;
import java.util.ArrayList;

public class ObraCompletaActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private ArrayList<Obra> obras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_obra_completa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_obra_completa), (v, insets) -> {
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
                Intent intent = new Intent(ObraCompletaActivity.this, Ejercicio4aActivity.class);
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
        obras = getIntent().getParcelableArrayListExtra("obras");

        if (obras == null || obras.isEmpty()) {
            Toast.makeText(this, "No se pudo cargar la lista de obras", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ObraCompletaAdapter(obras));
        viewPager.setCurrentItem(obraId, false);

        // Opciones de obra
        TextView btnObraInformacion = findViewById(R.id.btn_info);
        TextView btnObraOfertar = findViewById(R.id.btn_ofertar);
        TextView btnObraOfertas = findViewById(R.id.btn_ofertasObra);

        btnObraInformacion.setOnClickListener(v -> {
            int posicion = viewPager.getCurrentItem();
            Obra obraSeleccionada = obras.get(posicion);

            Intent intent = new Intent(this, ObraInformacionActivity.class);
            intent.putExtra("obra", obraSeleccionada);
            startActivity(intent);
        });

        btnObraOfertar.setOnClickListener(v -> {
            Intent intent = new Intent(this, OfertarActivity.class);
            intent.putExtra("idObra", obraId);
            startActivity(intent);
        });

        btnObraOfertas.setOnClickListener(v -> {
            Intent intent = new Intent(this, OfertasObraActivity.class);
            intent.putExtra("idObra", obraId);
            startActivity(intent);
        });
    } 
}