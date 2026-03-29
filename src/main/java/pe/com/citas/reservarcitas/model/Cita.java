package pe.com.citas.reservarcitas.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    private Integer idCita;
    private String especialista;
    private LocalDate fecha;
    private LocalTime hora;

    // Constructor vacío
    public Cita() {
    }

    // Constructor con parámetros (opcional, ayuda en los tests)
    public Cita(String especialista, LocalDate fecha, LocalTime hora) {
        this.especialista = especialista;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y Setters
    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public String getEspecialista() {
        return especialista;
    }

    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}