import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Csv {
    public static List<Dane> wczytaj(String sciezka) {
        List<Dane> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(sciezka))) {
            br.readLine();
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] pola = linia.split(";");
                if (pola.length >= 4) {
                    try {
                        String miesiac = pola[0].trim();
                        int ilosc = Integer.parseInt(pola[1].trim());
                        String typ = pola[2].trim();
                        String klient = pola[3].trim();

                        lista.add(new Dane(miesiac, ilosc, typ, klient));
                    } catch (NumberFormatException e) {
                        System.err.println("Błędne dane liczbowe: " + linia);
                    }
                } else {
                    System.err.println("Za mało danych w linii: " + linia);
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd przy wczytywaniu CSV: " + e.getMessage());
        }
        return lista;
    }
}
