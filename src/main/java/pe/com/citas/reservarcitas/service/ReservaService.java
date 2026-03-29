package pe.com.citas.reservarcitas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.citas.reservarcitas.model.Cita;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    // Simulamos la base de datos con una lista en memoria
    private List<Cita> citasSimuladas = new ArrayList<>();

    public String reservarCita(Cita cita) {
        // Regla 1: Fecha no anterior a la actual
        if (cita.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a la actual");
        }

        // Regla 2: Máximo 2 citas (Buscamos en nuestra lista)
        long contador = citasSimuladas.stream()
                .filter(c -> c.getEspecialista().equals(cita.getEspecialista()))
                .count();

        if (contador >= 2) {
            throw new IllegalStateException("El especialista ya tiene el máximo de 2 citas");
        }

        // Si todo está OK, lo "guardamos" en la lista y generamos código
        citasSimuladas.add(cita);
        return "CITA-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}