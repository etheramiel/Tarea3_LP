public class efectividadElementos {
    public static double getEfectividad(Tipo ataque, Tipo defensa){
        return switch (ataque) {
            case AGUA -> switch (defensa) {
                case FUEGO -> 2.0;
                case PLANTA, DRAGON -> 0.5;
                default -> 1.0;
            };
            case FUEGO -> switch (defensa) {
                case PLANTA -> 2.0;
                case AGUA, DRAGON-> 0.5;
                default -> 1.0;
            };
            case PLANTA -> switch (defensa) {
                case AGUA -> 2.0;
                case FUEGO, DRAGON -> 0.5;
                default -> 1.0;
            };
            case DRAGON -> switch (defensa) {
                case DRAGON -> 2.0;
                default -> 1.0;
            };
            default -> 1.0;
        };
    }
}
