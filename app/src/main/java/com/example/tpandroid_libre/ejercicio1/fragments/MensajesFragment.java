package com.example.tpandroid_libre.ejercicio1.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tpandroid_libre.R;
import com.example.tpandroid_libre.ejercicio1.ChatAdapter;
import java.util.Arrays;
import java.util.List;

public class MensajesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mensajes, container, false);

        RecyclerView recycler = view.findViewById(R.id.recyclerview_ej1_fig3);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> chats = Arrays.asList(
                "Juan Doe",
                "Bruno Sosa",
                "David Pérez",
                "Adriana Castillo",
                "David",
                "Movistar",
                "+33 236 2535",
                "+56 234 3242"
        );

        List<String> descripciones = Arrays.asList(
                "J simply dummy text of t",
                "nter took ",
                "ase of Letraset sheets containing Lore",
                "A is a lo",
                "jaja",
                "Mensaje de Movistar",
                "Mensaje de relleno",
                "Probando ScrollView"
        );

        recycler.setAdapter(new ChatAdapter(chats, descripciones));
        return view;
    }
}