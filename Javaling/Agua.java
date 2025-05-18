package Javaling;
import Movimientos.GeneradorMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;

public class Agua extends Javaling{

    private boolean oleaje = false;
    private boolean fueAtacadoTurnoAnterior = false;

    //Getters y setters--------------
    public boolean getOleaje(){
        return oleaje;
    }
    //-------------------------------

    
    public Agua(String nombre){
        super(nombre, 55 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.AGUA);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.AGUA);
        this.setMovimientos(movs);
    }

        
    public double getMultiplicadorHabilidad(Movimiento mov, Javaling objetivo) {
    if (oleaje) {
        oleaje = false;
        return 1.15;
    }
        return 1.0;
    }

    public void recibirDaño(int danio) {
        super.recibirDaño(danio);
        fueAtacadoTurnoAnterior = danio > 0;
    }

    public void iniciarTurno() {
        oleaje = fueAtacadoTurnoAnterior;
        fueAtacadoTurnoAnterior = false;
    }

    public void restaurarEstadoCombate() {
    super.restaurarEstadoCombate();
    this.oleaje = false;
    this.fueAtacadoTurnoAnterior = false;
    }
}
