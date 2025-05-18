package Objetos;
import java.util.Random;

/*
 * Clase que genera objetos aleatorios.
 * Contiene un metodo que genera un objeto aleatorio.
 */
public class ObjetoAleatorio {
    
    /**
     * Nombre: generarObjetoAleatorio
     * ---------------------------
     * Descripción:
     *   Genera un objeto aleatorio.
     *  Cada objeto tiene su nombre, si es curativo, si revive y su poder.
     ****************************************************************************************
    * Parámetros:
    *   - void: No recibe parámetros.
    *   
    **************************************************************************************
    * Retorno:
    *   - Objeto: Objeto aleatorio generado.
    ***********************************************************************************
    */
    public static Objeto generarObjetoAleatorio(){
        Random rand = new Random();
        int prob = rand.nextInt(100);

        if(prob < 20){
            System.out.println("Has encontrado Pocion");
            return new Objeto("Pocion", true, false, 20);
        }
        else if(prob < 50){
            System.out.println("Has encontrado Superpocion");
            return new Objeto("Superpocion", true, false, 50);
        }
        else if(prob < 60){
            System.out.println("Has encontrado Pocion Maxima");
            return new Objeto("Pocion Maxima", true, false, 100);
        }
        else if(prob < 70){
            System.out.println("Has encontrado Revivir Maximo");
            return new Objeto("Revivir Maximo", true, true, 100);
        }
        else if(prob < 90){
            System.out.println("Has encontrado Caramelo Pequeño");
            return new Objeto("Caramelo Pequeño", false, false, 1);
        }
        else{
            System.out.println("Has encontrado Caramelo Grande");
            return new Objeto("Caramelo Grande", false, false, 2);
        }
    }
}
