package com.example.tpandroid_libre.ejercicio4a.Clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Obra implements Parcelable {
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private long precioEstimado;
    private String duenio;
    private int path;

    public Obra(int id, String nombre, String descripcion, String fecha, long precioEstimado, String duenio, int path) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.precioEstimado = precioEstimado;
        this.duenio = duenio;
        this.path = path;
    }

    // Implementación de interfaz Parceable
    protected Obra(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        descripcion = in.readString();
        fecha = in.readString();
        precioEstimado = in.readLong();
        duenio = in.readString();
        path = in.readInt();
    }

    public static final Creator<Obra> CREATOR = new Creator<>() {
        @Override
        public Obra createFromParcel(Parcel in) {
            return new Obra(in);
        }

        @Override
        public Obra[] newArray(int size) {
            return new Obra[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(descripcion);
        parcel.writeString(fecha);
        parcel.writeLong(precioEstimado);
        parcel.writeString(duenio);
        parcel.writeInt(path);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(long precioEstimado) {
        this.precioEstimado = precioEstimado;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}

