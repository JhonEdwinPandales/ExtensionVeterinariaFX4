package org.example.extensionveterinariafx4;

import org.example.extensionveterinariafx4.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class MascotaTest {

    @Test
    void testCostoConsultaGeneralPerroJoven() {
        Mascota perro = new Perro("Rocky", "Labrador", 12, 20.0, "P001", new Responsable("Carlos"));
        double costo = perro.calcularCostoConsulta(TipoConsulta.GENERAL);
        assertEquals(30000, costo, 0.01);
    }

    @Test
    void testCostoUrgenciaGatoSenior() {
        Mascota gato = new Gato("Michi", "Persa", 96, 5.0, "G002", new Responsable("Lucía"));
        double costo = gato.calcularCostoConsulta(TipoConsulta.URGENCIA);
        assertTrue(costo > 50000);
    }

    @Test
    void testDescuentoConsultaAveAdulta() {
        Mascota ave = new Ave("Piolin", "Canario", 36, 0.2, "A003", new Responsable("Ana"));
        double costo = ave.calcularCostoConsulta(TipoConsulta.CONTROL);
        assertTrue(costo < 30000);
    }

    @Test
    void testCostoVacunacionReptilAdulto() {
        Mascota reptil = new Reptil("Spike", "Iguana", 60, 3.0, "R004", new Responsable("Pedro"));
        double costo = reptil.calcularCostoConsulta(TipoConsulta.VACUNACION);
        assertEquals(40000, costo, 0.01);
    }

    @Test
    void testCalculoDosisValida() {
        Mascota perro = new Perro("Luna", "Beagle", 24, 10.0, "P005", new Responsable("Luis"));
        double dosis = perro.calcularDosis(2.0);
        assertEquals(20.0, dosis, 0.01);
    }

    @Test
    void testCalculoDosisInvalidaLanzaExcepcion() {
        Mascota perro = new Perro("Rex", "Boxer", 24, 10.0, "P006", new Responsable("Laura"));
        assertThrows(IllegalArgumentException.class, () -> perro.calcularDosis(-1.0));
    }

    @Test
    void testProximaVacunacionPerroYGato12Meses() {
        Mascota perro = new Perro("Max", "Bulldog", 24, 15.0, "P007", new Responsable("Leo"));
        LocalDate proxima = perro.calcularProximaVacunacion(LocalDate.of(2025, 1, 1));
        assertEquals(LocalDate.of(2026, 1, 1), proxima);
    }

    @Test
    void testProximaVacunacionAve8Meses() {
        Mascota ave = new Ave("Kiwi", "Loro", 24, 1.0, "A008", new Responsable("Julia"));
        LocalDate proxima = ave.calcularProximaVacunacion(LocalDate.of(2025, 1, 1));
        assertEquals(LocalDate.of(2025, 9, 1), proxima);
    }

    @Test
    void testPrioridadUrgenciaEsUno() {
        Consulta c = new Consulta(TipoConsulta.URGENCIA, new Perro(), LocalDate.now());
        assertEquals(1, c.getPrioridad());
    }

    @Test
    void testResponsableMasFrecuente() {
        Responsable juan = new Responsable("Juan");
        Responsable maria = new Responsable("Maria");
        Mascota perro1 = new Perro("Firulais", "Labrador", 12, 10, "P009", juan);
        Mascota perro2 = new Perro("Boby", "Beagle", 8, 8, "P010", juan);
        Mascota gato1 = new Gato("Nina", "Siames", 24, 4, "G011", maria);

        Responsable masFrecuente = Responsable.obtenerMasFrecuente(
                java.util.List.of(perro1, perro2, gato1)
        );

        assertEquals("Juan", masFrecuente.getNombreCompleto());
    }
}
