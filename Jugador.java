import java.util.List;

public class Jugador implements Batalla{
    
    private String nombre;
    private List<Objeto> bolsa;
    private Javaling[] equipo = new Javaling[6];
    private Piso pisoActual;


    public Javaling elegirJavalingActivo(){

        Javaling javaling = new Agua();

        return javaling;
    }

    public void usarObjeto(Objeto item){

    }

}
