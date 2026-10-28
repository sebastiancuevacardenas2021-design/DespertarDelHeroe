public class Caballero 
{   // Atributos
    static final int VIDA_MAXIMA = 100;
    static final int NUMERO_POCIONES = 3;
    static final int CURACION_POCION = 25;
    static final int DANO_MINIMO = 10;
    static final int DANO_MAXIMO = 20;

    // Atributos del jugador
    String nombre;
    int vida;
    Armas arma;

    // Constructor
    public Caballero(String nombre, Armas arma) {
        this.nombre = nombre;
        this.arma = arma;
        this.vida = VIDA_MAXIMA; // Empieza con la vida al máximo
    }
}
