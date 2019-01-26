package cl.accenture.proyecto.repositorio;

import cl.accenture.proyecto.model.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RolRepository extends CrudRepository<Rol, String> {


    List<Rol> findBynombre(String Nombre);
    Rol findBydescripcion(String descripcion);
    Rol findByIdRol(String id);

}
