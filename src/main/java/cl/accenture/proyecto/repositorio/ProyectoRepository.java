package cl.accenture.proyecto.repositorio;

import cl.accenture.proyecto.model.Proyecto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProyectoRepository extends CrudRepository<Proyecto, String> {

    @Override
    long count();

   Proyecto findByNombreProyecto(String nombre);
   List<Proyecto> findByIdProyecto (String idProyecto);


    boolean findByIdProyecto(Proyecto proyecto);
}
