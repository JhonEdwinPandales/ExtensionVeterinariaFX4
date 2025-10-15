package org.example.extensionveterinariafx4.model;

import java.time.LocalDate;

public class Consulta {
    private String id;
    private LocalDate fecha;
    private Mascota mascota;
    private TipoConsulta tipo;
    private double valorBase;
    private double costoFinal;

    public Consulta() {}

    public Consulta(String id, LocalDate fecha, Mascota mascota, TipoConsulta tipo, double valorBase) {
        this.id = id;
        this.fecha = fecha;
        this.mascota = mascota;
        this.tipo = tipo;
        this.valorBase = valorBase;
        this.costoFinal = mascota != null ? mascota.calcularCostoConsulta(tipo) : valorBase;
    }

    public TipoConsulta getTipo() { return tipo; }
    public void setTipo(TipoConsulta tipo) { this.tipo = tipo; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    public int getPrioridad() {
        return tipo == TipoConsulta.URGENCIA ? 1 : 2;
    }

    // getters/setters...
}
