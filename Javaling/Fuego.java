package Javaling;
import java.nio.channels.Pipe.SourceChannel;

import Movimientos.GeneradorMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;

public class Fuego extends Javaling{
    private int enLlamas;
    private boolean estadoEnLlamas;

    public Fuego(String nombre){
        super(nombre, 60 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.FUEGO);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.FUEGO);
        this.setMovimientos(movs);
    }

    public double getMultiplicadorHabilidad(Movimiento mov, Javaling ovjetivo){
        if(mov.getPotencia() > 0){
            enLlamas++;
        }
        if(!estadoEnLlamas && enLlamas >= 3){
            estadoEnLlamas = true;
            System.out.println(getNombre() + " ha entrado en llamas (daño x1.2)");
        }
        return estadoEnLlamas ? 1.2 : 1.0;
    }

    public void restaurarEstadoCombate() {
        super.restaurarEstadoCombate();
        this.enLlamas = 0;
        this.estadoEnLlamas = false;
    }
}
