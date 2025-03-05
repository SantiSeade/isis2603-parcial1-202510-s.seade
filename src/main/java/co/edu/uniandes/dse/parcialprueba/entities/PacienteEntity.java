package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class PacienteEntity extends BaseEntity{

    private String nombre;
    private String correo;
    private String telefono;

    @PodamExclude
    @OneToMany(mappedBy = "paciente")
    private List<HistoriaClinicaEntity> historias = new ArrayList<>();

    @PodamExclude
    @OneToOne
    private PacienteEntity acudiente;

}
