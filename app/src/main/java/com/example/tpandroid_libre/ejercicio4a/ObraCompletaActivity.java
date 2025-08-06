package com.example.tpandroid_libre.ejercicio4a;

import android.os.Bundle;
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
    private int obraId = 0;
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


        obraId = getIntent().getIntExtra("obraId", -1);
        obras = getIntent().getParcelableArrayListExtra("obras");

        if (obras == null || obras.isEmpty()) {
            Toast.makeText(this, "No se pudo cargar la lista de obras", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ObraCompletaAdapter(obras));
        viewPager.setCurrentItem(obraId, false);

        TextView obraNombre = findViewById(R.id.obraNombre);
        TextView obraDescripcion = findViewById(R.id.obraDescripcion);
        TextView obraFecha = findViewById(R.id.obraFecha);
        TextView obraPrecioEstimado = findViewById(R.id.obraPrecioEstimado);
        TextView obraDuenio = findViewById(R.id.obraDuenio);
        Obra obra = obras.get(obraId);

        obraNombre.setText("Nombre: " + obra.getNombre());
        obraDescripcion.setText("Descripción: " + obra.getDescripcion());
        obraFecha.setText("Fecha: " + obra.getFecha());
        obraPrecioEstimado.setText("Precio: " + obra.getPrecioEstimado());
        obraDuenio.setText("Dueño: " + obra.getDuenio());

        // Modifica las descripciones al cambiar de obra
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                obraNombre.setText("Nombre: " + obras.get(position).getNombre());
                obraDescripcion.setText("Descripción: " + obras.get(position).getDescripcion());
                obraFecha.setText("Fecha: " + obras.get(position).getFecha());
                obraPrecioEstimado.setText("Precio: " + String.valueOf(obras.get(position).getPrecioEstimado()));
                obraDuenio.setText("Dueño: " + obras.get(position).getDuenio());
            }
        });
    }
}