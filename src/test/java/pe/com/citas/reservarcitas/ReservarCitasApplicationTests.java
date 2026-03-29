package pe.com.citas.reservarcitas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.com.citas.reservarcitas.model.Cita;
import pe.com.citas.reservarcitas.service.ReservaService;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservarCitasApplicationTests {

    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        reservaService = new ReservaService();
    }

    // CP-01: Reserva exitosa con datos válidos
    @Test
    void testRegistroCitaExitoso() {
        // Precondiciones: paciente autenticado, horario disponible
        // Datos de entrada: especialista, fecha mañana, hora 10:00
        Cita cita = new Cita();
        cita.setEspecialista("Dr. Gabriel Mendoza");
        cita.setFecha(LocalDate.now().plusDays(1));
        cita.setHora(LocalTime.of(10, 0));

        String codigoGenerado = reservaService.reservarCita(cita);

        // Resultado esperado: se genera código único con prefijo "CITA-"
        assertNotNull(codigoGenerado);
        assertTrue(codigoGenerado.startsWith("CITA-"));

        System.out.println("TEST CP-01 PASADO - Código generado: " + codigoGenerado);
    }

    // CP-02: Reserva con fecha anterior a la actual debe fallar
    @Test
    void testFechaAnteriorLanzaExcepcion() {
        // Datos de entrada: fecha de ayer
        Cita cita = new Cita();
        cita.setEspecialista("Dra. Pérez");
        cita.setFecha(LocalDate.now().minusDays(1));
        cita.setHora(LocalTime.of(15, 30));

        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservarCita(cita);
        });

        // Resultado esperado: mensaje de error por fecha inválida
        assertEquals("La fecha de la cita no puede ser anterior a la fecha actual", excepcion.getMessage());

        System.out.println("TEST CP-02 PASADO - Sistema bloqueó la fecha inválida correctamente.");
    }
}
