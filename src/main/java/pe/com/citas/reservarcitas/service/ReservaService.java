package pe.com.citas.reservarcitas.service;

import pe.com.citas.reservarcitas.model.Cita;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservaService {

    private List<Cita> citasSimuladas = new ArrayList<>();

    public String reservarCita(Cita cita) {
        // Regla 1: Especialista requerido
        if (cita.getEspecialista() == null || cita.getEspecialista().isBlank()) {
            throw new IllegalArgumentException("Por favor, selecciona un especialista.");
        }

        // Regla 2: Fecha no anterior a la actual
        if (cita.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de la cita no puede ser anterior a la fecha actual");
        }

        // Regla 3: Máximo 2 citas activas por usuario
        if (citasSimuladas.size() >= 2) {
            throw new IllegalStateException("No puedes tener más de 2 citas activas.");
        }

        // Todo OK: guardar y generar código único
        citasSimuladas.add(cita);
        return "CITA-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}
