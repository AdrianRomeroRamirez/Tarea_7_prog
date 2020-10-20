package gestion_clinica;

import java.io.*;
import utilidades.ES;

/**
 * Clase principal desde donde el usuario puede manipular y gestionar los datos 
 * de los distintos pacientes que va registrando.
 * 
 * También puede generar y leer desde un archivo creado por la aplicación.
 * @author Adrián Romero Ramírez
 */
public class Aplicacion {

    private static final int PACIENTES_MAXIMOS = 40; //Número maximo de pacientes
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int opcion; // Opción elegida por el usuario para navegar por el menú
        String ruta = ".\\pacientes.dat"; // Ruta del archivo
        File fichero = new File(ruta); // Archivo físico
        Paciente[] arrayPacientes = new Paciente[PACIENTES_MAXIMOS]; // Array donde se guardan los objetos Pacientes
        boolean cambios = false; // Boolean para saber si hay cambios o no
        
        
        // Primero compruebo si existe el fichero o no, si existe vuelco la información en el array
        if(fichero.exists()){
            arrayPacientes = Aplicacion.volcarArray(ruta);
            System.out.println("Datos del fichero cargados correctamente.");
        }
            
        // Aquí inicio el menú que estara reiterando hasta que el cliente pulse 0 para salir
        do{
            System.out.println("========================================\n" +
                "     ========= Gestión de la clínica ========\n" +
                "     ========================================\n" +
                "\n" +
                "         1.- Añadir un paciente.\n" +
                "         2.- Listar pacientes.\n" +
                "         3.- Borrar un paciente.\n" +
                "         4.- Guardar datos en fichero.\n" +
                "         5.- Recuperar datos desde fichero.\n" +
                "         6.- Escribir lista de pacientes a txt.\n" +
                "\n" +
                "         0.- Salir de la aplicación.\n" +
                "     ========================================\n" +
                "     Introduzca la opción elegida: ");
            opcion = ES.leeEntero(0, 6);

            switch(opcion){
                case 1:
                    if(Aplicacion.crearPaciente(arrayPacientes)){
                        cambios = true;
                    }
                    break;
                
                case 2:
                    System.out.println("Lista de pacientes.\n"
                            + "------------------------------");
                    for (Paciente arrayPaciente : arrayPacientes) {
                        if (arrayPaciente != null) {
                            System.out.println(arrayPaciente.toString());
                        }
                    }
                    System.out.println("Total de pacientes: " + (PacienteMutualista.getNumeroPacientesMutualistas()
                            +PacientePrivado.getNumeroPacientesPrivados()));
                    break;
                    
                case 3:
                    if(Aplicacion.borrarPaciente(arrayPacientes)){
                        cambios = true;
                    }
                    break;
                case 4:
                    if(Aplicacion.guardarEnFichero(ruta, arrayPacientes)){
                        cambios = false;
                    }
                    break;
                case 5:
                    if (cambios){
                        String respuesta = ES.leeRespuesta("Ha realizado cambios que no ha guardado en disco.\n"
                                + "Si continúa la carga del archivo se restaurarán los datos\n"
                                + "de disco y se perderán los cambios no guardados.\n"
                                + "¿Desea continuar con la carga y restaurar los datos del archivo? (S/N)");
                        if("S".equals(respuesta)){
                            arrayPacientes = Aplicacion.volcarArray(ruta);
                            System.out.println("Datos recuperados.");
                        }else{
                            System.out.println("Restauración de datos anulada.");
                        }
                    }else{
                        System.out.println("No se ha modificado ningún dato desde el ultimo guardado.");
                    }
                    break;
                case 6:
                    Aplicacion.generarTxt(arrayPacientes, ".\\pacientes.txt");
                    break;
                case 0:
                    if (cambios){
                        String respuesta = ES.leeRespuesta("Ha realizado cambios que no ha guardado en disco. ¿Desea guardarlos antes de salir?(S/N)");
                        if("S".equals(respuesta)){
                            if(Aplicacion.guardarEnFichero(ruta, arrayPacientes)){
                            }else{
                                System.out.println("No se ha podido guardar el fichero.");
                            }
                        }else{
                            System.out.println("Saliendo sin guardar los datos.");
                        }
                    }
            }

        }while(opcion!=0);
        
        System.out.println("Fin del programa.");
    }

    /**
     * Este método sirve para volcar la información del fichero en el array
     * @param ruta del fichero donde está la información
     * @return array con la lista de pacientes
     */
    private static Paciente[] volcarArray(String ruta){
        Paciente[] array = null;
        
        try{
            FileInputStream fichero = new FileInputStream(new File (ruta));
            try (ObjectInputStream ficheroEntrada = new ObjectInputStream(fichero)) {
                array = (Paciente[]) ficheroEntrada.readObject();
                PacienteMutualista.setNumeroPacientesMutualistas(ficheroEntrada.readInt());
                PacientePrivado.setNumeroPacientesPrivados(ficheroEntrada.readInt());
            }
        }catch (ClassNotFoundException ex){
            System.out.println("Error al revertir la clase");
        }catch (FileNotFoundException e){
            System.out.println("Fichero no encontrado.");
        }catch (IOException i){
            System.out.println("Error de lectura del archivo.");
        }
        return array;
    }
    
    /**
     * Este método crea un objeto de la clase paciente y lo guarda en el array metido
     * por parametro, pidiendo los datos al cliente por teclado y creando un tipo de 
     * paciente u otro dependiendo de la opción elegida por el cliente
     * @param pacientes array donde quieres que se guarde el objeto
     * @return boolean para saber si se ha creado o no el paciente
     */
    @SuppressWarnings("empty-statement")
    private static boolean crearPaciente(Paciente[] pacientes){
        
        boolean creado = false;
        boolean nifRepetido = false;
        boolean hueco = false;
        int posicionArray;
        
        for (Paciente paciente : pacientes) {
            if (paciente == null) {
                hueco = true;
            }
        }
        
        if(hueco){
            System.out.println("Introduzca los datos del paciente:\n");
            String nif, nombre, email;
            do{
                nif = ES.leeCadena("\nEscriba el NIF del paciente:");
                if(!ES.checkNif(nif)){
                    System.out.println("NIF no valido.");
                }
            }while(!ES.checkNif(nif));
            
            for(int i=0; i<pacientes.length && pacientes[i]!=null; i++){
                if(nif.equals(pacientes[i].getNif())){
                    nifRepetido = true;
                }
            }
            if(!nifRepetido){
                nombre = ES.leeCadena("Escriba el nombre del paciente:");

                do{
                    email = ES.leeCadena("Escriba el e-mail del paciente:");
                    if(!ES.checkEmail(email)){
                        System.out.println("e-mail no valido.");
                    }
                }while(!ES.checkEmail(email));

                int tipoPaciente = ES.leeEntero("Escriba el tipo de paciente (1-> PRIVADO, 2-> MUTUALISTA)", 1, 2);

                if (tipoPaciente == 1){
                    int numeroVisitas = ES.leeEntero("Escriba el número de visitas (0,150)", 0, 150);                
                    Paciente paciente = new PacientePrivado(nif, nombre, email, numeroVisitas);                
                    for (posicionArray=0; pacientes[posicionArray]!=null; posicionArray++);              
                    pacientes[posicionArray] = paciente;               
                    PacienteMutualista.numeroPacientesMutualistas++;               
                }else{
                    int numeroHospitalizaciones = ES.leeEntero("Escriba número de hospitalizaciones del paciente (0,100)", 0, 100);                
                    Paciente paciente = new PacienteMutualista(nif, nombre, email, numeroHospitalizaciones);                
                    for (posicionArray=0; pacientes[posicionArray]!=null; posicionArray++);                    
                    pacientes[posicionArray] = paciente;                
                    PacientePrivado.numeroPacientesPrivados++;                
                }            

                System.out.println("Se ha añadido un nuevo paciente.");            
                creado = true;           
            }else{
                System.out.println("Ya existe un paciente con ese NIF en la clínica.");
            } 
        }else{
            System.out.println("ERROR: clínica completa. No es posible dar de alta más de "+PACIENTES_MAXIMOS+" pacientes");
        }       
        return creado;
    }
    
    /**
     * Método para borrar un paciente idewntificado con su nif de la lista de pacientes
     * pasada por parametro
     * @param pacientes array de pacientes pasado por parametro
     * @return boolean para saber si se ha borrado correctamente
     */
    private static boolean borrarPaciente(Paciente[] pacientes){
        boolean borrado = false;
        boolean nifExiste = false;
        int posicionPaciente = 0;
        String nif = ES.leeCadena("Introduzca el nif del paciente a borrar:");
        for(int i=0; i<pacientes.length; i++){
            if(pacientes[i]!=null){
                if(nif.equals(pacientes[i].getNif())){
                    nifExiste = true;
                    posicionPaciente = i;
                }
            }
        }
        if(nifExiste){
            System.out.println("Se va aproceder a borrar al paciente:");
            System.out.println(pacientes[posicionPaciente]);
            String respuesta = ES.leeRespuesta("¿Desea continuar con el borrado? (S/N)");
            
            if("S".equals(respuesta)){
                if(pacientes[posicionPaciente] instanceof PacienteMutualista){
                    PacienteMutualista.numeroPacientesMutualistas--;
                }else{
                    PacientePrivado.numeroPacientesPrivados--;
                }
                pacientes[posicionPaciente] = null;
                borrado = true;
                System.out.println("Borrado realizado con exito.");
            }
        }else{
            System.out.println("NIF no encontrado.");
        }
        return borrado;
    }
    
    /**
     * Método que guarda la información de todos los paciente en un fichero
     * @param ruta donde se guarda el fichero
     * @param pacientes array de pacientes que se quiere guardar
     * @return boolean para saber si se ha guardado correctamente
     */
    private static boolean guardarEnFichero(String ruta, Paciente[] pacientes){
        boolean guardado = false;
        
        try{
            FileOutputStream fichero = new FileOutputStream(new File(ruta));
            try (ObjectOutputStream ficheroSalida = new ObjectOutputStream(fichero)) {
                ficheroSalida.writeObject(pacientes);
                ficheroSalida.writeInt(PacienteMutualista.getNumeroPacientesMutualistas());
                ficheroSalida.writeInt(PacientePrivado.getNumeroPacientesPrivados());
            }
            guardado = true;
            System.out.println("Datos guardados correctamente en el fichero pacientes.dat.");
        }catch(FileNotFoundException e){
            System.out.println("Fichero no encontrado");
        }catch(IOException ex){
            System.out.println("Error al guardar los datos");
        }
        return guardado;
    }

    /**
     * Método que genera un archivo .txt con el array y en la ruta que le metan como parametro
     * @param array del cual se quiere guardar la información
     * @param ruta donde se quiere guardar el archivo .txt
     */
    private static void generarTxt(Paciente[] array, String ruta){
        
        try{
            try (FileWriter fichero = new FileWriter(ruta);PrintWriter pw = new PrintWriter(fichero)) {
                for (Paciente array1 : array) {
                    if (array1 != null) {
                        pw.println(array1.toString());
                    }
                }
                System.out.println("Fichero pacientes.txt creado correctamente."); 
            }
        }catch (IOException ex) {
            System.out.println("Error al crear el archivo.");
        }
    }
}
