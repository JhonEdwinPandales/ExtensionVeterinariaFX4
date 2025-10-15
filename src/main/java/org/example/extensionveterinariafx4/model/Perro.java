package org.example.extensionveterinariafx4.model;

public class Perro extends Mascota {
    private Tamano tamano;
    private String nivelAdiestramiento;
    private boolean necesitaPaseosDiarios;

    public Perro() {}

    public Perro(String nombre, String raza, int edadMeses, double pesoKg, String codigoIdentificacion,
                 Responsable responsable, Tamano tamano, String nivelAdiestramiento, boolean necesitaPaseosDiarios) {
        super(nombre, raza, edadMeses, pesoKg, codigoIdentificacion, responsable);
        this.tamano = tamano;
        this.nivelAdiestramiento = nivelAdiestramiento;
        this.necesitaPaseosDiarios = necesitaPaseosDiarios;
    }

    public Tamano getTamano() { return tamano; }
    public void setTamano(Tamano tamano) { this.tamano = tamano; }

    public String getNivelAdiestramiento() { return nivelAdiestramiento; }
    public void setNivelAdiestramiento(String nivelAdiestramiento) { this.nivelAdiestramiento = nivelAdiestramiento; }

    public boolean isNecesitaPaseosDiarios() { return necesitaPaseosDiarios; }
    public void setNecesitaPaseosDiarios(boolean necesitaPaseosDiarios) { this.necesitaPaseosDiarios = necesitaPaseosDiarios; }
}
