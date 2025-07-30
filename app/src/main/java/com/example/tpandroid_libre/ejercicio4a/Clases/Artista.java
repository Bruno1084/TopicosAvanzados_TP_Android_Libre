package com.example.tpandroid_libre.ejercicio4a.Clases;

import java.io.Serializable;
import java.util.List;

public class Artista implements Serializable {
    private String nombre;
    private List<Obra> obras;

    public Artista(String nombre, List<Obra> obras) {
        this.nombre = nombre;
        this.obras = obras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
}
