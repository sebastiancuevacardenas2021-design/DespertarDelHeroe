import java.util.Scanner;

public class Main 
{   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Armas
        Armas espada = new Armas("Espada de Hierro", 10);
        Armas hacha = new Armas("Hacha de batalla", 25);
        Armas arco = new Armas("Arco grande", 10);

       //Crear al héroe con un arma inicial
       Caballero heroe = new Caballero(nombreHeroe, espada);
       int vidaEnemigo = 60; // Vida del monstruo a vencer

       System.out.println("!Un monstruo aparece frende a " + heroe.nombre + "!");
   
   //
   
   
    }

}
