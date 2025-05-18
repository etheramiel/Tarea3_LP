package Entidades;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Combate.Batalla;
import Javaling.Agua;
import Javaling.Dragon;
import Javaling.Fuego;
import Javaling.Javaling;
import Javaling.Planta;
import Movimientos.DatosMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;
import Piso.Piso;

/*
 * Clase Entrenador
 * Representa a un entrenador de Javalings en el juego.
 * Contiene información sobre su equipo de Javalings, su nombre y si es el campeón.
 */
public class Entrenador implements Batalla{
    
    private boolean esCampeon;
    private Javaling[] equipo;
    private String nombre;


    //Getters y Setters---------------------------------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Javaling[] getEquipo() {
        return equipo;
    }
    public void setEquipo(Javaling[] equipo) {
        this.equipo = equipo;
    }

    public boolean getEsCampeon(){
        return esCampeon;
    }
    //-----------------------------------------------------------------------------

    /*
     * Constructor de la clase Entrenador.
     * Inicializa el equipo de Javalings y determina si el entrenador es un campeón.
     * @param piso El piso en el que se encuentra el entrenador.
     */
    public Entrenador(Piso piso){

        this.equipo = new Javaling[6];
        Random rand = new Random();

        if(piso.getNumPiso() >= 30){
            int chance = 5 + (piso.getNumPiso() - 29);
            this.esCampeon = rand.nextInt(100) < chance;
            if(esCampeon){
                this.nombre = "Gary Oak";
            }
            else{
                this.nombre = "Cazabichos";
            }
        }
        else{
            this.esCampeon = false;
            this.nombre = "Cazabichos";
        }
        generarEquipoAleatorio(piso.getNumPiso());
    }


    /**
     * Nombre: generarJavaling
     * ---------------------------
     * Descripción:
     *   Genera un Javaling aleatorio de un tipo específico y lo inicializa con un nombre y nivel.
     *   Si el Javaling es un campeón, se le asigna un movimiento potente.
     * 
     ****************************************************************************************
    * Parámetros:
    *   - tipo: Tipo de Javaling a generar (AGUA, FUEGO, PLANTA o DRAGON).
    *   - nivel: Nivel del Javaling a generar.
    *   - esCampeon: Indica si el Javaling es un campeón.
    *   - casilla: Indica la posición en el equipo del Javaling en el equipo.
    *   
    **************************************************************************************
    * Retorno:
    *   - Javaling: El Javaling generado.
    ***********************************************************************************
    */
    private Javaling generarJavaling(Tipo tipo, int nivel, boolean esCampeon, int casilla){
        Javaling j;
        String nombre = generarNombrejavaling();

        switch (tipo) {
            case AGUA -> j = new Agua(nombre);
            case FUEGO -> j = new Fuego(nombre);
            case PLANTA -> j = new Planta(nombre);
            case DRAGON -> j = new Dragon(nombre);
            default -> j = new Agua(nombre);
        }

        j.setNivel(nivel);

        boolean tienePotente = false;
        Movimiento[] movs = j.getMovimientos();

        if (esCampeon) {
            for (int i = 0; i < 4; i++) {
                if(movs[i] != null){
                    if(movs[i].getPotencia() >= 100){
                    tienePotente = true;
                    }
                }
            }
        }

        if(!tienePotente && esCampeon){
            Movimiento mov = buscarMovimientoPotente(j);
            if(mov != null){
                j.getMovimientos()[3] = mov;;
            }

        }

        return j;
    }

    /**
     * Nombre: buscarMovimientoPotente
     * ---------------------------
     * Descripción:
     *  Busca un movimiento potente (con potencia >= 100) en la lista de movimientos disponibles.
     *  Si encuentra uno, lo retorna.
     ****************************************************************************************
    * Parámetros:
    *   - j: El Javaling para el cual se busca un movimiento potente.
    *   
    **************************************************************************************
    * Retorno:
    *   - Movimiento: El movimiento potente encontrado, o null si no se encuentra ninguno.
    ***********************************************************************************
    */
    private Movimiento buscarMovimientoPotente(Javaling j){
        
        List<Movimiento> todos = new ArrayList<>();
        todos.addAll(DatosMovimientos.movimientosAgua());
        todos.addAll(DatosMovimientos.movimientosPlanta());
        todos.addAll(DatosMovimientos.movimientosFuego());
        todos.addAll(DatosMovimientos.movimientosDragon());
        // todos.addAll(DatosMovimientos.movimientosNormal());

        for (int i = 0; i < todos.size(); i++) {
            if(todos.get(i).getPotencia() >= 100){
                return todos.get(i);
            }
        }

        return null;
    }

    /**
     * Nombre: generarNombrejavaling
     * ---------------------------
     * Descripción:
     *   Genera un nombre aleatorio para un Javaling combinando un prefijo y un sufijo.
     *  Los prefijos y sufijos son seleccionados aleatoriamente de listas predefinidas.
     ****************************************************************************************
    * Parámetros:
    *   - Ninguno.
    *   
    **************************************************************************************
    * Retorno:
    *   - String: El nombre generado para el Javaling.
    ***********************************************************************************
    */
    public static String generarNombrejavaling(){
        String[] prefijos = {
        "Char", "Lilli", "Meta", "Magi", "Snor", "Eev", "Vapo", "Cubo", "Slow", "Ab", "Odd",
        "Zap", "Drago", "Flare", "Bub", "Pika", "Raich", "Gengar", "Mew", "Luc", "Toxi"
        };

        String[] sufijos = {
        "izard", "pup", "pod", "karp", "lax", "vee", "reon", "bone", "poke", "ra", "dish",
        "chu", "osaur", "dude", "gas", "lock", "bat", "mite", "buzz", "quake", "tail"
        };

        Random rand = new Random();
        String prefijo = prefijos[rand.nextInt(prefijos.length)];
        String sufijo = sufijos[rand.nextInt(sufijos.length)];

        return prefijo + sufijo;
    }

    /**
     * Nombre: elegirTipoAleatorio
     * ---------------------------
     * Descripción:
     *   Genera un tipo aleatorio para un Javaling seleccionando entre AGUA, FUEGO y PLANTA.
     *   El tipo se elige aleatoriamente utilizando un generador de números aleatorios.
     ****************************************************************************************
    * Parámetros:
    *   - rand: El generador de números aleatorios utilizado para seleccionar el tipo.
    *   
    **************************************************************************************
    * Retorno:
    *   - Tipo: El tipo aleatorio seleccionado para el Javaling.
    ***********************************************************************************
    */
    private Tipo elegirTipoAleatorio(Random rand){
        Tipo[] tipos = {Tipo.AGUA, Tipo.FUEGO, Tipo.PLANTA};
        return tipos[rand.nextInt(tipos.length)];
    }

    /**
     * Nombre: calcularCantJavalings
     * ---------------------------
     * Descripción:
     *   Calcula la cantidad de Javalings que el entrenador puede tener en su equipo
     *   según el piso en el que se encuentra.
     *   Si el entrenador es un campeón, siempre tendrá 6 Javalings.
     *   La cantidad de Javalings varía según el piso:
     ****************************************************************************************
    * Parámetros:
    *   - piso: El piso en el que se encuentra el entrenador.
    *   
    **************************************************************************************
    * Retorno:
    *   - int: La cantidad de Javalings que el entrenador puede tener en su equipo.
    ***********************************************************************************
    */
    public int calcularCantJavalings(int piso){

        if(esCampeon) return 6;
        if(piso<= 20) return 1 + new Random().nextInt(2);
        if (piso <= 30) return 2 + new Random().nextInt(2);
        if (piso <= 40) return 3 + new Random().nextInt(2);
        if (piso <= 50) return 4 + new Random().nextInt(2);
        return 6;
    }

    /**
     * Nombre: generarEquipoAleatorio
     * ---------------------------
     * Descripción:
     *   Genera un equipo de Javalings aleatorio para el entrenador.
     *   La cantidad de Javalings y sus niveles se determinan según el piso en el que se encuentra el entrenador.
     *   Si el entrenador es un campeón, se le asigna un nivel mayor.
     *  Los tipos de Javalings se eligen aleatoriamente, y hay una probabilidad de que uno de ellos sea un dragón.
     ****************************************************************************************
    * Parámetros:
    *   - piso: El piso en el que se encuentra el entrenador.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void generarEquipoAleatorio(int piso){
        Random rand = new Random();

        int cantidad = calcularCantJavalings(piso);

        for (int i = 0; i < cantidad; i++) {
            int nivel;
            if(esCampeon){
                nivel = piso + 5;
            }
            else{
                if(piso <= 5){
                    nivel = 4 + rand.nextInt(3);
                }
                else{
                    int base = (int) Math.floor(1.3 * piso);
                    nivel = base + (rand.nextInt(7) - 3);
                    if (nivel < 1) {
                        nivel = 1;
                    }
                }
            }
           
            Tipo tipo;
            boolean esDragon = false;
            
            if(esCampeon){
                if(i == 0){
                    esDragon = true;
                }
                else{
                    esDragon = rand.nextInt(100) < 20;
                }
            }
            else{
                esDragon = rand.nextInt(100) < 5;
            }

            tipo = esDragon ? Tipo.DRAGON : elegirTipoAleatorio(rand);
            Javaling j = generarJavaling(tipo, nivel, esCampeon, i);
            equipo[i] = j;

        }

    }

    /**
     * Nombre: elegirJavalingActivo
     * ---------------------------
     * Descripción:
     *   Selecciona un Javaling activo del equipo del entrenador.
     *   Busca un Javaling que tenga puntos de salud (hp) y lo retorna.
     *   Si no encuentra ninguno, retorna null.
     *   Este método se utiliza para determinar qué Javaling está activo en el combate.
     ****************************************************************************************
    * Parámetros:
    *   - Ninguno.
    *   
    **************************************************************************************
    * Retorno:
    *   - Javaling: El Javaling activo seleccionado, o null si no se encuentra ninguno.
    ***********************************************************************************
    */
    public Javaling elegirJavalingActivo(){

        for (int i = 0; i < equipo.length; i++) {
            if( equipo[i] != null){
                Javaling j = equipo[i];
                if (j.getHpActual() > 0) {
                    return j;
                }
            }   
        }
        return null;
    }

    /**
     * Nombre: mostrarEquipo
     * ---------------------------
     * Descripción:
     *   Muestra la información del equipo de Javalings del entrenador.
     *   Imprime el nombre del entrenador y los detalles de cada Javaling en su equipo.
     ****************************************************************************************
    * Parámetros:
    *   - Ninguno.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void mostrarEquipo() {
    System.out.println(this.nombre);
    for (int i = 0; i < equipo.length; i++) {
        Javaling j = equipo[i];
        if (j != null) {
            System.out.printf("%d. %s (%s) - Nivel %d | HP: %d/%d | Velocidad: %d\n", i + 1, j.getNombre(), j.getTipo(), j.getNivel(), j.getHpActual(), j.getHpTotal(), j.getVelocidad());
        }
    }
    }

    /**
     * Nombre: escogerAtaque
     * ---------------------------
     * Descripción:
     *   Selecciona un ataque aleatorio para el Javaling activo del entrenador.
     *   Recorre los movimientos del Javaling y cuenta cuántos ataques tiene disponibles.
     *   Luego, elige un ataque aleatorio entre los disponibles y lo retorna.
     ****************************************************************************************
    * Parámetros:
    *   - javaling activo: El Javaling activo del entrenador.
    *   
    **************************************************************************************
    * Retorno:
    *   - int: El índice del ataque seleccionado aleatoriamente.
    *          Este índice se utiliza para determinar qué ataque utilizar en el combate.
    ***********************************************************************************
    */
    public int escogerAtaque(Javaling activo){
        int cantAtaques = 0;
        Random rand = new Random();

        for (int i = 0; i < activo.getMovimientos().length; i++) {
            if(activo.getMovimientos()[i] != null){
                cantAtaques++;
            }
        }
        int indice = rand.nextInt(cantAtaques);
        System.out.println("Movimiento entrenador: " + activo.getMovimientos()[indice].getNombre());
        return indice;
    }
}
