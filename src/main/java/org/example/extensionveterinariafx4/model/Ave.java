package org.example.extensionveterinariafx4.model;

public class Ave extends Mascota {
    private String tipoPlumaje;
    private boolean puedeVolar;
    private int cantidadImitaciones;

    public Ave() {}

    public Ave(String nombre, String raza, int edadMeses, double pesoKg, String codigoIdentificacion,
               Responsable responsable, String tipoPlumaje, boolean puedeVolar, int cantidadImitaciones) {
        super(nombre, raza, edadMeses, pesoKg, codigoIdentificacion, responsable);
        this.tipoPlumaje = tipoPlumaje;
        this.puedeVolar = puedeVolar;
        this.cantidadImitaciones = cantidadImitaciones;
    }

    public String getTipoPlumaje() { return tipoPlumaje; }
    public void setTipoPlumaje(String tipoPlumaje) { this.tipoPlumaje = tipoPlumaje; }

    public boolean isPuedeVolar() { return puedeVolar; }
    public void setPuedeVolar(boolean puedeVolar) { this.puedeVolar = puedeVolar; }

    public int getCantidadImitaciones() { return cantidadImitaciones; }
    public void setCantidadImitaciones(int cantidadImitaciones) { this.cantidadImitaciones = cantidadImitaciones; }
}
