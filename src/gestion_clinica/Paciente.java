package gestion_clinica;

import java.io.Serializable;

/**
 * Clase abstracta que representa un paciente generico de una clinica.
 * 
 * Los objetos de esta clase contienen atributos que permiten almacenar la mínima
 * información necesaria de un paciente.
 * 
 * -NIF o NIE del paciente.
 * -Nombre y apellidos del paciente.
 * -Dirección de correo electrónico del paciente.
 * 
 * @author Adrián Romero Ramírez
 */
public abstract class Paciente implements Serializable{
    
    protected String nif; // NIF o NIE del paciente
    protected String nombrePaciente; //Nombre y apellidos del paciente
    protected String emailNotificaciones; //Dirección de correo electrónico del paciente
    protected enum pacientes{Mutualista, Privado};
    private static final long serialVersionUID = 42L;

    /**
     * Devuelve el NIF o NIE del paciente
     * @return NIF o NIE del paciente
     */
    public String getNif() {
        return nif;
    }

    /**
     * Devuelve el nombre y apellido del paciente
     * @return nombre y apellido del paciente
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }
    
    /**
     * Devuelve el email del paciente
     * @return email del paciente
     */
    public String getEmailNotificaciones() {
        return emailNotificaciones;
    }

    /**
     * Cambia el nif de un paciente
     * @param nif nif nuevo
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Cambia el nombre del paciente
     * @param nombrePaciente nombre nuevo
     */
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    /**
     * cambia el email del paciente
     * @param emailNotificaciones email nuevo
     */
    public void setEmailNotificaciones(String emailNotificaciones) {
        this.emailNotificaciones = emailNotificaciones;
    }
    
    

    /**
     * Crea un objeto paciente con nif, nombre y email
     * @param nif NIF o NIE del paciente
     * @param nombrePaciente nombre y apellidos del paciente
     * @param emailNotificaciones email del paciente
     * @throws IllegalArgumentException si alguno de los parametros no es valido
     */
    public Paciente(String nif, String nombrePaciente, String emailNotificaciones){
        
        this.nif = nif;
        this.nombrePaciente = nombrePaciente;
        this.emailNotificaciones = emailNotificaciones;
    }
    
     /**
      * Constructor copia
      * @param paciente objeto paciente que se quiere copiar
      */
    public Paciente(Paciente paciente){
        this.nif = paciente.nif;
        this.nombrePaciente = paciente.nombrePaciente;
        this.emailNotificaciones = paciente.emailNotificaciones;
    }
    

    @Override
    public String toString(){
        StringBuilder cad = new StringBuilder();
        cad.append("NIF: ").append(this.nif).append(". ");
        cad.append("Nombre paciente: ").append(this.nombrePaciente).append(". ");
        cad.append("e-mail para notificaciones: ").append(this.emailNotificaciones).append(".");
        return cad.toString();
    }
    
    
}

