package org.example.extensionveterinariafx4.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Mascota {
    protected String nombre;
    protected String raza;
    protected int edadMeses;
    protected double pesoKg;
    protected String codigoIdentificacion;
    protected Responsable responsable;
    protected CategoriaEdad categoriaEdad;

    public Mascota() {}

    public Mascota(String nombre, String raza, int edadMeses, double pesoKg, String codigoIdentificacion, Responsable responsable) {
        this.nombre = nombre;
        this.raza = raza;
        this.edadMeses = edadMeses;
        this.pesoKg = pesoKg;
        this.codigoIdentificacion = codigoIdentificacion;
        this.responsable = responsable;
        this.categoriaEdad = calcularCategoriaEdad();
    }

    public CategoriaEdad calcularCategoriaEdad() {
        if (edadMeses < 12) return CategoriaEdad.CACHORRO_JOVEN;
        if (edadMeses < 84) return CategoriaEdad.ADULTO;
        return CategoriaEdad.SENIOR;
    }

    /**
     * Reglas (implementación simple y coherente con enunciado):
     * base = 50000
     * aves/reptiles: +20%
     * senior: +20%
     * urgencia: +50%
     * control + ave adulta: descuento 10%
     */
    public double calcularCostoConsulta(TipoConsulta tipo) {
        double base = 50000.0;
        double total = base;

        if (this instanceof Ave || this instanceof Reptil) total *= 1.2;
        if (this.categoriaEdad == CategoriaEdad.SENIOR) total *= 1.2;
        if (tipo == TipoConsulta.URGENCIA) total *= 1.5;
        if (tipo == TipoConsulta.CONTROL_RUTINARIO && this instanceof Ave && this.categoriaEdad == CategoriaEdad.ADULTO) total *= 0.9;

        return Math.round(total * 100.0) / 100.0;
    }

    public double calcularDosis(double mgPorKg) {
        if (mgPorKg <= 0 || this.pesoKg <= 0) throw new IllegalArgumentException("Parámetros inválidos");
        return Math.round((this.pesoKg * mgPorKg) * 100.0) / 100.0;
    }

    public LocalDate calcularProximaVacunacion(LocalDate fecha) {
        if (this instanceof Ave) return fecha.plusMonths(8);
        return fecha.plusMonths(12);
    }

    public static int obtenerPrioridad(Consulta consulta) {
        if (consulta == null) return 2;
        return consulta.getTipo() == TipoConsulta.URGENCIA ? 1 : 2;
    }

    public static Responsable obtenerResponsableMasFrecuente(List<Consulta> consultas) {
        if (consultas == null || consultas.isEmpty()) return null;
        Map<Responsable, Integer> cnt = new HashMap<>();
        for (Consulta c : consultas) {
            if (c.getMascota() == null || c.getMascota().getResponsable() == null) continue;
            cnt.merge(c.getMascota().getResponsable(), 1, Integer::sum);
        }
        return cnt.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    // getters/setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdadMeses() { return edadMeses; }
    public void setEdadMeses(int edadMeses) { this.edadMeses = edadMeses; this.categoriaEdad = calcularCategoriaEdad(); }

    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }

    public String getCodigoIdentificacion() { return codigoIdentificacion; }
    public void setCodigoIdentificacion(String codigoIdentificacion) { this.codigoIdentificacion = codigoIdentificacion; }

    public Responsable getResponsable() { return responsable; }
    public void setResponsable(Responsable responsable) { this.responsable = responsable; }

    public CategoriaEdad getCategoriaEdad() { return categoriaEdad; }
    public void setCategoriaEdad(CategoriaEdad categoriaEdad) { this.categoriaEdad = categoriaEdad; }

    @Override
    public String toString() {
        return nombre + " (" + raza + ")";
    }
}
