package cl.accenture.proyecto.controller;


import cl.accenture.proyecto.model.Persona;
import cl.accenture.proyecto.model.Proyecto;

import cl.accenture.proyecto.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = "*")
public class PersonaController {

    private PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @RequestMapping( method = RequestMethod.PUT, path = "/agregarUsuario")////funciona
    protected boolean agregarPer(@RequestParam("email") String email, @RequestParam("Nombre") String nombre, @RequestParam("Habilidad") String habilidad, @RequestParam ("Cargo")String cargo,@RequestParam ("idPersona") String idPersona, @RequestParam ("idProyecto")Proyecto proyecto, ModelMap map) {
        String destino = "";
        Persona persona = new Persona();
        persona.setEmail(email);
        persona.setIdPersona(idPersona);
        persona = personaService.validacionPersona(persona);
        if (true) {
            switch (persona.getMensaje()) {
                case "Correcto":
                    map.addAttribute("email", email);
                    destino = "El email, y la contraseña ya se encuentran registrados en la base de datos.";
                    break;
                case "inexistente":
                    map.addAttribute( "El usuario no existe.");
                    destino = "Usuario creado";
                    Persona per = new Persona();
                    per.setIdPersona(idPersona);
                    per.setNombre(nombre);
                    per.setEmail(email);
                    per.setCargo(cargo);
                    per.setHabilidad(habilidad);
                    per.setProyecto(proyecto);
                    personaService.guardarPersona(per);
                    break;
            }
            return Boolean.parseBoolean(destino);
        }else{
            return false;
        }
    }

    @RequestMapping( method = RequestMethod.PUT, path = "/editarUsuario")//funcionaaaaaaaaaaaaaaaaaaaaaaaa
    public boolean editPersona(@RequestParam("email") String email, @RequestParam("Nombre") String nombre, @RequestParam("Habilidad") String habilidad, @RequestParam ("Cargo")String cargo,@RequestParam ("idPersona") String idPersona, @RequestParam ("idProyecto")Proyecto proyecto) {
        Persona persona = personaService.obtenerPersona(idPersona);
        Assert.notNull(persona, "Persona not found");
        persona.setNombre(nombre);
        persona.setEmail(email);
        persona.setCargo(cargo);
        persona.setIdPersona(idPersona);
        persona.setHabilidad(habilidad);
        persona.setProyecto(proyecto);
        personaService.guardarPersona(persona);
        return Boolean.parseBoolean("Guardado.");
    }

    @RequestMapping( method = RequestMethod.DELETE, path = "/eliminarPersona") //funciona
    public boolean deletePersona(@RequestParam String idPersona) {
        Persona persona = personaService.obtenerPersona(idPersona);
        System.out.println("Persona DELETE" + persona);
        personaService.eliminarPersona(persona);
        return true;
    }
    @RequestMapping( method = RequestMethod.GET, path = "/obtenerUsuario/{id}") //funciona
    public Persona obtenerPorId(@PathVariable(value="id") String id) {
        return personaService.obtenerPersona(id); }

    @RequestMapping(method = RequestMethod.GET, path = "/encontrarEmail")//funciona
    public List<Persona> encontrarEmail(@RequestParam String email){ //funciona
        List<Persona> personas=personaService.obtenerEmail(email);
        return personas;
    }
    @RequestMapping( method = RequestMethod.GET, path = "/encontrarNombre")//funciona
    public List<Persona> encontrarNombre(@RequestParam Proyecto proyecto){ //funciona
        List<Persona> personas=personaService.obtenerProyecto(proyecto);
        return personas;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarTodos") //funciona
    public  List<Persona> encontrarTodos(){
        return personaService.encontrarTodos();
    }
}
