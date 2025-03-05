package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
    public PacienteEntity createPaciente(PacienteEntity pacienteEntity) throws IllegalOperationException {
        if (pacienteEntity.getTelefono() == null){
            throw new IllegalOperationException(null);
        }

        if (pacienteEntity.getTelefono().length() != 11) {
            throw new IllegalOperationException(null);
        }

        if (!(pacienteEntity.getTelefono().substring(0,4).equals("311")) && !(pacienteEntity.getTelefono().substring(0,4).equals("601"))) {
            throw new IllegalOperationException(null);
        }

        return pacienteRepository.save(pacienteEntity);
    }

    @Transactional
    public PacienteEntity asociarAcudiente(Long pacienteId, Long acudienteId) throws IllegalOperationException, EntityNotFoundException{
        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(pacienteId);
		Optional<PacienteEntity> acudienteEntity = pacienteRepository.findById(acudienteId);

        if (pacienteEntity.isEmpty()){
			throw new EntityNotFoundException(null);
        }

		if (acudienteEntity.isEmpty()){
			throw new EntityNotFoundException(null);
        }

        if (pacienteEntity.get().getAcudiente() != null){
            throw new IllegalOperationException(null);
        }

        if (acudienteEntity.get().getAcudiente() != null) {
            throw new IllegalOperationException(null);
        }

        pacienteEntity.get().setAcudiente(acudienteEntity.get());
        return acudienteEntity.get();
    }

}
