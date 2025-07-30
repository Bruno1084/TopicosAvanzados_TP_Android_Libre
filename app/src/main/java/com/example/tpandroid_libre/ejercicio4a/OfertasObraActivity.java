package com.example.tpandroid_libre.ejercicio4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tpandroid_libre.DBHelper;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio4a.Clases.Oferta;
import java.util.List;

public class OfertasObraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas_obra);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Toolbar buttons
        ImageView leftIcon = findViewById(R.id.ej4a_toolbar_left_icon);
        TextView titleToolbar = findViewById(R.id.ej4a_toolbar_title);

        titleToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfertasObraActivity.this, Ejercicio4aActivity.class);
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

        int idObra = getIntent().getIntExtra("idObra", -1);
        LinearLayout container = findViewById(R.id.ofertas_container);

        DBHelper db = new DBHelper(this);
        List<Oferta> ofertas = db.getOfertasFromObra(idObra);

        if (ofertas.isEmpty()) {
            TextView tv = new TextView(this);
            tv.setText("No hay ofertas para esta obra.");
            container.addView(tv);
        } else {
            for (Oferta oferta : ofertas) {
                TextView item = new TextView(this);
                item.setText("• Comprador: " + oferta.getComprador() +
                        "\n  Fecha: " + oferta.getFechaOferta() +
                        "\n  Precio ofrecido: $" + oferta.getMonto());
                item.setPadding(0, 16, 0, 16);
                container.addView(item);
            }
        }
    }
}
