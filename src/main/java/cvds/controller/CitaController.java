package cvds.edu.ecimedicina.controller;

import cvds.edu.ecimedicina.model.Cita;
import cvds.edu.ecimedicina.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaService.getAllCitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable String id) {
        Optional<Cita> cita = citaService.getCitaById(id);
        return cita;
    }

    @PostMapping("/create")
    public Cita createCita(@RequestBody Cita cita) {
        return citaService.saveCita(cita);
    }

    @GetMapping("/paciente/{paciente}") 
    public List<Cita> getCitasByPaciente(@PathVariable String paciente) {
        List<Cita> citas = citaService.getCitasByPaciente(paciente);
        return citas;
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteCita(@PathVariable String id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build();
    }

}