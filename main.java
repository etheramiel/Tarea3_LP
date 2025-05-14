import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Javaling javalingInicial;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Portero: Bienvenido al rascacielos joven, acá te enfrentarás con diversos desafios para poder convertirte en el mejor entrenador Javaling de la USM (y del mundo) y ganar la gran recompensa.\nPortero: Para poder dejarte pasar necesito tu nombre.");
        System.out.println("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        Jugador jugador = new Jugador();
        jugador.setNombre(nombre);
        System.out.println("Portero: Bienvenido " + jugador.getNombre() + ", espero que estés listo para este desafío.");

        System.out.println("Portero: Para iniciar tu aventura tendrás que escoger un Javaling inicial.\nPortero: Que tipo deseas?");
        System.out.println("1.Agua / 2.Fuego / 3.Planta / 4.Dragon");
        String tipoString = "";

        while(!tipoString.equals("1") && !tipoString.equals("2") && !tipoString.equals("3")){
            tipoString = scanner.nextLine();
            if (tipoString.equals("4")) {
                System.out.println("Portero: Aun no tienes la habilidad suficiente para llevar un Javaling de tipo Dragon, escoge otro.");
                System.out.println("1.Agua / 2.Fuego / 3.Planta / 4.Dragon");
            }
        }
        System.out.println("Portero: Que nombre le pondrá a su Javaling inicial");
        System.out.println("Ingrese el nombre de su primer Javaling: ");
        nombre = scanner.nextLine();
        switch (tipoString) {
            case "1" -> javalingInicial = new Agua(nombre);
            case "2" -> javalingInicial = new Fuego(nombre);
            case "3" -> javalingInicial = new Planta(nombre);
            default -> {System.out.println("Tipo no válido: " + tipoString);
            scanner.close();
            return;}
        }

        jugador.printEquipo();

        
        System.out.println("\n✅ Tu Javaling fue creado:");
        System.out.println("Nombre: " + javalingInicial.getNombre());
        System.out.println("Tipo: " + javalingInicial.getTipo());
        System.out.println("Nivel: " + javalingInicial.getNivel());
        System.out.println("HP Total: " + javalingInicial.getHpTotal());
        System.out.println("Velocidad: " + javalingInicial.getVelocidad());

        System.out.println("\nMovimientos:");
        Movimiento[] movs = javalingInicial.getMovimientos();
        for (int i = 0; i < movs.length; i++) {
            System.out.print((i + 1) + ". ");
            if (movs[i] != null) {
                System.out.println(movs[i].getNombre() + " | Tipo: " + movs[i].getTipo() +
                                   " | Potencia: " + movs[i].getPotencia() +
                                   " | Estado: " + (movs[i].getEstado() ? "Sí" : "No"));
            } else {
                System.out.println("Movimiento no asignado.");
            }
        }

        scanner.close();
    }

    
}
