package pe.com.citas.reservarcitas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.com.citas.reservarcitas.model.Cita;
import pe.com.citas.reservarcitas.service.ReservaService;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservaCitasApplicationTests {

    @Autowired
    private ReservaService reservaService;

    @Test
    void testRegistroCitaExitoso() {
        // 1. Preparar datos: Especialista, Fecha Mañana, Hora 10 AM
        Cita citaOk = new Cita();
        citaOk.setEspecialista("Dr. Gabriel Mendoza");
        citaOk.setFecha(LocalDate.now().plusDays(1));
        citaOk.setHora(LocalTime.of(10, 0));

        // 2. Ejecutar el método del servicio
        String codigoGenerado = reservaService.reservarCita(citaOk);

        // 3. Validar: El código no debe ser nulo y debe empezar con "CITA-"
        assertNotNull(codigoGenerado);
        assertTrue(codigoGenerado.startsWith("CITA-"));

        System.out.println("✅ TEST POSITIVO PASADO: Código = " + codigoGenerado);
    }

    @Test
    void testErrorFechaAnteriorDebeFallar() {
        // 1. Preparar datos con FECHA DE AYER
        Cita citaErronea = new Cita();
        citaErronea.setEspecialista("Dra. Perez");
        citaErronea.setFecha(LocalDate.now().minusDays(1));
        citaErronea.setHora(LocalTime.of(15, 30));

        // 2. Ejecutar y validar que lance la excepción IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservarCita(citaErronea);
        });

        // 3. Validar el mensaje de error
        String mensajeEsperado = "La fecha no puede ser anterior a la actual";
        assertEquals(mensajeEsperado, exception.getMessage());

        System.out.println("✅ TEST NEGATIVO PASADO: El sistema bloqueó la fecha inválida.");
    }
}