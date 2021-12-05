package com.mycompany.evidencia_final_cj;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author ALAN
 */
public class Main {

    static List<Usuario> usuarios;
    static int opc = 0;
    static Scanner entradaEscaner = new Scanner(System.in);
    static JSONArray arrayDocs = new JSONArray();
    static JSONArray arrayPacients = new JSONArray();
    static JSONArray arrayCitas = new JSONArray();

    public static void main(String[] args) {

        boolean existeUsuario;
        String usuario = "";
        String contrasena = "";

        System.out.println("Cargando sistema... ");
        cargarUsuarios();
        System.out.println("Inicio de sesion:");
        System.out.println("Usuario:");
        usuario = entradaEscaner.nextLine();
        System.out.println("Contraseña");
        contrasena = entradaEscaner.nextLine();
        existeUsuario = validarCredenciales(usuario, contrasena);

        if (existeUsuario) {
            System.out.println("existe el usuario\n");

            while (opc != 7) {
                loadDocs();
                loadPacients();
                loadCitas();
                menu();
            }

        } else {
            System.out.println("el usuario no existe");
        }

    }
    
    //******************* Metodo cargar usuarios *********************//
    
    public static void cargarUsuarios() {

        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }

        usuarios.add(new Usuario(1, "carlos", "1234"));
        usuarios.add(new Usuario(2, "sofia", "1234")
        );
        System.out.println("Los usuarios han sido cargados: " + usuarios.size());

    }
    
    //******************* Metodo validar usuarios *********************//
    
    public static boolean validarCredenciales(String usuario, String contrasena) {
        return usuarios.stream().anyMatch(x -> x.getNombre().equals(usuario) && x.getContrasena().equals(contrasena));
    }

    //******************* Metodo menu principal *********************//
    
    public static void menu() {

        System.out.println("1.-Dar de alta a medico\n"
                + "2.-Dar de alta a un paciente\n"
                + "3.-Crear una cita\n"
                + "4.-Ver a todos los medicos\n"
                + "5.-Ver a todos los pacientes\n"
                + "6.-Ver las citas doctor-paciente\n"
                + "7.-Salir");

        opc = Integer.parseInt(entradaEscaner.nextLine());

        switch (opc) {
            case 1:
                altaDocs();
                break;
            case 2:
                altaPacients();
                break;
            case 3:
                crearCitas();
                break;
            case 4:
                verDocs();
                break;
            case 5:
                verPacients();
                break;
            case 6:
                verCitas();
                break;
        }
        arrayCitas.clear();
        arrayDocs.clear();
        arrayPacients.clear();
    }
    
    //*********** Metodo cargar doctores a un array json *************//
    
    public static void loadDocs() {

        String json = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("doctores.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                json += linea;
            }
            br.close();

        } catch (Exception e) {
        }

        Gson gson = new Gson();

        java.lang.reflect.Type collectionType = new TypeToken<Collection<Object>>() {
        }.getType();
        Collection<Object> ints2 = gson.fromJson(json, collectionType);
        //System.out.println(ints2);

        for (Object object : ints2) {
            //System.out.println(object);
            arrayDocs.add(object);
        }

    }
    
    //*********** Metodo alta doctores a un array json *************//
    
    public static void altaDocs() {

        Medico medico = new Medico();

        System.out.print("\n");
        System.out.println("Ingrese el ID del doctor");
        medico.setId(Integer.parseInt(entradaEscaner.nextLine()));

        System.out.println("Ingrese el nombre del doctor");
        medico.setNombre(entradaEscaner.nextLine());

        System.out.println("Ingrese la especialidad del doctor");
        medico.setEspecialida(entradaEscaner.nextLine());

        arrayDocs.add(medico);

        guardarDoc();
    }
    
    //*********** Metodo ver doctores de un array json *************//
    
    public static void verDocs() {

        for (Object object : arrayDocs) {
            System.out.println(object);
        }
        System.out.println();

    }
    
    //********* Metodo guardar doctores a un archivo json ***********//
    
    public static void guardarDoc() {

        String archivoDocs = "doctores.json";

        StringWriter out = new StringWriter();
        try {
            arrayDocs.writeJSONString(out);
        } catch (Exception e) {
        }

        try {
            FileWriter fileWriter = new FileWriter(archivoDocs);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(out);
            printWriter.close();

        } catch (Exception e) {
            System.out.println("Ocurrio un error");
        }
        System.out.println("Se guardo correctamente");

    }

    //********** Metodo cargar pacientes a un array json *************//
    public static void loadPacients() {

        String json = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("pacientes.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                json += linea;
            }
            br.close();

        } catch (Exception e) {
        }

        Gson gson = new Gson();

        java.lang.reflect.Type collectionType = new TypeToken<Collection<Object>>() {
        }.getType();
        Collection<Object> ints2 = gson.fromJson(json, collectionType);
        //System.out.println(ints2);

        for (Object object : ints2) {
            //System.out.println(object);
            arrayPacients.add(object);
        }

    }
    
    //********** Metodo alta pacientes a un array json *************//
    
    public static void altaPacients() {

        Paciente paciente = new Paciente();

        System.out.print("\n");
        System.out.println("Ingrese el ID del paciente");
        paciente.setId(Integer.parseInt(entradaEscaner.nextLine()));

        System.out.println("Ingrese el nombre del paciente");
        paciente.setNombre(entradaEscaner.nextLine());

        arrayPacients.add(paciente);

        guardarPacient();
    }
    
    //********** Metodo ver pacientes de un array json *************//
    
    public static void verPacients() {

        for (Object object : arrayPacients) {
            System.out.println(object);
        }
        System.out.println();
    }
    
    //******** Metodo guardar pacientes a un archivo json ***********//
    
    public static void guardarPacient() {

        String archivoPacients = "pacientes.json";

        StringWriter out = new StringWriter();
        try {
            arrayPacients.writeJSONString(out);
        } catch (Exception e) {
        }

        try {
            FileWriter fileWriter = new FileWriter(archivoPacients);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(out);
            printWriter.close();

        } catch (Exception e) {
            System.out.println("Ocurrio un error");
        }
        System.out.println("Se guardo correctamente");

    }
    
    //************ Metodo cargar citas a un array json **************//

    public static void loadCitas() {

        String json = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("citas.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                json += linea;
            }
            br.close();

        } catch (Exception e) {
        }

        Gson gson = new Gson();

        java.lang.reflect.Type collectionType = new TypeToken<Collection<Object>>() {
        }.getType();
        Collection<Object> ints2 = gson.fromJson(json, collectionType);
        //System.out.println(ints2);

        for (Object object : ints2) {
            //System.out.println(object);
            arrayCitas.add(object);
        }

    }

    //************ Metodo crear citas a un array json **************//
    
    public static void crearCitas() {

        Cita cita = new Cita();
        Paciente paciente = new Paciente();
        Medico medico = new Medico();

        System.out.print("\n");
        System.out.println("Ingrese el ID de la cita");
        cita.setId(Integer.parseInt(entradaEscaner.nextLine()));

        System.out.println("Ingrese razon de la cita");
        cita.setNombreCita(entradaEscaner.nextLine());

        System.out.println("Ingrese el ID del medico");
        medico = buscarDoc(Integer.parseInt(entradaEscaner.nextLine()));
        cita.setMedico(medico);

        System.out.println("Ingrese el ID del paciente");
        paciente = buscarPaciente(Integer.parseInt(entradaEscaner.nextLine()));
        cita.setPaciente(paciente);

        System.out.println("Ingrese la fecha de la cita");
        cita.setFecha(entradaEscaner.nextLine());

        arrayCitas.add(cita);

        guardarCitas();
    }
    
    //********** Metodo buscar paciente de un array json ************//
    
    public static Paciente buscarPaciente(int pacID) {
        StringWriter out = new StringWriter();
        Paciente paciente = new Paciente();

        try {
            arrayPacients.writeJSONString(out);
        } catch (Exception e) {
        }
        JSONObject jsonPacients = new JSONObject();
        jsonPacients.put("pacients", out);

        JSONParser pacientsParse = new JSONParser();

        try {
            JSONObject objPacients = (JSONObject) pacientsParse.parse(jsonPacients.toJSONString());
            JSONArray arrayPacients2 = (JSONArray) objPacients.get("pacients");

            for (int i = 0; i < arrayPacients2.size(); i++) {
                JSONObject pacients = (JSONObject) arrayPacients2.get(i);

                if (pacID == Integer.parseInt(pacients.get("id").toString())) {
                    int id = Integer.parseInt(pacients.get("id").toString());
                    String nombre = pacients.get("nombre").toString();

                    paciente.setId(id);
                    paciente.setNombre(nombre);
                }

            }
        } catch (Exception e) {
        }
        return paciente;
    }

    //********** Metodo buscar medico de un array json ************//
    
    public static Medico buscarDoc(int docID) {
        StringWriter out = new StringWriter();
        Medico medico = new Medico();

        try {
            arrayDocs.writeJSONString(out);
        } catch (Exception e) {
        }
        JSONObject jsonDocs = new JSONObject();
        jsonDocs.put("doctores", out);

        JSONParser docsParse = new JSONParser();

        try {
            JSONObject objDocs = (JSONObject) docsParse.parse(jsonDocs.toJSONString());
            JSONArray arrayDocs2 = (JSONArray) objDocs.get("doctores");

            for (int i = 0; i < arrayDocs2.size(); i++) {
                JSONObject docs = (JSONObject) arrayDocs2.get(i);

                if (docID == Integer.parseInt(docs.get("id").toString())) {
                    int id = Integer.parseInt(docs.get("id").toString());
                    String nombre = docs.get("nombre").toString();
                    String especialidad = docs.get("especialida").toString();

                    medico.setId(id);
                    medico.setNombre(nombre);
                    medico.setEspecialida(especialidad);
                }

            }
        } catch (Exception e) {
        }
        return medico;
    }

    //*********** Metodo ver citas de un array json **************//
    
    public static void verCitas() {

        for (Object object : arrayCitas) {
            System.out.println(object);
        }
        System.out.println();
    }

    //*********** Metodo guardar citas a un archivo json ************//
    
    public static void guardarCitas() {

        String archivoCitas = "citas.json";

        StringWriter out = new StringWriter();
        try {
            arrayCitas.writeJSONString(out);
        } catch (Exception e) {
        }

        try {
            FileWriter fileWriter = new FileWriter(archivoCitas);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(out);
            printWriter.close();

        } catch (Exception e) {
            System.out.println("Ocurrio un error");
        }
        System.out.println("Se guardo correctamente");

    }
}
