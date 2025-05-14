import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GeneradorMovimientos {
    public static Movimiento[] generadorMovimientos(Tipo tipoPrincipal){
        Movimiento[] resultado = new Movimiento[4];
        Random rand = new Random();

        List<Movimiento> tipoPropio = switch (tipoPrincipal) {
            case AGUA -> DatosMovimientos.movimientosAgua();
            case FUEGO -> DatosMovimientos.movimientosFuego();
            case PLANTA -> DatosMovimientos.movimientosPlanta();
            case DRAGON -> DatosMovimientos.movimientosDragon();
            default -> new ArrayList<>();
        };

        Collections.shuffle(tipoPropio);
        resultado[0] = tipoPropio.get(0);
        resultado[1] = tipoPropio.get(1);

        List<Movimiento> estado = DatosMovimientos.movimientosEstado();
        Collections.shuffle(estado);
        resultado[2] = estado.get(0);

        if (rand.nextBoolean()) {
            List<Movimiento> todos = new ArrayList<>();
            todos.addAll(DatosMovimientos.movimientosAgua());
            todos.addAll(DatosMovimientos.movimientosFuego());
            todos.addAll(DatosMovimientos.movimientosPlanta());
            todos.addAll(DatosMovimientos.movimientosDragon());
            todos.addAll(DatosMovimientos.movimientosNormal());


            todos.removeIf(m -> m.getNombre().equals(resultado[0].getNombre()) || m.getNombre().equals(resultado[1].getNombre()) || m.getNombre().equals(resultado[2].getNombre()));
            
            if (!todos.isEmpty()){
                Collections.shuffle(todos);
                resultado[3] = todos.get(0);
            }
            else{
                resultado[3] = null;
            }
        }
        else{
            resultado[3]= null;
        }
        
        return resultado;
    }
}
