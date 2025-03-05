package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoriaClinicaService {

    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
    public HistoriaClinicaEntity crearHistoriaClinica(HistoriaClinicaEntity historiaClinicaEntity, Long pacienteId) throws EntityNotFoundException, IllegalOperationException{
        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(pacienteId);

        if (pacienteEntity.isEmpty()) {
            throw new EntityNotFoundException(null);
        }

        if (pacienteEntity.get().getAcudiente() != null){
            if (!(historiaClinicaEntity.getDiagnostico().substring(0,20).equals("HistoriaCompartida-"))){
                throw new IllegalOperationException(null);
            }
        }

        historiaClinicaEntity.setPaciente(pacienteEntity.get());
        return historiaClinicaRepository.save(historiaClinicaEntity);
    }

}
