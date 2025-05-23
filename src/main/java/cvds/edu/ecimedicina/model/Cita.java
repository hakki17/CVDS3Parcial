package cvds.edu.ecimedicina.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "citas")
public class Cita {
    @Id
    private String id;
    private String paciente;
    private String especialidad;
    private LocalDateTime fechaHora;
    private String medico;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaciente(){
        return paciente;
    }

    public void setPaciente(String paciente){
        this.paciente = paciente;
    }

    public String getEspecialidad(){
        return especialidad;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }

    public LocalDateTime getFechaHora(){
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora){
        this.fechaHora = fechaHora;
    }

    public String getMedico(){
        return medico;
    }

    public void setMedico(String medico){
        this.medico = medico;
    }

}