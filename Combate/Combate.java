package Combate;
import java.util.Scanner;
import Javaling.Javaling;

import Entidades.Entrenador;
import Entidades.Jugador;

public class Combate {
    public static boolean iniciarCombate(Jugador jugador, Entrenador entrenador){
        Scanner scanner = new Scanner(System.in);
        if(entrenador.getEsCampeon()){
            System.out.println("\n\n\n\n          ALERTA!!!!!\n Te has encontrado con un campeon Javaling.\nSi ganas este combate te convertirás en el nuevo campeon Javaling, si pierdes, tendrás que intentarlo de nuevo desde el comienzo\n\n\n");
        }

        System.out.println("\nComienza el combate contra " + entrenador.getNombre() + "\n");
        Javaling jugadorActivo = jugador.elegirJavalingActivo();
        Javaling enemigoActivo = entrenador.elegirJavalingActivo();
        System.out.printf("\nJavaling Activo de %s: %s | HP: %d/%d | (%s)\n\n", jugador.getNombre(), jugadorActivo.getNombre(), jugadorActivo.getHpActual(), jugadorActivo.getHpTotal(), jugadorActivo.getTipo());
        System.out.printf("\nJavaling Activo de %s: %s | HP: %d/%d | (%s)\n\n", entrenador.getNombre(), enemigoActivo.getNombre(), enemigoActivo.getHpActual(), enemigoActivo.getHpTotal(), enemigoActivo.getTipo());
        while (true) {
            jugadorActivo.iniciarTurno();
            enemigoActivo.iniciarTurno();
             if (jugadorActivo.getHpActual() == 0) {
                System.out.printf("Tu javaling %s ha quedado fuera de combate :(\n", jugadorActivo.getNombre());
                jugadorActivo = jugador.elegirJavalingActivo();
                if(jugadorActivo == null){
                    System.out.println("Has sido derrotado");
                    System.out.printf("%s se ha quedado sin puntos de salud!\n", enemigoActivo.getNombre());
                    System.out.println(">Presiona enter para continuar...");
                    scanner.nextLine();
                    return false;
                }else{
                    System.out.printf("\nJavaling Activo de %s: %s | HP: %d/%d | (%s)\n\n", jugador.getNombre(), jugadorActivo.getNombre(), jugadorActivo.getHpActual(), jugadorActivo.getHpTotal(), jugadorActivo.getTipo());
                }
            }
            if(enemigoActivo.getHpActual() == 0){
                System.out.printf("%s se ha quedado sin puntos de salud!\n", enemigoActivo.getNombre());
                System.out.println(">Presiona enter para continuar...");
                scanner.nextLine();
                System.out.print("\033[H\033[2J");
                enemigoActivo = entrenador.elegirJavalingActivo();
                if(enemigoActivo == null){
                    System.out.printf("%s ha sido derrotado!\n", entrenador.getNombre());
                    jugador.subirEquipoNivel(2);
                    for (Javaling j : jugador.getEquipo()) {
                        if (j != null) j.restaurarEstadoCombate(); 
                    }
                    System.out.println(">Presiona enter para continuar...");
                    scanner.nextLine();
                    return true;
                }
                else{
                    System.out.printf("\nJavaling Activo de %s: %s | HP: %d/%d | (%s)\n\n", jugador.getNombre(), jugadorActivo.getNombre(), jugadorActivo.getHpActual(), jugadorActivo.getHpTotal(), jugadorActivo.getTipo());
                    System.out.printf("\nJavaling Activo de %s: %s | HP: %d/%d | (%s)\n\n", entrenador.getNombre(), enemigoActivo.getNombre(), enemigoActivo.getHpActual(), enemigoActivo.getHpTotal(), enemigoActivo.getTipo());
                }   
            }
            System.out.println("\n>¿Que accion deseas realizar?\n1. Atacar\n2. Cambiar Javaling Activo\n3. Escapar");
            int decision = scanner.nextInt();

            if (decision == 1) {
                // System.out.print("\033[H\033[2J");
                System.out.println(">¿Que ataque deseas utilizar?");
                jugadorActivo.printMovimientos();
                int movimiento = -1;
                do {
                    System.out.print("> Elige movimiento (1-4): ");
                    movimiento = scanner.nextInt();
                    System.out.print("\n");
                } while (movimiento < 1 || movimiento > 4 || jugadorActivo.getMovimientos()[movimiento - 1] == null);

                if(jugadorActivo.getVelocidad() > enemigoActivo.getVelocidad()){
                    jugadorActivo.atacar(enemigoActivo, movimiento - 1);
                    if (enemigoActivo.getHpActual() > 0) {
                        int movEntrenador = entrenador.escogerAtaque(enemigoActivo);
                        enemigoActivo.atacar(jugadorActivo, movEntrenador);
                    }
                }
                else{
                    int movEntrenador = entrenador.escogerAtaque(enemigoActivo);
                    enemigoActivo.atacar(jugadorActivo, movEntrenador);
                    if (jugadorActivo.getHpActual() > 0) {
                        jugadorActivo.atacar(enemigoActivo, movimiento - 1);
                    }
                }
            }
            else if (decision == 2) {
                // System.out.print("\033[H\033[2J");
                jugadorActivo = jugador.elegirJavalingActivo();
                int movEntrenador = entrenador.escogerAtaque(enemigoActivo);
                enemigoActivo.atacar(jugadorActivo, movEntrenador);
            }
            else if(decision == 3){
                return true;
            }
            
           
            System.out.println("Tu javaling activo: " + jugadorActivo.getNombre() + " " + jugadorActivo.getHpActual() + "/" + jugadorActivo.getHpTotal());
            System.out.println("Javaling enemigo: " + enemigoActivo.getNombre() + " " + enemigoActivo.getHpActual() + "/" + enemigoActivo.getHpTotal());
            System.out.println(">Presione enter para continuar...");
            scanner.nextLine(); 
            scanner.nextLine(); 
            System.out.print("\033[H\033[2J");
            System.out.flush();

        }

    }
}
