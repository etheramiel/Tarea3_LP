package Javaling;
import Movimientos.GeneradorMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;
/**
 * Clase Planta que representa un tipo de Javaling.
 * Hereda de la clase Javaling.
 */
public class Planta extends Javaling{
    private int fotosintesis = 0;

    /**
     * Constructor de la clase Planta.
     * Inicializa los atributos de la clase padre Javaling
     * y genera los movimientos específicos de tipo Planta.
     * 
     */
    public Planta(String nombre){
        super(nombre, 65 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.PLANTA);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.PLANTA);
        this.setMovimientos(movs);
    }

    /**
     * Nombre: iniciarTurno
     * ---------------------------
     * Descripción:
     *  Inicializa el estado del Javaling al inicio de su turno.
     ****************************************************************************************
    * Parámetros:
    *   - void: No recibe parámetros.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void iniciarTurno() {
        if (getHpActual() > 0) {
            int curar = (int)(getHpTotal() * 0.05);
            setHpActual(Math.min(getHpActual() + curar, getHpTotal()));
            System.out.println(getNombre() + " se curó " + curar + " puntos por fotosíntesis.");
        }   
    }

    /**
     * Nombre: restaurarEstadoCombate
     * ---------------------------
     * Descripción:
     *   Inicializa el estado del Javaling al inicio de su turno.
     ****************************************************************************************
    * Parámetros:
    *   - void: No recibe parámetros.
    *
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void restaurarEstadoCombate() {
    super.restaurarEstadoCombate();
    }
}
