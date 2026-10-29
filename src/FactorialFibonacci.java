public class FactorialFibonacci {

    public static void main(String[] args) {
        int[] valoresFactorial = {5, 10, 15, 20, 32};
        int[] valoresFibonacci = {50};

        long inicioTotal = System.nanoTime();

        // factorial
        System.out.println("===== FACTORIAL =====");
        for (int i = 0; i < valoresFactorial.length; i++) {
            int n = valoresFactorial[i];

            long inicio = System.nanoTime();
            long resultado = factorialIterativo(n);
            long fin = System.nanoTime();
            imprimir("Factorial iterativo", n, resultado, inicio, fin);

            inicio = System.nanoTime();
            resultado = factorialRecursivo(n);
            fin = System.nanoTime();
            imprimir("Factorial recursivo", n, resultado, inicio, fin);

            if (n > 20) {
                System.out.println("  OJO: con n > 20 el resultado se desborda (overflow) y es incorrecto.");
            }
        }

        // fibonacci
        System.out.println("\n===== FIBONACCI =====");
        for (int i = 0; i < valoresFibonacci.length; i++) {
            int n = valoresFibonacci[i];

            // iterativo ---
            long inicio = System.nanoTime();
            long resultado = fibonacciIterativo(n);
            long fin = System.nanoTime();
            imprimir("Fibonacci iterativo", n, resultado, inicio, fin);

            // recursivo ---
            inicio = System.nanoTime();
            resultado = fibonacciRecursivo(n);
            fin = System.nanoTime();
            imprimir("Fibonacci recursivo", n, resultado, inicio, fin);
        }

        long finTotal = System.nanoTime();
        double msTotal = (finTotal - inicioTotal) / 1_000_000.0;
        System.out.printf("%nTiempo total: %.4f ms%n", msTotal);
    }

    public static void imprimir(String nombre, int n, long resultado, long inicio, long fin) {
        double ms = (fin - inicio) / 1_000_000.0;
        System.out.printf("%s(%d) = %d  ->  %.4f ms%n", nombre, n, resultado, ms);
    }

    // Factorial ---

    public static long factorialIterativo(int n) {
        long resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado = resultado * i;
        }
        return resultado;
    }

    public static long factorialRecursivo(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorialRecursivo(n - 1);
    }

    // Fibonacci

    public static long fibonacciIterativo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long anterior = 0;
        long actual = 1;

        for (int i = 2; i <= n; i++) {
            long siguiente = anterior + actual;
            anterior = actual;
            actual = siguiente;
        }
        return actual;
    }

    public static long fibonacciRecursivo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursivo(n - 1) + fibonacciRecursivo(n - 2);
    }
}