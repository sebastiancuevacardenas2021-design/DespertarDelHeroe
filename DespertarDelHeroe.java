import java.util.Scanner;

public class DespertarDelHeroe 
{
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("        EL DESPERTAR DEL HÉROE          ");
        System.out.println("=========================================");
        System.out.println("");
        
        System.out.println("Vida máxima del caballero: " + Caballero.VIDA_MAXIMA);
        System.out.println("");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre de tu héroe: ");
        String nombreHeroe = scanner.nextLine();

        Armas armaInicial = new Armas("Espada", 5);
        Caballero heroe = new Caballero(nombreHeroe, armaInicial);

        System.out.println("¡Héroe " + heroe.nombre + " creado con éxito!");
    }
}
