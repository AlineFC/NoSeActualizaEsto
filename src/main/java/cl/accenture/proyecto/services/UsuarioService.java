package cl.accenture.proyecto.services;

import cl.accenture.proyecto.model.Usuario;
import cl.accenture.proyecto.repositorio.UsuarioRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario validacionUsuario(Usuario usuario) {
        Usuario usuario1 = new Usuario();
        List<Usuario> usuarios;
        usuarios = usuarioRepository.findByEmail(usuario.getEmail());
        switch(usuarios.size()){
            case 0:
                usuario1.setMensaje("inexistente");
                break;
            case 1:
                if(usuario.getContrasena().equals(usuarios.get(0).getContrasena())){
                    usuario1.setMensaje("Correcto");
                }else{
                    usuario1.setMensaje("contraseña");
                }
                break;
        }
        return usuario1;
    }
    public String encriptar(String contrasena) { String respuesta = DigestUtils.md5Hex(contrasena);return respuesta; }
    //Obtener usuario por ID
    public Usuario obtenerUsuario(String idUser){
        return usuarioRepository.findByIdUser(idUser);
    }
    public Usuario obtenerNombre(String nombre){return  usuarioRepository.findByNombre(nombre);}
    public List<Usuario> obtenerEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
    public List<Usuario> encontrarTodos(){
        List<Usuario> usuarios=new ArrayList<>();
        usuarioRepository.findAll().forEach(Usuario -> usuarios.add(Usuario));
        return usuarios;
    }
    public void guardarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    public void eliminarUser(Usuario usuario){usuarioRepository.delete(usuario);}
    public void modificarFecha(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    public List<Usuario> validador(String email, String contrasena){
        return usuarioRepository.findByEmailAndContrasena(email,contrasena);
    }
}
