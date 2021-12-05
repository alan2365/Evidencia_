package com.mycompany.evidencia_final_cj;

/**
 *
 * @author ALAN
 */
public class Usuario {
    private Integer id;
    private String nombre;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
