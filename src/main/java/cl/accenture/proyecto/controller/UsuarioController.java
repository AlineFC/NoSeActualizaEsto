package cl.accenture.proyecto.controller;
import cl.accenture.proyecto.model.Rol;
import cl.accenture.proyecto.model.Usuario;
import cl.accenture.proyecto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

/*
    @RequestMapping("/loginUsuario")//funciona
    protected String login(@RequestParam("email") String email, @RequestParam("contrasena") String contrasena, ModelMap map)  {
        String destino = "";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setContrasena(contrasena);
        usuario = usuarioService.validacionUsuario(usuario);

        switch(usuario.getMensaje()){
            case "Correcto":
                map.addAttribute("email", email);
                destino = "Iniciando sesion...";
                break;
            case "contraseña":
                map.addAttribute( "La contraseña es incorrecta.");
                destino = "La contraseña es incorrecta.";
                break;
            case "inexistente":
                map.addAttribute( "El usuario no existe.");
                destino = "El email no se encuentra registrado en la base de datos.";
                break;
        }
        return destino;
    }
    */

        @RequestMapping( method = RequestMethod.POST , path = "/loginUsuario")//funciona
             protected boolean login(@RequestBody Usuario usuario, ModelMap map)  {
              String destino = "";
              usuario.setEmail(usuario.getEmail());
              usuario.setContrasena(usuario.getContrasena());
              usuario = usuarioService.validacionUsuario(usuario);

                 switch(usuario.getMensaje()){
            case "Correcto":
                map.addAttribute("email", usuario.getEmail());
                destino = "Iniciando sesion...";
                break;
            case "contraseÃ±a":
                map.addAttribute( "La contraseÃ±a es incorrecta.", usuario.getContrasena());
                destino = "La contraseÃ±a es incorrecta.";
                throw new RuntimeException("Contraseña kwsbewbcek");
            case "inexistente":
                map.addAttribute( "El usuario no existe.");
                destino = "El email no se encuentra registrado en la base de datos.";
                throw new RuntimeException("Usuario no encontrado");
        }
        return true;
    }


/*
    @RequestMapping( method = RequestMethod.POST , path = "/loginUsuario")//funcionaentrecomillas
    public boolean login(@RequestBody Usuario usuario){
        String contra=usuarioService.encriptar(usuario.getContrasena());
        String email=usuario.getEmail();
        List<Usuario> usuarios=usuarioService.validador(contra,email);
        if(usuarios.size()!=0){
            usuarios.get(0).setUltimoLogin(usuario.dateToDate(new Date())+" "+usuario.dateToTime(new Date()));
            usuarioService.modificarFecha(usuarios.get(0));
            System.out.println("Bienvenido");
            return true;
        }
        else {
            System.out.println("Rut o clave incorrectos");
            return false;
        }
    }
    */
    @RequestMapping( method = RequestMethod.PUT, path = "/agregarUsuario")////funciona
    protected boolean agregarUs(@RequestParam("email") String email, @RequestParam("contrasena") String contrasena, ModelMap map, @RequestParam ("nombre")String nombre,@RequestParam ("id_user") String idUser, @RequestParam ("id_rol")Rol rol ) {
        String destino = "";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setContrasena(contrasena);
        usuario = usuarioService.validacionUsuario(usuario);
        if (true) {
            switch (usuario.getMensaje()) {
               case "Correcto":
                    map.addAttribute("email", email);
                    destino = "El email, y la contraseña ya se encuentran registrados en la base de datos.";
                    break;
                case "inexistente":
                    map.addAttribute( "El usuario no existe.");
                    destino = "Usuario creado";
                    Usuario us = new Usuario();
                    us.setIdUser(idUser);
                    us.setNombre(nombre);
                    us.setEmail(email);
                    us.setContrasena(contrasena);
                    us.setRol(rol);
                    usuarioService.guardarUsuario(us);
                    break;
            }
            return Boolean.parseBoolean(destino);
        }else{
            return false;
        }
    }

    @RequestMapping( method = RequestMethod.PUT, path = "/editarUsuario")//funcionaaaaaaaaaaaaaaaaaaaaaaaa
    public boolean editUser(@RequestParam("email") String email, @RequestParam("contrasena") String contrasena, ModelMap map, @RequestParam ("nombre")String nombre,@RequestParam ("id_user") String idUser, @RequestParam ("id_rol")Rol rol) {
        Usuario usuario = usuarioService.obtenerUsuario(idUser);
        Assert.notNull(usuario, "Usuario not found");
        usuario.setNombre(nombre);
        usuario.setContrasena(contrasena);
        usuario.setEmail(email);
        usuario.setRol(rol);
        usuario.setIdUser(idUser);
        usuarioService.guardarUsuario(usuario);
        return Boolean.parseBoolean("Guardado.");
    }

    @RequestMapping( method = RequestMethod.DELETE, path = "/eliminarUsuario") //funciona
    public boolean deleteUser(@RequestParam String idUser) {
        Usuario usuario = usuarioService.obtenerUsuario(idUser);
        System.out.println("Usuario DELETE" + usuario);
        usuarioService.eliminarUser(usuario);
        return true;
    }
    @RequestMapping( method = RequestMethod.GET, path = "/obtenerUsuario/{id}") //funciona
    public Usuario obtenerPorId(@PathVariable(value="id") String id) {
        return usuarioService.obtenerUsuario(id); }

   @RequestMapping(method = RequestMethod.GET, path = "/encontrarEmail")//funciona
    public List<Usuario> encontrarEmail(@RequestParam String email){ //funciona
        List<Usuario> usuarios=usuarioService.obtenerEmail(email);
        return usuarios;
    }
    @RequestMapping( method = RequestMethod.GET, path = "/encontrarNombre")//funciona
    public List<Usuario> encontrarNombre(@RequestParam String nombre){ //funciona
        List<Usuario> usuarios=usuarioService.obtenerEmail(nombre);
        return usuarios;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/encontrarTodos") //funciona
    public  List<Usuario> encontrarTodos(){
        return usuarioService.encontrarTodos();
    }

}