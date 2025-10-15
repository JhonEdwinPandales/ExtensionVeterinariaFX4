package org.example.extensionveterinariafx4.model;

import java.util.Objects;

public class Responsable {
    private String nombreCompleto;
    private String numeroContacto;
    private String direccion;
    private int puntajeFidelidad;

    public Responsable() {}

    public Responsable(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Responsable(String nombreCompleto, String numeroContacto, String direccion) {
        this.nombreCompleto = nombreCompleto;
        this.numeroContacto = numeroContacto;
        this.direccion = direccion;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getNumeroContacto() { return numeroContacto; }
    public void setNumeroContacto(String numeroContacto) { this.numeroContacto = numeroContacto; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getPuntajeFidelidad() { return puntajeFidelidad; }
    public void setPuntajeFidelidad(int puntajeFidelidad) { this.puntajeFidelidad = puntajeFidelidad; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Responsable)) return false;
        Responsable that = (Responsable) o;
        return Objects.equals(nombreCompleto, that.nombreCompleto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreCompleto);
    }
}
