package cvds.edu.ecimedicina;

import cvds.edu.ecimedicina.model.Cita;
import cvds.edu.ecimedicina.repository.CitaRepository;
import cvds.edu.ecimedicina.service.CitaService;
import cvds.edu.ecimedicina.controller.CitaController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EcimedicinaApplicationTests {

    @Test
    void testCitaModel() {
        Cita cita = new Cita();
        cita.setId("1");
        cita.setPaciente("Juan Perez");
        cita.setEspecialidad("Cardiología");
        cita.setFechaHora(LocalDateTime.of(2025, 5, 23, 10, 0));
        cita.setMedico("Dr. Gomez");

        assertEquals("1", cita.getId());
        assertEquals("Juan Perez", cita.getPaciente());
        assertEquals("Cardiología", cita.getEspecialidad());
        assertEquals(LocalDateTime.of(2025, 5, 23, 10, 0), cita.getFechaHora());
        assertEquals("Dr. Gomez", cita.getMedico());
    }

    @DataMongoTest
    static class CitaRepositoryTest {

        @Autowired
        private CitaRepository citaRepository;

        @Test
        void testFindByPaciente() {
            Cita cita = new Cita();
            cita.setPaciente("Juan Perez");
            cita.setEspecialidad("Cardiología");
            cita.setFechaHora(LocalDateTime.of(2025, 5, 23, 10, 0));
            cita.setMedico("Dr. Gomez");

            citaRepository.save(cita);

            List<Cita> citas = citaRepository.findByPaciente("Juan Perez");
            assertFalse(citas.isEmpty());
            assertEquals("Cardiología", citas.get(0).getEspecialidad());
        }
    }

    static class CitaServiceTest {

        private final CitaRepository citaRepository = Mockito.mock(CitaRepository.class);
        private final CitaService citaService = new CitaService(citaRepository);

        @Test
        void testObtenerCitasPorPaciente() {
            Cita cita = new Cita();
            cita.setPaciente("Juan Perez");
            cita.setEspecialidad("Cardiología");
            cita.setFechaHora(LocalDateTime.of(2025, 5, 23, 10, 0));
            cita.setMedico("Dr. Gomez");

            when(citaRepository.findByPaciente("Juan Perez")).thenReturn(List.of(cita));

            List<Cita> citas = citaService.obtenerCitasPorPaciente("Juan Perez");
            assertFalse(citas.isEmpty());
            assertEquals("Cardiología", citas.get(0).getEspecialidad());
        }
    }

    @WebMvcTest(CitaController.class)
    static class CitaControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CitaService citaService;

        @Test
        void testObtenerCitasPorPaciente() throws Exception {
            Cita cita = new Cita();
            cita.setPaciente("Juan Perez");
            cita.setEspecialidad("Cardiología");
            cita.setFechaHora(LocalDateTime.of(2025, 5, 23, 10, 0));
            cita.setMedico("Dr. Gomez");

            when(citaService.obtenerCitasPorPaciente("Juan Perez")).thenReturn(List.of(cita));

            mockMvc.perform(get("/api/citas/paciente/Juan Perez"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].paciente").value("Juan Perez"))
                    .andExpect(jsonPath("$[0].especialidad").value("Cardiología"));
        }
    }
}