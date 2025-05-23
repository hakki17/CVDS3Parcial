package cvds.edu.ecimedicina.service;

import cvds.edu.ecimedicina.model.Cita;
import cvds.edu.ecimedicina.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public List<Cita> getCitasByPaciente(String paciente) {
        return citaRepository.findByPaciente(paciente);
    }

    public List<Cita> getCitasByEspecialidad(String especialidad) {
        return citaRepository.findByEspecialidad(especialidad);
    }

    public Cita saveCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public void deleteCita(String id) {
        citaRepository.deleteById(id);
    }

}
