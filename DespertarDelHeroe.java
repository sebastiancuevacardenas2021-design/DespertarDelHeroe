import java.util.Scanner;

public class DespertarDelHeroe 
{
        //Bienvenida al juego
        public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("        EL DESPERTAR DEL HÉROE          ");
        System.out.println("=========================================");

        System.out.print("Introduce el nombre de tu héroe: ");
        Scanner scanner = new Scanner(System.in);
        String nombreHeroes = scanner.nextLine();
        
        //Armas disponibles
        Armas espada = new Armas("Espada de Hierro", 15);
        Armas hacha = new Armas("Hacha de Batalla", 25);
        Armas arco = new Armas("Arco Grande", 10);

        //Creación del caballero con espada por defecto
        Caballero heroe = new Caballero(nombreJugador, espada);
        int vidaEnemigo = 60;

        

    }
}

