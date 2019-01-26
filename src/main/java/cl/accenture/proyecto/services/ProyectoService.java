package cl.accenture.proyecto.services;


import cl.accenture.proyecto.model.Proyecto;
import cl.accenture.proyecto.repositorio.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProyectoService {

    private ProyectoRepository proyectoRepository;

    @Autowired
    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }


    public Proyecto validacionProyecto(String idProyecto) {
        Proyecto pro = new Proyecto();
        List<Proyecto> proyectos;
        proyectos = proyectoRepository.findByIdProyecto(idProyecto);
        switch(proyectos.size()){
            case 0:
                pro.setMensaje("inexistente");
               break;
            case 1:
                if(idProyecto.equals(proyectos.get(0).getIdProyecto(idProyecto))){
                    pro.setMensaje("Correcto");
                }else{
                    pro.setMensaje("contraseña");
                }
                break;
        }
        return pro;
    }
    //public Proyecto ob(String id){return proyectoRepository.findById(id).get();}
    public boolean obtenerProyecto(Proyecto proyecto){return proyectoRepository.findByIdProyecto(proyecto);
    }
    public Proyecto obtenerNombre(String nombre){return  proyectoRepository.findByNombreProyecto(nombre);}
    public List<Proyecto> encontrarTodos(){
        List<Proyecto> proyectos=new ArrayList<>();
        proyectoRepository.findAll().forEach(Proyecto -> proyectos.add(Proyecto));
        return proyectos;
    }

  /* public Proyecto obtenerE(String idPro){
        return proyectoRepository.findById(idPro).get();
    }
*/
    public void guardarProyecto(Proyecto proyecto){
        proyectoRepository.save(proyecto);
    }

    public void eliminarProyecto(Proyecto proyecto){ proyectoRepository.delete(proyecto);}

}
