package cl.accenture.proyecto.controller;

import cl.accenture.proyecto.model.*;
import cl.accenture.proyecto.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {

    private ProyectoService proyectoService;

    @Autowired
    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

/*
    @RequestMapping(method = RequestMethod.PUT, path = "/agregarProyecto")//funciona
    protected boolean agregarProyecto(@RequestParam("idProyecto") String idProyecto, @RequestParam("nombre") String nombreProyecto, ModelMap map, @RequestParam Date fechaInicio, @RequestParam Date fechaTermino, @RequestParam ("idEmpresa")Empresa empresa ) {
        String destino = "";
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(idProyecto);
        proyecto.setNombreProyecto(nombreProyecto);
        proyecto = proyectoService.validacionProyecto(idProyecto);
        if (true) {
            switch (proyecto.getMensaje()) {
                case "Correcto":
                    map.addAttribute("idProyecto", idProyecto);
                    destino = "El proyecto, y la contraseña ya se encuentran registrados en la base de datos.";
                    break;
                case "inexistente":
                    map.addAttribute( "El proyecto no existe.");
                    Proyecto pro = new Proyecto();
                    pro.setIdProyecto(idProyecto);
                    pro.setNombreProyecto(nombreProyecto);
                    pro.setFechaInicio(fechaInicio);
                    pro.setFechaTermino(fechaTermino);
                 //   pro.setStatus(status);
                    pro.setEmpresa(empresa);
                    proyectoService.guardarProyecto(pro);
                    destino = "Proyecto creado";
                    break;
            }
            return Boolean.parseBoolean(destino);
        }else{
            return false;
        }
    }
    @RequestMapping(method = RequestMethod.PUT, path = "/editarUsuario")//funcionaaaaaaaaaaaaaaaaaaaaaaaa
    public String editProyecto(ModelMap map,@RequestParam ("nombre")String nombre, @RequestParam ("fechaInicio") Date fechaInicio, @RequestParam ("fechaTermino") Date fechaTermino, @RequestParam ("idProyecto") String idProyecto, Proyecto proyecto, Empresa empresa) {
        proyecto.getIdProyecto(idProyecto);
        Assert.notNull(proyecto, "Proyecto not found");
        proyecto.setNombreProyecto(nombre);
        proyecto.setFechaInicio(fechaInicio);
        proyecto.setFechaTermino(fechaTermino);
        proyecto.setEmpresa(proyecto.getEmpresa());
        proyecto.setEmpresa(empresa);
        proyectoService.guardarProyecto(proyecto);
        return "Proyecto actualizado correctamente.";
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/eliminarProyecto") //funciona
    public boolean deleteProyecto(@RequestBody Proyecto proyecto) {
        Proyecto proyecto1 = proyectoService.obtenerProyecto(proyecto);
        System.out.println("SE ELIMINO" +proyecto1);
        proyectoService.eliminarProyecto(proyecto1);
        return true;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/obtenerUsuario/{id}") //funciona
    public Proyecto obtenerPorId(@PathVariable(value="id") String id) {
        return obtenerPorId(id); }


    @RequestMapping(method = RequestMethod.GET, path = "/encontrarNombre")//funciona
    public List<Proyecto> encontrarNombre(@RequestParam String nombre){ //funciona
        List<Proyecto> proyectos= encontrarNombre(nombre);
        return proyectos;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarTodos") //funciona
    public  List<Proyecto> encontrarTodos(){
        return proyectoService.encontrarTodos();
    }
}

*/
}