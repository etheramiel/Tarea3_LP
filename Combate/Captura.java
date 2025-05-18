package Combate;
import java.util.Random;
import java.util.Scanner;
import Javaling.Javaling;

import Entidades.Jugador;
import Piso.Piso;

/*
 * Clase que maneja la captura de un Javaling salvaje.
*
 */
public class Captura {

        /**
     * Nombre: intentarCapturar
     * ---------------------------
     * Descripción:
     *  Esta función permite al jugador intentar capturar un Javaling salvaje.
     *  Si el jugador decide no capturarlo, el Javaling puede escapar.
     * Si el jugador captura el Javaling, se le da la opción de cambiarle el nombre.
     * El jugador también sube de nivel al capturar un Javaling.
     ****************************************************************************************
    * Parámetros:
    *   - jugador: El jugador que intenta capturar el Javaling.
    *   - piso: El piso en el que se encuentra el jugador.
    *   - scanner: El objeto Scanner para leer la entrada del usuario.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public static void intentarCapturar(Jugador jugador, Piso piso, Scanner scanner){
        Random rand = new Random();
        Javaling salvaje = Javaling.generadorSalvaje(piso);
        System.out.println("Un " + salvaje.getNombre() + " salvaje ha aparecido. Tipo: " + salvaje.getTipo() + ". Nivel: " + salvaje.getNivel() + ". HP Total: " + salvaje.getHpTotal());

        boolean capturado = false;
        boolean escapado = false;

        while (!escapado && !capturado) {
            System.out.println("\n>¿Intentar capturar?\n1. Si / 2. No");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if(opcion == 2){
                System.out.println("Decidiste dejarlo escapar");
                System.out.println(">Presiona enter para continuar...");
                scanner.nextLine();
                System.out.print("\033[H\033[2J");
                return;
            }

            int captura = rand.nextInt(100);

            if (captura < 40) {
                System.out.println("Lo has capturado con exito");
                System.out.println(">¿Deseas cambiarle el nombre?\n1. Si / 2. No");
                int cambiarNombre = scanner.nextInt();
                scanner.nextLine();
                if (cambiarNombre == 1) {
                    System.out.println("Ingrese nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    salvaje.setNombre(nuevoNombre);
                }
                System.out.print("\033[H\033[2J");
                jugador.subirEquipoNivel(1);
                System.out.println("\n");
                jugador.agregarJavaling(salvaje);
                salvaje.printDatos();
                System.out.println("\n");
                salvaje.printMovimientos();
                capturado = true;
            }
            else{
                int escapa = rand.nextInt(100);
                if (escapa < 30) {
                    System.out.println(salvaje.getNombre() + " salvaje ha escapado");
                    escapado = true;
                }
                else{
                    System.out.println("Fallaste, pero el Javaling no ha escapado. Puedes intentarlo de nuevo");
                    System.out.println(">Presiona enter para continuar...");
                    scanner.nextLine();
                    System.out.print("\033[H\033[2J");
                }
            }

        }
        System.out.println(">Presiona enter para continuar...");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");


    }

}
