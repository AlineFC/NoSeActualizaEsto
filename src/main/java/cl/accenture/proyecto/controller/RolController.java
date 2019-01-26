package cl.accenture.proyecto.controller;


import cl.accenture.proyecto.model.Rol;
import cl.accenture.proyecto.services.RolService;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.omg.IOP.ExceptionDetailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*")
public class RolController {

    private RolService rolService;

    @Autowired
    public RolController(RolService rolService){
        this.rolService=rolService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/administradores/{nombre}")
    public List<Rol> rolPorNombre(@RequestParam String nombre){
        return rolService.rolPorNombre(nombre);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/agregarRol")//funciona
    protected boolean agregarRol(@RequestParam("idRol") String idRol,@RequestParam("nombre") String nombre,ModelMap map,@RequestParam ("descripcion") String descripcion) {
        String destino = "";
        Rol rol1 = new Rol();
        rol1.setIdRol(idRol);
        rol1.setNombre(nombre);
        rol1 = rolService.validacionRol(rol1);
        if (true) {
            switch (rol1.getDescripcion()) {
                case "Correcto":
                    map.addAttribute("idRol", idRol);
                    destino = "La persona ya se encuentra registrada en la base de datos como administrador.";
                    break;
                case "inexistente":
                    map.addAttribute( "El rol no existe.");
                    destino = "Rol creado";
                    Rol rl = new Rol();
                    rl.setIdRol(idRol);
                    rl.setNombre(nombre);
                    rl.setDescripcion(descripcion);
                    rolService.guardarRol(rl);
                    break;
            }
            return Boolean.parseBoolean(destino);
        }else{
            return true;
        }
    }
/*
    @PutMapping("/agregarRol")//funciona
    protected boolean agregarRol(@RequestParam("idRol") String idRol,@RequestParam("nombre") String nombre,ModelMap map,@RequestParam ("descripcion") String descripcion) {
        String destino = "";
        Rol rol1 = new Rol();
        rol1.setIdRol(idRol);
        rol1.setNombre(nombre);
        rol1 = rolService.validacionRol(rol1);
        if (true) {
            switch (rol1.getDescripcion()) {
                case "Correcto":
                    map.addAttribute("idRol", idRol);
                    destino = "La persona ya se encuentra registrada en la base de datos como administrador.";
                    break;
                case "inexistente":
                    map.addAttribute( "El rol no existe.");
                    destino = "Rol creado";
                    Rol rl = new Rol();
                    rl.setIdRol(idRol);
                    rl.setNombre(nombre);
                    rl.setDescripcion(descripcion);
                    rolService.guardarRol(rl);
                    break;
            }
            return Boolean.parseBoolean(destino);
        }else{
            return false;
        }
    }
    */


    @RequestMapping(method = RequestMethod.DELETE, path = "/eliminarRol") //funciona
    public boolean deleteRol(@RequestParam String id) {
        Rol rol = rolService.rolPorId(id);
        System.out.println("Usuario DELETE" + rol);
        rolService.eliminarRol(rol);
        return true;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/obtenerRol/{id}") //funciona
    public Rol obtenerPorId(@PathVariable(value="id") String id) {
        return rolService.rolPorId(id); }

    @RequestMapping(method = RequestMethod.GET, path = "/encontrarNombre")//funciona
    public List<Rol> encontrarNombre(@RequestParam String nombre){ //funciona
        List<Rol> rols=rolService.rolPorNombre(nombre);
        return rols;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarTodos") //funciona
    public  List<Rol> encontrarTodos(){
        return rolService.encontrarTodos();
    }

}
