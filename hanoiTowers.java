import java.util.Scanner;
import java.util.Stack;

public class hanoiTowers {
    static Scanner sc = new Scanner(System.in);
    static int numDiscos = 3;

    static Stack<Integer>[] torres = new Stack[3]; // 0=A, 1=B, 2=C

    public static void main(String[] args) {
        menuPrincipal();
    }

    static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un numero valido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n===== TORRES DE HANOI =====");
            System.out.println("1. Elegir numero de discos (3-8)");
            System.out.println("2. Jugar manualmente");
            System.out.println("3. Mostrar solucion automatica");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    elegirNumeroDiscos();
                    break;
                case 2:
                    jugarManual();
                    break;
                case 3:
                    mostrarSolucion();
                    break;
                case 4:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 4);
    }

    public static void elegirNumeroDiscos() {
        int n;
        do {
            System.out.print("Ingresa el numero de discos (3-8): ");
            n = leerEntero();
            if (n < 3 || n > 8) {
                System.out.println("Numero invalido. Debe estar entre 3 y 8.");
            }
        } while (n < 3 || n > 8);
        numDiscos = n;
    }

    // Inicializa y muestra torres

    public static void inicializaTorres() {
                // crea las torres, como son 3, se crearan 3 torres
        for (int i = 0; i < 3; i++) {
            torres[i] = new Stack<>();
        }
                // crear pila, empieza contando numDiscos, empieza desde el numero mas grande y acaba en 1, el
                // disco mas grande debe de quedar hasta abajo
        for (int i = numDiscos; i >= 1; i--) {
            torres[0].push(i);
        }
    }

    public static Stack<Integer> obtenerTorre(int torre) {
        return torres[torre];
    }

    public static void despliegaTorre(int torre) {
        Stack<Integer> torreActual = obtenerTorre(torre);
        char letra = (char) ('A' + torre);

        System.out.print("Torre " + letra + ": ");
        for (int disco : torreActual) {
            System.out.print(disco + " ");
        }
        System.out.println();
    }

    public static void despliegaTorres() {
        despliegaTorre(0);
        despliegaTorre(1);
        despliegaTorre(2);
    }

    // Leer la torre, A B O C

    public static int leerTorre(String mensaje) {
        int torre = -1;
        while (torre == -1) {
            System.out.print(mensaje);
            String entrada = sc.next().trim().toUpperCase();

            if (entrada.equals("A")) {
                torre = 0;
            } else if (entrada.equals("B")) {
                torre = 1;
            } else if (entrada.equals("C")) {
                torre = 2;
            } else {
                System.out.println("Torre invalida. Escribe A, B o C.");
            }
        }
        return torre;
    }

    // mover un disco, validando hanoi

    public static boolean moverDisco(int origen, int destino) {
        Stack<Integer> torreOrigen = obtenerTorre(origen);
        Stack<Integer> torreDestino = obtenerTorre(destino);

        if (origen == destino) {
            System.out.println("La torre de origen y destino no pueden ser la misma.");
            return false;
        }

        if (torreOrigen.isEmpty()) {
            System.out.println("La torre de origen esta vacia, no hay disco que mover.");
            return false;
        }

        int discoAMover = torreOrigen.peek();

        if (!torreDestino.isEmpty()) {
            int discoDestino = torreDestino.peek();
            if (discoAMover > discoDestino) {
                System.out.println("Movimiento invalido: no puedes poner un disco grande sobre uno chico.");
                return false;
            }
        }

        // el movimiento es valido: se saca de origen (pop) y se pone en destino (push)
        torreOrigen.pop();
        torreDestino.push(discoAMover);
        return true;
    }

    // Valida si se acaba el juego

    public static boolean juegoTerminado() {
        return torres[2].size() == numDiscos;
    }

    public static void jugarManual() {
        inicializaTorres();
        System.out.println("\nObjetivo: mover todos los discos de la torre A a la torre C.");

        while (!juegoTerminado()) {
            despliegaTorres();
            int origen = leerTorre("Torre de origen (A/B/C): ");
            int destino = leerTorre("Torre de destino (A/B/C): ");
            moverDisco(origen, destino);
        }

        despliegaTorres();
        System.out.println("¡Felicidades, resolviste las Torres de Hanoi!");
    }

    // solucion recursiva

    public static void mostrarSolucion() {
        inicializaTorres();
        System.out.println("\nEstado inicial:");
        despliegaTorres();

        resolverHanoi(numDiscos, 0, 2, 1); // mover de A(0) a C(2) usando B(1)

        System.out.println("\nEstado final:");
        despliegaTorres();
    }

    public static void resolverHanoi(int n, int origen, int destino, int auxiliar) {
        if (n == 0) {
            return; // caso base: no hay disco que mover
        }

        resolverHanoi(n - 1, origen, auxiliar, destino);

        moverDisco(origen, destino);
        System.out.println("Mover disco de " + (char) ('A' + origen) + " a " + (char) ('A' + destino));
        despliegaTorres();

        resolverHanoi(n - 1, auxiliar, destino, origen);
    }
}