package org.example.extensionveterinariafx4.model;

import java.util.ArrayList;
import java.util.List;

public class ClinicaVeterinaria {
    private List<Mascota> mascotas;
    private List<Consulta> consultas;
    private List<Responsable> responsables;

    public ClinicaVeterinaria() {
        this.mascotas = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.responsables = new ArrayList<>();
    }

    public void registrarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public void registrarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void registrarResponsable(Responsable responsable) {
        responsables.add(responsable);
    }

    public List<Mascota> getMascotas() { return mascotas; }
    public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }

    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }

    public List<Responsable> getResponsables() { return responsables; }
    public void setResponsables(List<Responsable> responsables) { this.responsables = responsables; }
}
