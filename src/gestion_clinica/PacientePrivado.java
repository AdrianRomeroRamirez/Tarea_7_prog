package gestion_clinica;

/**
 * Clase que representa un paciente de clase Privada.
 * 
 * Esta clase contiene atributos especificos de la clase privada.
 * 
 * -Numero de visitas de cada paciente privado.
 * -Numero total de pacientes privados.
 * 
 * @author Adrián Romero Ramírez
 */
public class PacientePrivado extends Paciente{
    
    protected int numeroDeVisitas; //Número de visitas de cada paciente
    protected static int numeroPacientesPrivados; //Número total de pacientes 

    /**
     * Crea un objeto PacientePribado con un nif, nombre, email y numero de visitas
     * @param nif NIF o NIE del paciente
     * @param nombrePaciente nombre y apellidos del paciente
     * @param emailNotificaciones email del paciente
     * @param numeroDeVisitas numero de visitas del paciente
     * @throws IllegalArgumentException si alguno de los parametros no es valido
     */
    public PacientePrivado(String nif, String nombrePaciente, String emailNotificaciones,
            int numeroDeVisitas){
        super(nif, nombrePaciente, emailNotificaciones);
        this.numeroDeVisitas = numeroDeVisitas;
    }
    
    @Override
    public String toString(){
        StringBuilder cad = new StringBuilder();
        String cadInicial = super.toString();
        cad.append(cadInicial);
        cad.append("\nNumero de visitas: ").append(numeroDeVisitas).append(".\n");
        return cad.toString();
    }

    /**
     * Devuelve el número de visitas del paciente
     * @return número de visitas
     */
    public int getNumeroDeVisitas() {
        return numeroDeVisitas;
    }

    /**
     * Devuelve el número total de pacientes privados
     * @return total de pacientes privados
     */
    public static int getNumeroPacientesPrivados() {
        return numeroPacientesPrivados;
    }

    /**
     * Cambia el número total de pacientes privados por el metido como parametro
     * @param numeroPacientesPrivados total de pacientes privados que se quiere cambiar
     */
    public static void setNumeroPacientesPrivados(int numeroPacientesPrivados) {
        PacientePrivado.numeroPacientesPrivados = numeroPacientesPrivados;
    }

    /**
     * Cambia el número de visitas
     * @param numeroDeVisitas nuevo número de visitas
     */
    public void setNumeroDeVisitas(int numeroDeVisitas) {
        this.numeroDeVisitas = numeroDeVisitas;
    }
    
}
