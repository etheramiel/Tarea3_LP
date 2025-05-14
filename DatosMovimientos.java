import java.util.ArrayList;
import java.util.List;

public class DatosMovimientos {

    public static List<Movimiento> movimientosAgua(){
        List<Movimiento> lista = new ArrayList<>();
        lista.add(new Movimiento("Impulso Acuatico", 60, 100, false, Tipo.AGUA, "Aumenta Velocidad en 1.5 por el resto del combate"));
        lista.add(new Movimiento("Marea Viva", 75, 100, false, Tipo.AGUA, "Potencia*1.15 Cada vez que se usa este ataque de forma consecutiva."));
        lista.add(new Movimiento("Corriente Maritima", 100, 100, false, Tipo.AGUA, "20% Reducir la velocidad del oponente"));
        lista.add(new Movimiento("Hidrobomba", 120, 90, false, Tipo.AGUA, "-"));
        lista.add(new Movimiento("Refuerzo Acuatico", 0, 100, true, Tipo.AGUA, "Cura un 30% de salud, 60% si oleaje fue activado este turno (fue atacado durante el turno anterior)"));
        return lista;
    }

    public static List<Movimiento> movimientosFuego(){
        List<Movimiento> lista = new ArrayList<>();
        lista.add(new Movimiento("Brasa Ígnea ", 50, 100, false, Tipo.FUEGO, "Potencia*1.15 a todos sus movimientos por el resto del combate"));
        lista.add(new Movimiento("Rueda Fuego",80, 100, false, Tipo.FUEGO, "Aumenta Velocidad en 1.2 por el resto del combate"));
        lista.add(new Movimiento("Disparo Magma", 100, 100, false, Tipo.FUEGO, "-"));
        lista.add(new Movimiento("Erupción", 150,90, false, Tipo.FUEGO, "Solo 150 potencia al 100% de vida, potencia se calcula a partir del hpActual"));
        lista.add(new Movimiento("Avivar", 0, 100, true, Tipo.FUEGO, "Autoinflige 5% hpActual, multiplica el daño en 1.15 y sube la velocidad en 1.15 por el resto del combate"));
        return lista;
    }

    public static List<Movimiento> movimientosPlanta(){
        List<Movimiento> lista = new ArrayList<>();
        lista.add(new Movimiento("Hoja Navaja ", 60, 100, false, Tipo.PLANTA, "20% de bajar Precisión del rival en 15 puntos"));
        lista.add(new Movimiento("Bomba Espora", 75, 100, false, Tipo.PLANTA, "Bomba espora"));
        lista.add(new Movimiento("Asta Drenaje", 90, 95, false, Tipo.PLANTA, "El usuario cura 50% del daño infligido"));
        lista.add(new Movimiento("Tormenta Pétalo", 120, 90, false, Tipo.PLANTA, "-"));
        lista.add(new Movimiento("Crecimiento", 0, 100, true, Tipo.PLANTA, "Aumenta hpTOTAL y hpActual 1.2% hasta finalizar el combate"));
        return lista;
    }

    public static List<Movimiento> movimientosDragon(){
        List<Movimiento> lista = new ArrayList<>();
        lista.add(new Movimiento("Mordida Dracónica", 70, 100, false, Tipo.DRAGON, "30% de hacer retroceder al rival (rival no puede atacar sgte. turno)"));
        lista.add(new Movimiento("Garra Dragón", 80, 100, false, Tipo.DRAGON, "30% de subir potencia y velocidad por 1.2"));
        lista.add(new Movimiento("Pulso Dragón", 100, 100, false, Tipo.DRAGON, "20% de bajar Precision del rival en 10 puntos"));
        lista.add(new Movimiento("Draco meteoro", 120, 80, false, Tipo.DRAGON, "-"));
        lista.add(new Movimiento("Danza Dragón", 0, 100, true, Tipo.DRAGON, "Aumenta potencias y Velocidad del usuario en 1.5"));
        return lista;
    }

    public static List<Movimiento> movimientosNormal(){
        List<Movimiento> lista = new ArrayList<>();
        lista.add(new Movimiento("Protección", 0, 100, true, Tipo.NORMAL, "Siempre va primero, anula cualquier daño, falla si se usa más de una vez consecutiva"));
        lista.add(new Movimiento("Rugido", 0, 100, true, Tipo.NORMAL, "Obliga al rival a cambiar de Javaling, si solo tiene 1, reduce la velocidad rival en un 20%"));
        return lista;
    }

    public static List<Movimiento> movimientosEstado(){
        List<Movimiento> lista = new ArrayList<>();
        for(Movimiento m : movimientosAgua()){
            if(m.getEstado()){
                lista.add(m);
            }
        }
        for(Movimiento m : movimientosFuego()){
            if(m.getEstado()){
                lista.add(m);
            }
        }
        for(Movimiento m : movimientosPlanta()){
            if(m.getEstado()){
                lista.add(m);
            }
        }
        for(Movimiento m : movimientosDragon()){
            if(m.getEstado()){
                lista.add(m);
            }
        }
        for(Movimiento m : movimientosNormal()){
            if(m.getEstado()){
                lista.add(m);
            }
        }
        return lista;
    }

}