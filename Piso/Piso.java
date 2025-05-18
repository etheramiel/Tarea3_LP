package Piso;
import java.util.Scanner;

import Entidades.Jugador;
import Objetos.Objeto;
import Objetos.ObjetoAleatorio;

/*
 * Clase Piso
 * Representa un piso del juego
 * Contiene atributos como el número de piso, si es un centro Sansanito y la decisión del jugador.
 */
public class Piso {
    
    private boolean centroSansanito;
    private int decision;
    public int numPiso;



    //Getters y setterts --------------------------------------
    public int getDecision() {
        return decision;
    }
    public void setDecision(int decision) {
        this.decision = decision;
    }

    public int getNumPiso() {
        return numPiso;
    }
    public void setNumPiso(int numPiso) {
        this.numPiso = numPiso;
    }

    public boolean getCentroSansanito() {
        return centroSansanito;
    }
    public void setCentroSansanito(boolean centroSansanito) {
        this.centroSansanito = centroSansanito;
    }
    //------------------------------------------------------------------------

    /**
     * Nombre: ejecutarDecision
     * ---------------------------
     * Descripción:
     *   Esta función se encarga de ejecutar la decisión del jugador en el piso actual.
     *   Si el número de piso es restado en 1 es múltiplo de 10, se activa el centro Sansanito y se curan los Javalings.
     *   En caso contrario, se desactiva el centro Sansanito.
     ****************************************************************************************
    * Parámetros:
    *   - Jugador jugador: El jugador que está jugando actualmente.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void ejecutarDecision(Jugador jugador){
        if((numPiso - 1) % 10 == 0){
            this.centroSansanito = true;
            curar(jugador);
        }
        else{
            this.centroSansanito = false;
        }
    }

    /**
     * Nombre: curar
     * ---------------------------
     * Descripción:
     *   Esta función se encarga de curar a todos los Javalings del jugador.
     *   Se establece el HP actual de cada Javalings al HP total.
     ****************************************************************************************
    * Parámetros:
    *   - Jugador jugador: El jugador que está jugando actualmente.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    *   Se imprime un mensaje indicando que todos los Javalings han sido curados.
    ***********************************************************************************
    */
    public void curar(Jugador jugador){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al centro Sansanito, acá tus Javalings serán curados");

        for (int i = 0; i < jugador.getEquipo().length; i++) {
            if (jugador.getEquipo()[i] != null) {
                jugador.getEquipo()[i].setHpActual(jugador.getEquipo()[i].getHpTotal());
            }
        }

        System.out.println(".....");
        System.out.println(".....");
        System.out.println("Todos tus Javalings han sido curados");

        Objeto objeto = ObjetoAleatorio.generarObjetoAleatorio();
        jugador.agregarObjeto(objeto);

        objeto = ObjetoAleatorio.generarObjetoAleatorio();
        jugador.agregarObjeto(objeto);

        System.out.println(">Presione enter para continuar...");
        scanner.nextLine(); 
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }   
}
