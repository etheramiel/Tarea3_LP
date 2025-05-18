package Entidades;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Combate.Batalla;
import Javaling.Javaling;
import Objetos.Objeto;
import Piso.Piso;

/**
 * Clase Jugador que representa al jugador en el juego.
 * Implementa la interfaz Batalla para permitir la selección de un Javaling activo.
 */
public class Jugador implements Batalla{
    
    private String nombre;
    private List<Objeto> bolsa;
    private Javaling[] equipo; //= new Javaling[6];
    private Piso pisoActual;

    /*
     * Constructor de la clase Jugador.
     * Inicializa el equipo de javalings y la bolsa de objetos.
     */
    public Jugador(){
        this.equipo = new Javaling[6];
        this.bolsa = new ArrayList<>();
        this.pisoActual = new Piso();
        this.pisoActual.setNumPiso(1);
    }

    //Getters y Setters ------------------------------------------------------------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Objeto> getBolsa() {
        return bolsa;
    }
    public void setBolsa(List<Objeto> bolsa) {
        this.bolsa = bolsa;
    }

    public Javaling[] getEquipo() {
        return equipo;
    }
    public void setEquipo(Javaling[] equipo) {
        this.equipo = equipo;
    }

    public Piso getPisoActual() {
        return pisoActual;
    }
    public void setPisoActual(Piso pisoActual) {
        this.pisoActual = pisoActual;
    }
    //-----------------------------------------------------------------------------------


    /**
    * Nombre: elegirJavalingActivo
    * ---------------------------
    * Descripción:
    *   Esta función permite al jugador elegir un Javaling activo de su equipo.
    *  Si el jugador no tiene javalings disponibles, se le informa y se termina el juego.
    *  Si el jugador elige un javaling que está fuera de combate, se le informa y se le pide que elija otro.
    *  Si el jugador elige un javaling válido, se retorna el javaling elegido.
    *  Si el jugador elige un número fuera del rango, se le informa y se le pide que elija otro.
    ****************************************************************************************
    * Parámetros:
    *   - void: No recibe parámetros.
    *   
    **************************************************************************************
    * Retorno:
    *   - Javaling: Retorna el javaling elegido por el jugador.
    ***********************************************************************************
    */
    public Javaling elegirJavalingActivo(){
        Scanner scanner = new Scanner(System.in);

        while(true){

            boolean hayDisponible = false;

            for (int i = 0; i < equipo.length; i++) {
                if (equipo[i] != null && equipo[i].getHpActual() > 0) {
                    hayDisponible = true;
                }
            }

            if(!hayDisponible){
                System.out.println("Todos tus javalings han quedado fuera de combate");
                return null;
            }

            System.out.println("Elige tu javaling activo:");
            for (int i = 0; i < equipo.length; i++) {
                Javaling j = equipo[i];
                if (j != null) {
                    String estado = j.getHpActual() > 0 ? "Disponible" : "KO";
                    System.out.println(i+1 + " " + j.getNombre() + " " + " " + j.getHpActual() + "/" + j.getHpTotal() + " " + j.getTipo() + " " + estado);
                }
                else{
                    System.out.println(i+1 + ". Vacio");
                }
                

            }
            System.out.println("\n>Ingrese el numero de Javaling:");
            int opcion = scanner.nextInt();

            if(opcion < 1 || opcion > 6){
                System.out.println(">Opcion no valida");
                continue;
            }

            Javaling elegido = equipo[opcion - 1];

            if(elegido == null){
                System.out.print("\033[H\033[2J");
                System.out.println("Esta casilla está vacia");
                continue;
            }
            else if(elegido.getHpActual() == 0){
                System.out.print("\033[H\033[2J");
                System.out.println("Javaling fuera de combate :(");
                continue;
            }
            else{
                System.out.print("\033[H\033[2J");
                return elegido;
            }

        }
        
    }

    /**
     * Nombre: agregarJavaling
     * ---------------------------
     * Descripción:
     *   Esta función permite agregar un nuevo Javaling al equipo del jugador.
     *   Si el equipo ya tiene 6 javalings, se le pregunta al jugador si desea reemplazar uno.
     *   Si el jugador decide reemplazar uno, se le muestra el equipo y se le pide que elija el javaling a eliminar.
     *   Si el jugador decide no reemplazar, se informa que el javaling no fue agregado.
     ****************************************************************************************
    * Parámetros:
    *   - nuevo: El nuevo javaling a agregar al equipo.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void agregarJavaling(Javaling nuevo){
        Scanner scanner = new Scanner(System.in);
        int contador = 0;

        for (int i = 0; i < equipo.length; i++) {
            if(equipo[i] != null){
                contador++;
            }
        }

        if(contador < 6){
            for (int i = 0; i < equipo.length; i++) {
                if(equipo[i] == null){
                    equipo[i] = nuevo;
                    return;
                }
            }
        }
        else{
            System.out.println("Tu equipo ya tiene 6 Javaling ¿Deseas reemplazar uno?\n1. Si\n2. No");
            int opcion = scanner.nextInt();
            ;
            switch (opcion) {
                case 2 -> {
                    System.out.println("El javaling no fue agregado");
                    return;
                }
                case 1 -> {
                    printEquipo();
                    System.out.println("Ingrese el numero de pokemon a eliminar:");
                    int eliminar = scanner.nextInt();
                    eliminarJavaling(eliminar - 1);
                    equipo[eliminar - 1] = nuevo;
                    break;
                }
                default -> {
                    return;
                }
            }
        }

    }
    
    /**
     * Nombre: eliminarJavaling
     * ---------------------------
     * Descripción:
     *   Esta función permite eliminar un javaling del equipo del jugador.
     *   Se le pide al jugador que elija el javaling a eliminar.
     *   Si el jugador elige un número fuera del rango, se le informa y se termina la función.
     ****************************************************************************************
    * Parámetros:
    *   - indice: El índice del javaling a eliminar del equipo.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void eliminarJavaling(int indice){
        this.equipo[indice] = null;
    }



    /**
     * Nombre: printEquipo
     * ---------------------------
     * Descripción:
     *   Esta función imprime el estado del equipo del jugador.
     *   Muestra el nombre, tipo, HP actual, velocidad y nivel de cada javaling en el equipo.
     *   Si el javaling está vacío, se indica que la casilla está vacía.
     ****************************************************************************************
    * Parámetros:
    *   - null: No recibe parámetros.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void printEquipo(){
        for (int i = 0; i < equipo.length; i++) {
            if(equipo[i] != null){
                System.out.println(i+1 + ". Nombre: " + equipo[i].getNombre() + " || Tipo: " + equipo[i].getTipo() + " || HP: " + equipo[i].getHpActual() + "/"+equipo[i].getHpTotal() + " || Velocidad: " + equipo[i].getVelocidad() + " || Nivel: " + equipo[i].getNivel());
            }
            else{
                System.out.println(i+1 + ". Vacío");
            }
        }

        // String[] lista = new String[6];
        // for (int i = 0; i < equipo.length; i++) {
        //     if (equipo[i] == null) {
        //         lista[i] = "Vacío";
        //     }
        //     else{
        //         lista[i] = equipo[i].getNombre();
        //     }
        // }
        // System.out.println(Arrays.toString(lista));
    }

    /**
    * Nombre: subirEquipoNivel
    * ---------------------------
    * Descripción:
    *   Esta función permite subir el nivel de todos los javalings del equipo.
    *   segundo el nivel especificado.
    *   
    ****************************************************************************************
    * Parámetros:
    *   - tablero: Puntero a la estructura Tablero que se va a inicializar.
    *   - filas: Número de filas del tablero.
    *   - columnas: Número de columnas del tablero.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void subirEquipoNivel(int nivel){
        for (int i = 0; i < equipo.length; i++) {
            if(equipo[i] != null){
                equipo[i].subirNivel(nivel);
            }
        }
        if (nivel == 1) {
            System.out.println("Todos tus Javalings han subido " + nivel + " nivel");
        }
        else{
            System.out.println("Todos tus Javalings han subido " + nivel + " niveles");
        }
        
    }

    /**
    * Nombre: agregarObjeto
    * ---------------------------
    * Descripción:
    *   Esta función permite agregar un nuevo objeto a la bolsa del jugador.
    *   Si el objeto ya existe en la bolsa, se aumenta su cantidad.
    *   Si el objeto no existe, se agrega a la bolsa.
    ****************************************************************************************
    * Parámetros:
    *   - objeto: El nuevo objeto a agregar a la bolsa.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void agregarObjeto(Objeto nuevo){
        for (int i = 0; i < bolsa.size(); i++) {
            Objeto o = bolsa.get(i);
            if (o.getNombre().equals(nuevo.getNombre())) {
                o.aumentarCantidad();
                return;
            }
        }
        bolsa.add(nuevo);
    }


    /**
    * Nombre: usarObjeto
    * ---------------------------
    * Descripción:
    *   Esta función permite al jugador usar un objeto de su bolsa.
    *   Si la bolsa está vacía, se informa al jugador.
    *   Si el jugador elige un objeto que no tiene, se le informa y se termina la función.
    ****************************************************************************************
    * Parámetros:
    *   - Scanner scanner: El objeto Scanner para leer la entrada del usuario.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void usarObjeto(Scanner scanner){
        if(bolsa.isEmpty()){
            System.out.println(">Tu bolsa está vacia");
            return;
        }

        System.out.println(">Objetos Disponibles:");

        for (int i = 0; i < bolsa.size(); i++) {
            Objeto o = bolsa.get(i);
            if (o.getCantidad() > 0) {
                System.out.println(i+1  + ". " + o.getNombre() + " x" + o.getCantidad());
            }
        }
        System.out.println("\n>Elige un objeto para usar \n(Ingresa 9 para salir)");
        int opcion = scanner.nextInt();

        if (opcion == 9) {
            return;
        }
        opcion--;
        if(opcion < 0 || opcion > bolsa.size()){
            return;
        }

        Objeto o = bolsa.get(opcion);

        if (o.getCantidad() <= 0) {
            System.out.println("No tienes ese objeto.");
            return;
        }

        o.usar(this, scanner);

        if (o.getCantidad() == 0) {
            bolsa.remove(opcion);
        }
    }

}
