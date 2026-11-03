import java.util.Scanner;

public class DespertarDelHeroe 
{
        //Bienvenida al juego
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("        EL DESPERTAR DEL HÉROE          ");
        System.out.println("=========================================");

        System.out.print("Introduce el nombre de tu héroe: ");
        String nombreHeroes = scanner.nextLine().trim();

        System.out.println("¡" + nombreHeroes + "se ha despertado y comienza su aventura!");
        
        //Armas disponibles
        Armas espada = new Armas("Espada de Hierro", 15);
        Armas hacha = new Armas("Hacha de Batalla", 25);
        Armas arco = new Armas("Arco Grande", 10);
        
        //Inventario
        System.out.println("---INVENTARIO---");
        System.out.println("Elije tu arma para comenzar:");
        System.out.println("1. Espada de Hierro (Dano extra: 15)");
        System.out.println("2. Hacha de Batalla (Dano extra: 25)");
        System.out.println("3. Arco Grande (Dano extra: 10)");
        System.out.println("Selecciona una pocion(1-3): ");
        int opcionArma = scanner.nextInt();


       
       
       
       
       
       
       
        //Creación del caballero con espada por defecto
        Caballero heroe = new Caballero(nombreHeroes, espada);
        int vidaEnemigo = 60;

        

    }
}

