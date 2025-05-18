package Piso;
import java.util.Scanner;

import Entidades.Jugador;

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

    public void ejecutarDecision(Jugador jugador){
        if((numPiso - 1) % 10 == 0){
            this.centroSansanito = true;
            curar(jugador);
        }
        else{
            this.centroSansanito = false;
        }
    }

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

        //Logica objeto aleatorio

        System.out.println(">Presione enter para continuar...");
        scanner.nextLine(); 
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }   
}
