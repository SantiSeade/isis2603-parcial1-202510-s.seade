package co.edu.uniandes.dse.parcialprueba.services;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(PacienteService.class)
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<PacienteEntity> pacienteList = new ArrayList<>();
    private PacienteEntity acudienteEntity;

    @BeforeEach
    void setUp() {
        clearData();
		insertData();
    }

    private void clearData() {
		entityManager.getEntityManager().createQuery("delete from PacienteEntity");
		entityManager.getEntityManager().createQuery("delete from HistoriaClinicaEntity");
	}

    private void insertData() {
        acudienteEntity = factory.manufacturePojo(PacienteEntity.class);
        entityManager.persist(acudienteEntity);

        for (int i = 0; i < 3; i++) {
			PacienteEntity pacienteEntity = factory.manufacturePojo(PacienteEntity.class);
			entityManager.persist(pacienteEntity);
			pacienteList.add(pacienteEntity);
		}
    }

    @Test
    void testCreatePacienteTelefonoDiferenteDe11(){
        assertThrows(IllegalOperationException.class, () -> {
            PacienteEntity pacienteEntity = factory.manufacturePojo(PacienteEntity.class);
            pacienteEntity.setTelefono("111");
            pacienteService.createPaciente(pacienteEntity);
        });
    }

    @Test
    void testCreatePacienteTelefonoNulo1(){
        assertThrows(IllegalOperationException.class, () -> {
            PacienteEntity pacienteEntity = factory.manufacturePojo(PacienteEntity.class);
            pacienteEntity.setTelefono("111");
            pacienteService.createPaciente(pacienteEntity);
        });
    }
    

}
