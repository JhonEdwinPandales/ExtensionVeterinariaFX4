package org.example.extensionveterinariafx4.model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
        if (edadMeses < 12) {
            return CategoriaEdad.CACHORRO_JOVEN;
        } else if (edadMeses < 84) {
            return CategoriaEdad.ADULTO;
        } else {
            return CategoriaEdad.SENIOR;
        }
    }

    // ----------------------------------------------------
    // MÉTODOS ÚTILES (para pruebas)
    // ----------------------------------------------------

    /**
     * Calcula costo de la consulta aplicando reglas simples:
     * - base: 50000
     * - urgencia: *1.5
     * - si es senior: *1.2
     * - si es Ave o Reptil: *1.2 (recargo)
     * - control sobre aves: descuento 10% (multiplica por 0.9)
     * - vacunación no aplica descuento por defecto
     */
    public double calcularCostoConsulta(TipoConsulta tipoConsulta) {
        double base = 50000.0;
        double total = base;

        // recargo por especie (aves y reptiles)
        if (this instanceof Ave || this instanceof Reptil) {
            total *= 1.2;
        }

        // recargo por edad senior
        if (this.getCategoriaEdad() == CategoriaEdad.SENIOR) {
            total *= 1.2;
        }

        // tipo de consulta
        if (tipoConsulta == TipoConsulta.URGENCIA) {
            total *= 1.5;
        } else if (tipoConsulta == TipoConsulta.CONTROL) {
            // descuento especial para aves adultas
            if (this instanceof Ave && this.getCategoriaEdad() == CategoriaEdad.ADULTO) {
                total *= 0.9; // 10% descuento
            }
        } else if (tipoConsulta == TipoConsulta.VACUNACION) {
            // ninguna regla extra por defecto
        }

        // redondear a 2 decimales
        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * Calcula dosis (mg) = pesoKg * factor.
     * Lanza IllegalArgumentException si factor <= 0 o peso <= 0
     */
    public double calcularDosis(double mgPorKg) {
        if (mgPorKg <= 0 || this.pesoKg <= 0) {
            throw new IllegalArgumentException("Parámetros inválidos para calcular dosis");
        }
        return Math.round((pesoKg * mgPorKg) * 100.0) / 100.0;
    }

    /**
     * Próxima vacunación:
     * - aves: +8 meses
     * - perros/gatos/otros: +12 meses
     */
    public LocalDate calcularProximaVacunacion(LocalDate fechaActual) {
        if (this instanceof Ave) {
            return fechaActual.plusMonths(8);
        } else {
            return fechaActual.plusMonths(12);
        }
    }

    /**
     * Prioridad simple: urgencia -> 1, otro -> 2
     */
    public static int obtenerPrioridad(String tipo) {
        if (tipo == null) return 2;
        if (tipo.equalsIgnoreCase("urgencia") || tipo.equalsIgnoreCase("URGENCIA") || tipo.equalsIgnoreCase("URGENTE")) {
            return 1;
        }
        return 2;
    }

    /**
     * Dados una lista de mascotas, devuelve el responsable más frecuente (por nombre).
     * Retorna null si la lista es vacía.
     */
    public static Responsable obtenerResponsableMasFrecuente(List<Mascota> lista) {
        if (lista == null || lista.isEmpty()) return null;
        Map<String, Integer> map = new HashMap<>();
        Map<String, Responsable> mapResp = new HashMap<>();
        for (Mascota m : lista) {
            if (m == null || m.getResponsable() == null) continue;
            String key = m.getResponsable().getNombreCompleto();
            map.merge(key, 1, Integer::sum);
            mapResp.putIfAbsent(key, m.getResponsable());
        }
        String mayor = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
        return mapResp.get(mayor);
    }

    // ----------------------------------------------------
    // Getters / Setters habituales
    // ----------------------------------------------------
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
