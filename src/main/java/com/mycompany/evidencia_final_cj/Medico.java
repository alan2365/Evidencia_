package com.mycompany.evidencia_final_cj;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

/**
 *
 * @author ALAN
 */
public class Medico implements JSONStreamAware{
    private Integer id;
    private String nombre;
    private String especialida;

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

    public String getEspecialida() {
        return especialida;
    }

    public void setEspecialida(String especialida) {
        this.especialida = especialida;
    }

    public void writeJSONString(Writer out) throws IOException {
        LinkedHashMap obj = new LinkedHashMap();
        obj.put("id", String.valueOf(id));
        obj.put("nombre", nombre);
        obj.put("especialida", especialida);
        
        JSONValue.writeJSONString(obj, out);
    }
}
