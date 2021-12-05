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
public class Cita implements JSONStreamAware{
    private Integer id;
    private String nombreCita;
    private String fecha;
    private Medico medico;
    private Paciente paciente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCita() {
        return nombreCita;
    }

    public void setNombreCita(String nombreCita) {
        this.nombreCita = nombreCita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public void writeJSONString(Writer out) throws IOException {
        LinkedHashMap obj = new LinkedHashMap();
        obj.put("id", String.valueOf(id));
        obj.put("nombre", nombreCita);
        obj.put("fecha", fecha);
        obj.put("medico", medico);
        obj.put("paciente", paciente);
        
        JSONValue.writeJSONString(obj, out);
    }

}
