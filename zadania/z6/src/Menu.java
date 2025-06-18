import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import java.io.*;

public class Menu {
    private static final Set<String> POPRAWNE_MIESIACE = Set.of(
            "styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec",
            "lipiec", "sierpień", "wrzesień", "październik", "listopad", "grudzień"
    );
    private static List<Dane> bombki;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz plik CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Pliki CSV", "csv"));

        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("Nie wybrano pliku, kończę program.");
            return;
        }

        String sciezka = fileChooser.getSelectedFile().getAbsolutePath();

        try {
            bombki = Csv.wczytaj(sciezka);
        } catch (Exception e) {
            System.out.println("Błąd przy wczytywaniu pliku: " + e.getMessage());
            return;
        }

        if (bombki == null || bombki.isEmpty()) {
            System.out.println("Brak danych w pliku lub plik nie został wczytany!");
            return;
        }

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Ilość sprzedanych sztuk w danym miesiącu");
            System.out.println("2. Najpopularniejszy model");
            System.out.println("3. Najlepszy miesiąc");
            System.out.println("4. Wyjście");

            System.out.print("Wybierz opcję: ");
            int wybor;
            try {
                wybor = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź poprawną liczbę!");
                continue;
            }

            switch (wybor) {
                case 1:
                    System.out.print("Podaj miesiąc: ");
                    System.out.print("Podaj miesiąc (np. styczeń): ");
                    String miesiac = scanner.nextLine().trim().toLowerCase();

                    if (!POPRAWNE_MIESIACE.contains(miesiac)) {
                        System.out.println("To nie jest miesiąc.");
                        break;
                    }
                    iloscSprzedanychSztuk(miesiac);

                    iloscSprzedanychSztuk(miesiac);
                    break;
                case 2:
                    najpopularniejszyModel();
                    break;
                case 3:
                    najlepszyMiesiac();
                    break;
                case 4:
                    System.out.println("Koniec.");
                    return;
                default:
                    System.out.println("Nie ma takiej opcji.");
            }
        }
    }

    private static void iloscSprzedanychSztuk(String miesiac) {
        int suma = 0;
        for (Dane d : bombki) {
            if (d.getMiesiac().toLowerCase().equals(miesiac)) {
                suma += d.getIlosc();
            }
        }
        System.out.println("Ilość sprzedanych sztuk w miesiącu " + miesiac + ": " + suma);
    }

    private static void najpopularniejszyModel() {
        Map<String, Integer> mapaModeli = new HashMap<>();
        for (Dane d : bombki) {
            mapaModeli.put(d.getTyp(), mapaModeli.getOrDefault(d.getTyp(), 0) + d.getIlosc());
        }
        String najpopularniejszy = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : mapaModeli.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                najpopularniejszy = entry.getKey();
            }
        }
        System.out.println("Najpopularniejszy model: " + (najpopularniejszy != null ? najpopularniejszy + " (" + max + " szt.)" : "Brak danych"));
    }

    private static void najlepszyMiesiac() {
        Map<String, Integer> mapaMiesiecy = new HashMap<>();
        for (Dane d : bombki) {
            mapaMiesiecy.put(d.getMiesiac(), mapaMiesiecy.getOrDefault(d.getMiesiac(), 0) + d.getIlosc());
        }
        String najlepszy = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : mapaMiesiecy.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                najlepszy = entry.getKey();
            }
        }
        System.out.println("Najlepszy miesiąc pod względem sprzedaży: " + (najlepszy != null ? najlepszy + " (" + max + " szt.)" : "Brak danych"));
    }
}
