package gestion_clinica;

/**
 * Clase que representa un paciente de clase mutualista.
 * 
 * Esta clase contiene atributos especificos de la clase mutualista.
 * 
 * -Numero de hospitalizaciones de cada paciente mutualista.
 * -Numero total de pacientes mutualistas.
 * 
 * @author Adrián Romero Ramírez
 */
public class PacienteMutualista extends Paciente{
    
    protected int numeroDeHospitalizaciones; //Número de hospitalizaciones del paciente
    protected static int numeroPacientesMutualistas; //Número de pacientes mutualistas

    /**
     * Crea un objeto PacienteMutualista con un nif, nombre, email y numero de hospitalizaciones
     * @param nif NIF o NIE del paciente
     * @param nombrePaciente nombre y apellidos del paciente
     * @param emailNotificaciones email del paciente
     * @param numeroDeHospitalizaciones numero de hospitalizaciones del paciente
     * @throws IllegalArgumentException si alguno de los parametros no es valido
     */
    public PacienteMutualista(String nif, String nombrePaciente, String emailNotificaciones, 
            int numeroDeHospitalizaciones){
        super(nif, nombrePaciente, emailNotificaciones);
        this.numeroDeHospitalizaciones = numeroDeHospitalizaciones;
    }
    
    @Override
    public String toString(){
        StringBuilder cad = new StringBuilder();
        String cadInicial = super.toString();
        cad.append(cadInicial);
        cad.append("\nNúmero de hospitalizaciones: ").append(numeroDeHospitalizaciones).append(".\n");
        return cad.toString();
    }

    /**
     * Devuelve el número de hospitalizaciones del paciente
     * @return número de hospitalizaciones
     */
    public int getNumeroDeHospitalizaciones() {
        return numeroDeHospitalizaciones;
    }

    /**
     * Devuelve el número total de pacientes mutualistas
     * @return total de pacientes mutualistas
     */
    public static int getNumeroPacientesMutualistas() {
        return numeroPacientesMutualistas;
    }

    /**
     * Cambia el número total de pacientes mutualistas por el metido como parametro
     * @param numeroPacientesMutualistas total de pacientes mutualistas que se quiere cambiar
     */
    public static void setNumeroPacientesMutualistas(int numeroPacientesMutualistas) {
        PacienteMutualista.numeroPacientesMutualistas = numeroPacientesMutualistas;
    }

    /**
     * Cambia el número de hospitalzaciones 
     * @param numeroDeHospitalizaciones nuevo número de hospitalizaciones
     */
    public void setNumeroDeHospitalizaciones(int numeroDeHospitalizaciones) {
        this.numeroDeHospitalizaciones = numeroDeHospitalizaciones;
    }
    
}
