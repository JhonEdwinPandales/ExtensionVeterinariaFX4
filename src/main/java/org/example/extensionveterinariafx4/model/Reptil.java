package org.example.extensionveterinariafx4.model;

public class Reptil extends Mascota {
    private TipoHabitat tipoHabitat;
    private double temperaturaOptima;
    private String nivelPeligrosidad;

    public Reptil() {}

    public Reptil(String nombre, String raza, int edadMeses, double pesoKg, String codigoIdentificacion,
                  Responsable responsable, TipoHabitat tipoHabitat, double temperaturaOptima, String nivelPeligrosidad) {
        super(nombre, raza, edadMeses, pesoKg, codigoIdentificacion, responsable);
        this.tipoHabitat = tipoHabitat;
        this.temperaturaOptima = temperaturaOptima;
        this.nivelPeligrosidad = nivelPeligrosidad;
    }

    public TipoHabitat getTipoHabitat() { return tipoHabitat; }
    public void setTipoHabitat(TipoHabitat tipoHabitat) { this.tipoHabitat = tipoHabitat; }

    public double getTemperaturaOptima() { return temperaturaOptima; }
    public void setTemperaturaOptima(double temperaturaOptima) { this.temperaturaOptima = temperaturaOptima; }

    public String getNivelPeligrosidad() { return nivelPeligrosidad; }
    public void setNivelPeligrosidad(String nivelPeligrosidad) { this.nivelPeligrosidad = nivelPeligrosidad; }
}
