package cl.accenture.proyecto.services;

import cl.accenture.proyecto.model.Rol;
import cl.accenture.proyecto.repositorio.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolService {

    private RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }


    public Rol validacionRol(Rol rol) {
        Rol rol1 = new Rol();
        List<Rol> rols;
        rols = rolRepository.findBynombre(rol.getNombre());
        switch(rols.size()){
            case 0:
                rol1.setDescripcion("inexistente");
                break;
            case 1:
                if(rol.getIdRol().equals(rols.get(0).getIdRol())){
                    rol1.setDescripcion("Correcto");
                }else{
                    rol1.setDescripcion("contraseña");
                }
                break;
        }
        return rol1;
    }

    public Rol rolPorId(String idRol){
        return rolRepository.findByIdRol(idRol);
    }
    public Rol rolPorDescripcion(String descripcion){return rolRepository.findBydescripcion(descripcion);}
    public List<Rol> rolPorNombre(String nombre){
        return rolRepository.findBynombre(nombre);
    }
    public List<Rol> encontrarTodos(){
        List<Rol> rols=new ArrayList<>();
        rolRepository.findAll().forEach(Rol -> rols.add(Rol));
        return rols;
    }
    public void guardarRol(Rol rol){
        rolRepository.save(rol);
    }
    public void eliminarRol(Rol rol){rolRepository.delete(rol);}
}


