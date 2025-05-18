package Javaling;
import Movimientos.GeneradorMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;

public class Dragon extends Javaling{
    private boolean multiescamas;

    public Dragon(String nombre){
        super(nombre, 70 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.DRAGON);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.DRAGON);
        this.setMovimientos(movs);
    }

    public void iniciarTurno() {
        multiescamas = (getHpActual() == getHpTotal());
    }


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
