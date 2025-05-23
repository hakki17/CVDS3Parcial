package cvds.edu.ecimedicina.repository;

import cvds.edu.ecimedicina.model.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {
    List<Cita> findByPaciente(String paciente);
    List<Cita> findByEspecialidad(String especialidad);
}