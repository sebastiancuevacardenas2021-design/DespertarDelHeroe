import java.util.Scanner;

public class DespertarDelHeroe {

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("       EL DESPERTAR DEL HEROE");
        System.out.println("=====================================");

        Scanner teclado = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(teclado, 1, 4);
            switch (opcion) {
                case 1:
                    System.out.println("Dano de prueba: " + calcularDano(Caballero.DANO_MINIMO, Caballero.DANO_MAXIMO));
                    System.out.println("Bienvenido, " + pedirNombre(teclado) + ".");
                    break;
                case 2:
                    System.out.println("(El ranking se hara mas adelante)");
                    break;
                case 3:
                    System.out.println("(La ayuda se hara mas adelante)");
                    break;
                case 4:
                    System.out.println("Hasta pronto.");
                    break;
            }
        } while (opcion != 4);
        teclado.close();
    }

    // Pide el nombre del heroe y no termina hasta que no este vacio.
    static String pedirNombre(Scanner teclado) {
        String nombre = "";
        while (nombre.isEmpty()) {
            System.out.print("Nombre del heroe: ");
            nombre = teclado.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacio.");
            }
        }
        return nombre;
    }

    // Muestra las opciones del menu principal.
    static void mostrarMenu() {
        System.out.println("");
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1. Nueva partida");
        System.out.println("2. Consultar ranking");
        System.out.println("3. Ayuda");
        System.out.println("4. Salir");
    }

    // Lee un numero entre minimo y maximo. Repite hasta que sea valido.
    static int leerOpcion(Scanner teclado, int minimo, int maximo) {
        int opcion = 0;
        boolean valida = false;
        while (!valida) {
            System.out.print("Elige una opcion (" + minimo + "-" + maximo + "): ");
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                if (opcion >= minimo && opcion <= maximo) {
                    valida = true;
                } else {
                    System.out.println("Opcion fuera de rango.");
                }
            } else {
                System.out.println("Debes escribir un numero.");
            }
            teclado.nextLine();
        }
        return opcion;
    }

    // Devuelve un numero al azar entre minimo y maximo (ambos incluidos).
    static int calcularDano(int minimo, int maximo) {
        return (int) (Math.random() * (maximo - minimo + 1)) + minimo;
    }
}