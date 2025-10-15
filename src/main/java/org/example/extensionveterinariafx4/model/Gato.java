package org.example.extensionveterinariafx4.model;

public class Gato extends Mascota {
    private EsIndoor esIndoor;
    private int horasSuenoPromedio;
    private String nivelIndependencia;

    public Gato() {}

    public Gato(String nombre, String raza, int edadMeses, double pesoKg, String codigoIdentificacion,
                Responsable responsable, EsIndoor esIndoor, int horasSuenoPromedio, String nivelIndependencia) {
        super(nombre, raza, edadMeses, pesoKg, codigoIdentificacion, responsable);
        this.esIndoor = esIndoor;
        this.horasSuenoPromedio = horasSuenoPromedio;
        this.nivelIndependencia = nivelIndependencia;
    }

    public Gato(String nombre, String raza, int edadMeses, double pesoKg, String codigoIdentificacion, Responsable responsable) {
        super(nombre, raza, edadMeses, pesoKg, codigoIdentificacion, responsable);
        this.esIndoor = EsIndoor.INDOOR;
        this.horasSuenoPromedio = 14;
        this.nivelIndependencia = "Media";
    }

    public EsIndoor getEsIndoor() { return esIndoor; }
    public void setEsIndoor(EsIndoor esIndoor) { this.esIndoor = esIndoor; }

    public int getHorasSuenoPromedio() { return horasSuenoPromedio; }
    public void setHorasSuenoPromedio(int horasSuenoPromedio) { this.horasSuenoPromedio = horasSuenoPromedio; }

    public String getNivelIndependencia() { return nivelIndependencia; }
    public void setNivelIndependencia(String nivelIndependencia) { this.nivelIndependencia = nivelIndependencia; }
}
