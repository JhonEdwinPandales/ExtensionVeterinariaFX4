package org.example.extensionveterinariafx4.test;

import org.example.extensionveterinariafx4.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MascotaTest {

    private Perro perroJoven;
    private Gato gatoSenior;
    private Ave aveAdulta;
    private Reptil reptilAdulto;
    private Responsable r1, r2;

    @BeforeEach
    void setUp() {
        r1 = new Responsable("Carlos");
        r2 = new Responsable("Laura");

        perroJoven = new Perro("Rex", "Labrador", 10, 15.0, "P001", r1, Tamano.MEDIANO, "Intermedio", true);
        gatoSenior = new Gato("Michi", "Persa", 120, 5.0, "G002", r2, EsIndoor.INDOOR, 14, "Alta");
        aveAdulta = new Ave("Piolin", "Canario", 48, 0.3, "A001", r1, "Canto", true, 8);
        reptilAdulto = new Reptil("Draco", "Iguana", 60, 3.0, "R001", r2, TipoHabitat.TERRESTRE, 28.0, "MEDIO");
    }

    // 1
    @Test
    void costoConsultaGeneralPerroJoven() {
        double costo = perroJoven.calcularCostoConsulta(TipoConsulta.GENERAL);
        assertEquals(50000.0, costo, 0.01);
    }

    // 2
    @Test
    void costoUrgenciaGatoSenior() {
        double costo = gatoSenior.calcularCostoConsulta(TipoConsulta.URGENCIA);
        // senior +20% y urgencia +50% on base
        assertTrue(costo > 50000.0);
    }

    // 3
    @Test
    void descuentoControlAveAdulta() {
        double costo = aveAdulta.calcularCostoConsulta(TipoConsulta.CONTROL);
        // base 50000, ave recargo 1.2 => 60000, control + adulto => descuento 10% => 54000
        assertEquals(54000.0, costo, 0.01);
    }

    // 4
    @Test
    void costoVacunacionReptilAdulto() {
        double costo = reptilAdulto.calcularCostoConsulta(TipoConsulta.VACUNACION);
        // base 50000 * 1.2 (reptil) = 60000
        assertEquals(60000.0, costo, 0.01);
    }

    // 5
    @Test
    void estimacionDosis() {
        double dosis = perroJoven.calcularDosis(2.0); // 15 kg * 2 = 30 mg
        assertEquals(30.0, dosis, 0.01);
    }

    // 6
    @Test
    void excepcionDosisInvalida() {
        assertThrows(IllegalArgumentException.class, () -> perroJoven.calcularDosis(-1.0));
    }

    // 7
    @Test
    void proximaVacunacionPerroGato() {
        LocalDate hoy = LocalDate.of(2024, 1, 1);
        assertEquals(hoy.plusMonths(12), perroJoven.calcularProximaVacunacion(hoy));
        assertEquals(hoy.plusMonths(12), gatoSenior.calcularProximaVacunacion(hoy));
    }

    // 8
    @Test
    void proximaVacunacionAve() {
        LocalDate hoy = LocalDate.of(2024, 1, 1);
        assertEquals(hoy.plusMonths(8), aveAdulta.calcularProximaVacunacion(hoy));
    }

    // 9
    @Test
    void prioridadUrgenciaEs1() {
        Consulta c = new Consulta("C1", LocalDate.now(), gatoSenior, TipoConsulta.URGENCIA, 50000);
        assertEquals(1, c.getPrioridad());
    }

    // 10
    @Test
    void responsableMasFrecuente() {
        List<Consulta> consultas = new ArrayList<>();
        consultas.add(new Consulta("c1", LocalDate.now(), perroJoven, TipoConsulta.GENERAL, 50000));
        consultas.add(new Consulta("c2", LocalDate.now(), aveAdulta, TipoConsulta.GENERAL, 50000));
        consultas.add(new Consulta("c3", LocalDate.now(), new Perro("Toby","P",12,6.0,"P002",r1,Tamano.PEQUENO,"B",false), TipoConsulta.GENERAL, 50000));

        Responsable top = Mascota.obtenerResponsableMasFrecuente(consultas);
        assertNotNull(top);
        assertEquals("Carlos", top.getNombreCompleto());
    }
}
