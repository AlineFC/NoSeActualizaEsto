/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.accenture.proyecto.model;

import javax.persistence.*;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author CrateX
 */

@Entity
@Table(name = "usuarios")
public class Usuario {


    @Id
    @Column(name = "idUser")
    private String idUser;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;
    private String mensaje;

    private String ultimoLogin;

    public Usuario() {
    }

    public Usuario(String idUser, String nombre, String contrasena, String email, Rol rol, String mensaje, String ultimoLogin) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.rol = rol;
        this.mensaje = mensaje;
        this.ultimoLogin = dateToDate(new Date()) + "" + dateToTime(new Date());
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreUs) {
        this.nombre = nombreUs;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(String ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public String dateToTime(Date date) {
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return formatoHora.format(date);
    }

    public String dateToDate(Date date) {
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(date);


    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUser=" + idUser +
                ", nombre='" + nombre + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", email='" + email + '\'' +
                ", rol=" + rol +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}










