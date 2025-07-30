package com.example.tpandroid_libre.ejercicio4a.Clases;

public class  Oferta {
    private int id;
    private int idObra;
    private double monto;
    private String fechaOferta;
    private String comprador;

    public Oferta(int id, int idObra, double monto, String fechaOferta, String comprador) {
        this.id = id;
        this.idObra = idObra;
        this.monto = monto;
        this.fechaOferta = fechaOferta;
        this.comprador = comprador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaOferta() {
        return fechaOferta;
    }

    public void setFechaOferta(String fechaOferta) {
        this.fechaOferta = fechaOferta;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }
}
