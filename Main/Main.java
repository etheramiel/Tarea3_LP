package Main;
import java.util.Scanner;

import Combate.Captura;
import Combate.Combate;
import Entidades.Entrenador;
import Entidades.Jugador;
import Javaling.Agua;
import Javaling.Fuego;
import Javaling.Javaling;
import Javaling.Planta;
import Miscelaneos.AsciiArt;
import Objetos.ObjetoAleatorio;

/*
 * Main.java
 * 
 */
public class Main {

    /**
     * Nombre: main
     * ---------------------------
     * Descripción:
     *   Inicializa el juego, solicita el nombre del jugador y el tipo de Javaling inicial.
     *   Luego inicia un bucle donde el jugador puede elegir entre pelear contra un entrenador,
     *   buscar un Javaling salvaje, encontrar un objeto aleatorio, ver su equipo, usar un objeto
     *   o ver los movimientos de su Javaling.
     *   Si el jugador es derrotado, se termina el juego.
     *   
     ****************************************************************************************
    * Parámetros:
    *   - args: Argumentos de la línea de comandos (no se utilizan).
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public static void main(String[] args) {
        AsciiArt.printTorre();

        Javaling javalingInicial;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Portero: Bienvenido al rascacielos joven, acá te enfrentarás con diversos desafios para poder convertirte en el mejor entrenador Javaling de la USM (y del mundo) y ganar la gran recompensa.\nPortero: Para poder dejarte pasar necesito tu nombre.");
        System.out.println(">Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("\033[H\033[2J");

        Jugador jugador = new Jugador();
        jugador.setNombre(nombre);
        System.out.println("Portero: Bienvenido " + jugador.getNombre() + ", espero que estés listo para este desafío.");

        System.out.println("Portero: Para iniciar tu aventura tendrás que escoger un Javaling inicial.\nPortero: Que tipo deseas?");
        System.out.println(">1.Agua / 2.Fuego / 3.Planta / 4.Dragon");
        String tipoString = "";
        

        while(!tipoString.equals("1") && !tipoString.equals("2") && !tipoString.equals("3")){
            tipoString = scanner.nextLine();
            if (tipoString.equals("4")) {
                System.out.print("\033[H\033[2J");
                System.out.println("Portero: Aun no tienes la habilidad suficiente para llevar un Javaling de tipo Dragon, escoge otro.");
                System.out.println(">1. Agua / 2. Fuego / 3. Planta / 4. Dragon");
            }
        }
        System.out.print("\033[H\033[2J");

        System.out.println("Portero: Que nombre le pondrá a su Javaling inicial");
        System.out.println(">Ingrese el nombre de su primer Javaling: ");
        nombre = scanner.nextLine();
        switch (tipoString) {
            case "1" -> javalingInicial = new Agua(nombre);
            case "2" -> javalingInicial = new Fuego(nombre);
            case "3" -> javalingInicial = new Planta(nombre);
            default -> {System.out.println("Tipo no válido: " + tipoString);
            scanner.close();
            return;}
        }
        System.out.print("\033[H\033[2J");
        javalingInicial.setNivelCaptura(1);
        jugador.agregarJavaling(javalingInicial);
        javalingInicial.printDatos();
        javalingInicial.printMovimientos();


        System.out.println(">Presiona enter para continuar...");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        boolean avanzarPiso = true;
        boolean derrotado = true;

        while (derrotado) {
            
            System.out.print("\033[H\033[2J");
            System.out.flush();
            int pisoActual = jugador.getPisoActual().getNumPiso();
            String titulo = "PISO " + pisoActual;
            String borde = "=".repeat(titulo.length() + 10);
            System.out.println(borde);
            System.out.println("     " + titulo + "    ");
            System.out.println(borde);
            System.out.println(">¿Que deseas hacer?\n1. Pelear contra un entrenador | 2. Buscar Javaling Salvaje | 3. Encontrar Objeto Aleatorio\n4. Ver equipo | 5. Usar objeto | 6. Ver movimientos");
            String opcion = scanner.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            switch(opcion){
                case "1" -> {
                    Entrenador rival = new Entrenador(jugador.getPisoActual());
                    rival.mostrarEquipo();
                    derrotado = Combate.iniciarCombate(jugador, rival);
                    if (rival.getEsCampeon() && derrotado) {
                        System.out.println("Felicidades, Shinji... perdón, " + jugador.getNombre() + ".");
                        System.out.println("Has completado el juego. Omedetou... ¿pero realmente te sientes satisfecho?");
                        return;
                    }
                    avanzarPiso = true;
                }
                case "2" -> {
                    Captura.intentarCapturar(jugador, jugador.getPisoActual(), scanner);
                    avanzarPiso = true;
                }
                case "3" -> {
                    jugador.agregarObjeto(ObjetoAleatorio.generarObjetoAleatorio());
                    System.out.println(">Presione enter para continuar...");
                    scanner.nextLine(); 
                    System.out.print("\033[H\033[2J");
                    avanzarPiso = true;
                }
                case "4" -> {
                    jugador.printEquipo();
                    avanzarPiso = false;
                    System.out.println(">Presione enter para continuar...");
                    scanner.nextLine(); 
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    continue;
                }
                case "5" -> {
                    jugador.usarObjeto(scanner);
                    avanzarPiso = false;
                    System.out.println(">Presione enter para continuar...");
                    scanner.nextLine(); 
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    continue;
                }
                case "6" -> {
                    jugador.printEquipo();
                    System.out.println(">Seleccione Javaling: ");
                    int movimientoVer = scanner.nextInt();
                    scanner.nextLine();
                    jugador.getEquipo()[movimientoVer - 1].printMovimientos();
                    System.out.println(">Presione enter para continuar...");
                    scanner.nextLine(); 
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    avanzarPiso = false;
                    continue;
                }
                default -> {
                    System.out.println("Opcion Invalida");
                    avanzarPiso = false;
                }
            }
 

            if (avanzarPiso) {
                jugador.getPisoActual().setNumPiso(jugador.getPisoActual().getNumPiso() + 1);
                jugador.getPisoActual().ejecutarDecision(jugador);
            }

        }

        if (!derrotado) {
            System.out.println("Lo lamento, has sido derrotado. No eres el mejor entrenador Javaling :c\nEl tercer impacto es inevitable ahora.");
        }

    }



    
}
