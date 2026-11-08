import java.util.Scanner;

public class DespertarDelHeroe {
    //Bienvenida al juego
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
                    int[] pociones = crearInventario();
                    int cantidad = Caballero.NUMERO_POCIONES;
                    mostrarInventario(pociones, cantidad);
                    cantidad = retirarPocion(pociones, cantidad);
                    mostrarInventario(pociones, cantidad);
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

    // Nombre del heroe
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

    // Menu principal
    static void mostrarMenu() {
        System.out.println("");
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1. Nueva partida");
        System.out.println("2. Consultar ranking");
        System.out.println("3. Ayuda");
        System.out.println("4. Salir");
    }

    // Lee el numero que pongas, si esta fuera de rango te lo dice 
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

    // Devuelve un numero al azar entre minimo y maximo
    static int calcularDano(int minimo, int maximo) {
        return (int) (Math.random() * (maximo - minimo + 1)) + minimo;
    }
    //Inventario 
    static int[] crearInventario() {
        int[] pociones = new int[Caballero.NUMERO_POCIONES];
        for (int i = 0; i < pociones.length; i++) {
            pociones[i] = Caballero.CURACION_POCION;
        }
        return pociones;
    }

    //Muestra solo las pociones que tienes
    static void mostrarInventario(int[] pociones, int cantidad) {
        System.out.println("---INVENTARIO---");
        if (cantidad == 0) {
            System.out.println("No te quedan pociones");
        }
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Pocion " + (i + 1) + ": cura " + pociones[i] + " de vida");
        }
    }
    
   //Retira una pocion y devuelve cuantas quedan 
   static int retirarPocion(int[] pociones, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("No tienes pociones.");
            return 0;
        }
        pociones[cantidad - 1] = 0;
        return cantidad - 1;
    }
}