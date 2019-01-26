package cl.accenture.proyecto.repositorio;

import cl.accenture.proyecto.model.Empresa;
import org.springframework.data.repository.CrudRepository;


public interface EmpresaRepository extends CrudRepository<Empresa, String> {

}
