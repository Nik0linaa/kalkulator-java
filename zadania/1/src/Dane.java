import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dane {
    public static List<Nagłówki> wczytaj(String sciezka) {
        List<Nagłówki> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(sciezka))) {
            br.readLine();
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] pola = linia.split(";");
                if (pola.length >= 15) {
                    try {
                        lista.add(new Nagłówki(pola));
                    } catch (NumberFormatException e) {
                        System.err.println("Błędne dane w linii: " + linia);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd przy wczytywaniu CSV: " + e.getMessage());
        }
        return lista;
    }
}