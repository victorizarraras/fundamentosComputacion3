import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NombresArrayList {
    public static void main(String[] args) {
        ArrayList<String> personalities = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Integer> cantidadNombres = new ArrayList<>();

        personalities.add("Ada Lovelace");
        personalities.add("Alan turing");
        personalities.add("Grace hopper");

        // Leer nombre
        Path rutaArchivo = Path.of("listado.txt");

        try {
            List<String> lineas = Files.readAllLines(rutaArchivo);
            personalities.addAll(lineas);
        } catch (IOException e) {
            System.out.println("No se encontró el archivo: " + e.getMessage());
        }

        for (int i = 0; i < personalities.size(); i++) {
            // extraer solo la primera palabra del nombre completo
            String[] partes = personalities.get(i).split(" ");
            String primerNombre = partes[0];

            // ya se vio el primer nombre antes?
            int posicion = nombres.indexOf(primerNombre);

            if (posicion == -1) {
                // Si no existe se agrega
                nombres.add(primerNombre);
                cantidadNombres.add(1);
            } else {
                // Si ya existe se incrementa contador
                int cantidadActual = cantidadNombres.get(posicion);
                cantidadNombres.set(posicion, cantidadActual + 1);
            }
        }

        for (int i = 0; i < nombres.size(); i++) {
            System.out.println(nombres.get(i) + ": " + cantidadNombres.get(i));
        }
    }
}