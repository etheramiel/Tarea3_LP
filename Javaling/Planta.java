package Javaling;
import Movimientos.GeneradorMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;

public class Planta extends Javaling{
    private int fotosintesis = 0;

    public Planta(String nombre){
        super(nombre, 65 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.PLANTA);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.PLANTA);
        this.setMovimientos(movs);
    }

    public void iniciarTurno() {
        if (getHpActual() > 0) {
            int curar = (int)(getHpTotal() * 0.05);
            setHpActual(Math.min(getHpActual() + curar, getHpTotal()));
            System.out.println(getNombre() + " se curó " + curar + " puntos por fotosíntesis.");
        }   
    }

    public void restaurarEstadoCombate() {
    super.restaurarEstadoCombate();
    }
}
