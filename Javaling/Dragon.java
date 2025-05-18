package Javaling;
import Movimientos.GeneradorMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;

/*
 * Clase que representa un Javaling de tipo Dragon.
 * Hereda de la clase Javaling.
 */
public class Dragon extends Javaling{
    private boolean multiescamas;

    /*
     * Constructor de la clase Dragon.
     * Inicializa los atributos de la clase padre Javaling
     */
    public Dragon(String nombre){
        super(nombre, 70 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.DRAGON);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.DRAGON);
        this.setMovimientos(movs);
    }

/**
 * Nombre: iniciarTurno
 * ---------------------------
 * Descripción:
 *   Inicializa el estado del Javaling al inicio de su turno.
 *   Si el Javaling tiene la vida máxima,
 *   se activa la habilidad de multiescamas.
 * 
 ****************************************************************************************
 * Parámetros:
 *   - Ninguno.
 *   
 **************************************************************************************
 * Retorno:
 *   - void: No retorna nada.
 ***********************************************************************************
 */
    public void iniciarTurno() {
        multiescamas = (getHpActual() == getHpTotal());
    }


/**
 * Nombre: recibirDaño
 * ---------------------------
 * Descripción:
 *   Recibe el daño del ataque del enemigo.
 *   Si el Javaling tiene la habilidad de multiescamas,
 *   reduce el daño recibido al 10%.
 *   Luego, llama al método de la clase padre para aplicar el daño.
 ****************************************************************************************
 * Parámetros:
 *   - danio: Daño infligido al Javaling.
 *   
 **************************************************************************************
 * Retorno:
 *   - void: No retorna nada.
 ***********************************************************************************
 */
    public void recibirDaño(int danio) {
        if (multiescamas) {
            danio = (int)(danio * 0.1);
            System.out.println(getNombre() + " redujo el daño con multiescamas");
        }
        super.recibirDaño(danio);
        multiescamas = false;
    }

    public void restaurarEstadoCombate() {
    super.restaurarEstadoCombate();
    this.multiescamas = false;
    }
}
