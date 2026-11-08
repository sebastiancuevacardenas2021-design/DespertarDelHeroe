import java.util.Scanner;

public class DespertarDelHeroe 
{
        static final int MAX_RANKING = 10;
        static final int PUNTOS_POR_ENEMIGO = 100;
    //Bienvenida al juego
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("       EL DESPERTAR DEL HEROE");
        System.out.println("=====================================");

        Scanner teclado = new Scanner(System.in);
        String[] rankingNombres = new String[MAX_RANKING];
        int[] rankingPuntos = new int[MAX_RANKING];
        int rankingCantidad = 0;
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(teclado, 1, 4);
            switch (opcion) {
                case 1:
                    String nombreHeroe = pedirNombre(teclado);
                    System.out.println("Bienvenido, " + nombreHeroe + ".");
                    int[] pociones = crearInventario();
                    int[] heroe = {Caballero.VIDA_MAXIMA, Caballero.NUMERO_POCIONES};
                    String[] nombre = {"Goblin", "Orco", "Rey Demonio"};
                    int[] vidas = {Enemigos.GOBLIN_VIDA, Enemigos.ORCO_VIDA, Enemigos.REY_DEMONIO_VIDA};
                    int[] danosMin = {Enemigos.GOBLIN_DANO_MINIMO, Enemigos.ORCO_DANO_MINMO, Enemigos.REY_DEMONIO_MINIMO};
                    int[] danoMax = {Enemigos.GOBLIN_DANO_MAXIMO, Enemigos.ORCO_DANO_MAXIMO, Enemigos.REY_DEMONIO_MAXIMO};
                    
                    int derrotados = 0;
                    int resultado = 1;
                    for (int i = 0; i < nombre.length && resultado == 1; i++) {
                        resultado = simularCombate(teclado, heroe, pociones, nombre[i], vidas[i], danosMin[i], danoMax[i]);
                        if (resultado == 1) {
                            derrotados++;
                        }
                    }

                    int puntos = derrotados * PUNTOS_POR_ENEMIGO;
                    if (derrotados == nombre.length) {
                       puntos += heroe[0];
                       System.out.println("VICTORIA! HAS COMPLETADO LA AVENTURA.");
                    } else if (resultado == 0) {
                       System.out.println("DERROTA");
                    }
                    System.out.println("Puntos: " + puntos);
                    rankingCantidad = registrarPuntuacion(rankingNombres, rankingPuntos, rankingCantidad, nombreHeroe, puntos);
                    break;                
                case 2:
                    mostrarRanking(rankingNombres, rankingPuntos, rankingCantidad);
                    break;
                case 3:
                    mostrarAyuda();;
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
    //Las reglas
    static void mostrarAyuda() {
        System.out.println("");
        System.out.println("==== AYUDA ====");
        System.out.println("REGLAS");
        System.out.println("-Debes vencer a 3 enemigos seguidos: Goblin, Orco y Rey Demonio.");
        System.out.println("Empiezas con " + Caballero.DANO_MINIMO + " y" + Caballero.DANO_MAXIMO + " de dano.");  
        System.out.println("ACCIONES EN EL COMBATE");
        System.out.println("- Atacar: Golpeas al nemigo y el responde.");
        System.out.println("- Usar pocion: recuperas " + Caballero.CURACION_POCION + "de vida (maximo " + Caballero.VIDA_MAXIMA + ").");
        System.out.println("- Abandonar: terminas la partida.");
        System.out.println("PUNTACION;");
        System.out.println("- 100 puntos por cada enemigo derrotado.");
        System.out.println("- Si ganas la aventura, se suma de tu vida restante.");
    }
    static int simularCombate(Scanner teclado, int[] heroe, int[] pociones,
                              String nombreEnemigo, int vidaEnemigo, int danoMin, int danoMax) {
        System.out.println("");
        System.out.println("Aparece un " + nombreEnemigo + "! (vida: " + vidaEnemigo + ")");

        while (heroe[0] > 0 && vidaEnemigo > 0) {
            System.out.println("");
            System.out.println("Tu vida: " + heroe[0] + " | " + nombreEnemigo + ":" + vidaEnemigo);
            System.out.println("1. Atacar 2.Usar pocion 3. Ver inventario 4. Abandonar");
            int opcion = leerOpcion(teclado, 1,4);
            boolean turnoValido = false;

            switch (opcion) {
                case 1:
                    int dano = calcularDano(Caballero.DANO_MINIMO, Caballero.DANO_MAXIMO);
                    vidaEnemigo -= dano;
                    System.out.println("Golpeas al " + nombreEnemigo + " y le haces " + dano + " de dano.");
                    turnoValido = true;
                    break;
                case 2:
                    if (heroe[1] == 0) {
                    System.out.println("No tienes pociones.");
                    } else if (heroe[0] == Caballero.VIDA_MAXIMA) {
                    System.out.println("Tu vida ya esta completa.");
                    } else {
                    heroe[1] = retirarPocion(pociones, heroe[1]);
                    heroe[0] = Math.min(Caballero.VIDA_MAXIMA, heroe[0] + Caballero.CURACION_POCION);
                    System.out.println("Usas una pocion. Vida: " + heroe[0]);
                    turnoValido = true;
                    }
                    break;
                case 3:
                    mostrarInventario(pociones, heroe[1]);
                    break;
                case 4:
                    System.out.println("Abandonas la partida.");
                    return -1;                      
            }
            //Ataque del enemigo
            if (turnoValido && vidaEnemigo > 0) {
               int danoEnemigo = calcularDano(danoMin, danoMax);
               heroe[0] -= danoEnemigo;
               if (heroe[0] < 0) {
                   heroe[0] = 0;
               }
               System.out.println("El " + nombreEnemigo + " te hace " + danoEnemigo + " de damo.");
            }
        }
        if (heroe[0] > 0) {
            System.out.println("Has derrotado al " + nombreEnemigo + "!");
            return 1;
        }
        System.out.println("Has caido en combate...");
        return 0;
    }
    //Ranking del orden de mayor a menor
    static int registrarPuntuacion(String[] nombres, int[] puntos, int cantidad, String nombre, int puntuacion) {
        int posicion = cantidad;
        for (int i = 0; i < cantidad; i++) {
            if (puntuacion > puntos[i]) {
                posicion = i;
                break;
            }
        }
        if (posicion >= MAX_RANKING) {
            System.out.println("Tu puntacion no entra en el ranking.");
            return cantidad;
        }
        int nuevaCantidad = Math.min(cantidad + 1, MAX_RANKING);
        for (int i = nuevaCantidad - 1; i > posicion; i--) {
            nombres[i] = nombres[i - 1];
            puntos[i] = puntos[i - 1];
        }
        nombres[posicion] = nombre;
        puntos[posicion] = puntuacion;
        return nuevaCantidad;
    }

    //Muestra el reaking, si esta vacio te avisara 
       static void mostrarRanking(String[] nombres, int[] puntos, int cantidad) {
        System.out.println("");
        System.out.println("==== REAKING ====");
        if (cantidad == 0) {
            System.out.println("Aun no hay puntaciones.");
        }
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + nombres[i] + " - " + puntos[i] + " puntos");
        }
    
    }

}
