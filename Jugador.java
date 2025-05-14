import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jugador implements Batalla{
    
    private String nombre;
    private List<Objeto> bolsa;
    private Javaling[] equipo; //= new Javaling[6];
    private Piso pisoActual;


    //Constructor()
    public Jugador(){
        this.equipo = new Javaling[6];
        this.bolsa = new ArrayList<>();
        this.pisoActual = new Piso();
    }

    //Getters y Setters ------------------------------------------------------------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Objeto> getBolsa() {
        return bolsa;
    }
    public void setBolsa(List<Objeto> bolsa) {
        this.bolsa = bolsa;
    }

    public Javaling[] getEquipo() {
        return equipo;
    }
    public void setEquipo(Javaling[] equipo) {
        this.equipo = equipo;
    }

    public Piso getPisoActual() {
        return pisoActual;
    }
    public void setPisoActual(Piso pisoActual) {
        this.pisoActual = pisoActual;
    }
    //-----------------------------------------------------------------------------------


    //Interface
    public Javaling elegirJavalingActivo(){

        Javaling javaling = new Agua(nombre);

        return javaling;
    }


    public void printEquipo(){
        String[] lista = new String[6];
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] == null) {
                lista[i] = "Vacío";
            }
            else{
                lista[i] = equipo[i].getNombre();
            }
        }
        System.out.println(Arrays.toString(lista));
    }

    public void usarObjeto(Objeto item){

    }

}
