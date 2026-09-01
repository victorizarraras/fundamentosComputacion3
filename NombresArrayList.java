import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NombresArrayList {
    public static void main(String[] args) {
        ArrayList<String> personalities = new ArrayList<>();
        personalities.add("Ada Lovelace");
        personalities.add("Alan turing");
        personalities.add("Grace hopper");

        // --- Leer nombres desde el archivo de texto ---
        Path rutaArchivo = Path.of("listado.txt");

        try {
            List<String> lineas = Files.readAllLines(rutaArchivo);
            personalities.addAll(lineas);
        } catch (IOException e) {
            System.out.println("No se encontró el archivo: " + e.getMessage());
        }

        String name = personalities.get(1);
        System.out.println(name);
        System.out.println("The size of the arraylist is: " + personalities.size());

        personalities.size();
        personalities.remove(0);
        personalities.set(1, "Margaret Hamilton");
        System.out.println(personalities);
    }
}