package org.example.extensionveterinariafx4.model;

import java.time.LocalDate;

public class Consulta {
    private TipoConsulta tipo;
    private Mascota mascota;
    private LocalDate fecha;

    public Consulta() {}

    public Consulta(TipoConsulta tipo, Mascota mascota, LocalDate fecha) {
        this.tipo = tipo;
        this.mascota = mascota;
        this.fecha = fecha;
    }

    public int getPrioridad() {
        return (tipo == TipoConsulta.URGENCIA) ? 1 : 2;
    }

    // getters/setters
    public TipoConsulta getTipo() { return tipo; }
    public void setTipo(TipoConsulta tipo) { this.tipo = tipo; }
    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
