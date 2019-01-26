package cl.accenture.proyecto.repositorio;

import cl.accenture.proyecto.model.Persona;
import cl.accenture.proyecto.model.Proyecto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonaRepository extends CrudRepository<Persona, String> {

    List<Persona> findByEmail(String email);
    List<Persona> findByProyecto(Proyecto proyecto);
    Persona findByIdPersona(String idPersona);
}
