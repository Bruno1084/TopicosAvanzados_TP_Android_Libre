package com.example.tpandroid_libre.ejercicio1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tpandroid_libre.R;
import java.util.Arrays;
import java.util.List;

public class TablaActivity extends AppCompatActivity {

    private AppGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_ej1_fig2);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        //Relleno para mostrar el scroll
        List<AppItem> apps = Arrays.asList(

                new AppItem("WhatsApp", R.drawable.img_whatsapp_icon),
                new AppItem("Facebook", R.drawable.img_facebook_icon),
                new AppItem("Clima", R.drawable.img_weather_icon),
                new AppItem("Reloj", R.drawable.img_clock_icon),

                new AppItem("WhatsApp", R.drawable.img_whatsapp_icon),
                new AppItem("Facebook", R.drawable.img_facebook_icon),
                new AppItem("Clima", R.drawable.img_weather_icon),
                new AppItem("Reloj", R.drawable.img_clock_icon),

                new AppItem("WhatsApp", R.drawable.img_whatsapp_icon),
                new AppItem("Facebook", R.drawable.img_facebook_icon),
                new AppItem("Clima", R.drawable.img_weather_icon),
                new AppItem("Reloj", R.drawable.img_clock_icon),

                new AppItem("WhatsApp", R.drawable.img_whatsapp_icon),
                new AppItem("Facebook", R.drawable.img_facebook_icon),
                new AppItem("Clima", R.drawable.img_weather_icon),
                new AppItem("Reloj", R.drawable.img_clock_icon),

                new AppItem("WhatsApp", R.drawable.img_whatsapp_icon),
                new AppItem("Facebook", R.drawable.img_facebook_icon),
                new AppItem("Clima", R.drawable.img_weather_icon),
                new AppItem("Reloj", R.drawable.img_clock_icon),

                new AppItem("WhatsApp", R.drawable.img_whatsapp_icon),
                new AppItem("Facebook", R.drawable.img_facebook_icon),
                new AppItem("Clima", R.drawable.img_weather_icon),
                new AppItem("Reloj", R.drawable.img_clock_icon),

                new AppItem("WhatsApp", R.drawable.img_whatsapp_icon),
                new AppItem("Facebook", R.drawable.img_facebook_icon),
                new AppItem("Clima", R.drawable.img_weather_icon),
                new AppItem("Reloj", R.drawable.img_clock_icon),

                new AppItem("WhatsApp", R.drawable.img_whatsapp_icon),
                new AppItem("Facebook", R.drawable.img_facebook_icon),
                new AppItem("Clima", R.drawable.img_weather_icon),
                new AppItem("Reloj", R.drawable.img_clock_icon),

                new AppItem("WhatsApp", R.drawable.img_whatsapp_icon),
                new AppItem("Facebook", R.drawable.img_facebook_icon),
                new AppItem("Clima", R.drawable.img_weather_icon),
                new AppItem("Reloj", R.drawable.img_clock_icon)
        );

        adapter = new AppGridAdapter(apps);
        recyclerView.setAdapter(adapter);

        // Elimina el título por defecto
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Buscar aplicaciones...");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu_ej1_fig2, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search_ej1_fig2);
        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setQueryHint("Buscar aplicaciones...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        return true;
    }
}
