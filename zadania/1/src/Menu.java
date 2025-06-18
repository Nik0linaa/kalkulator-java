import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;
import java.util.Scanner;

public class Menu {
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

        List<Nagłówki> bombki;
        try {
            bombki = Dane.wczytaj(sciezka);
        } catch (Exception e) {
            System.out.println("Błąd przy wczytywaniu pliku: " + e.getMessage());
            return;
        }

        if (bombki == null || bombki.isEmpty()) {
            System.out.println("Brak danych w pliku lub plik nie został wczytany!");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Ile bombek o rozmiarze X?");
            System.out.println("2. Ilość zamówionych sztuk bombek o rozmiarze X?");
            System.out.println("3. Jaka jest wartość całkowita zamówienia?");
            System.out.println("4. Wyjście");

            int wybor;
            System.out.print("Wybierz opcję: ");
            try {
                wybor = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź liczbę!");
                continue;
            }

            switch (wybor) {
                case 1 -> {
                    System.out.print("Podaj rozmiar: ");
                    String r = scanner.nextLine().trim();
                    if (r.isEmpty()) {
                        System.out.println("Nie podano rozmiaru!");
                        continue;
                    }
                    long ile = bombki.stream().filter(b -> b.getRozmiar().equals(r)).count();
                    if (ile == 0) {
                        System.out.println("Brak bombek o rozmiarze: " + r);
                    } else {
                        System.out.println("Liczba bombek o rozmiarze " + r + ": " + ile);
                    }
                }
                case 2 -> {
                    System.out.print("Podaj rozmiar: ");
                    String r = scanner.nextLine().trim();
                    if (r.isEmpty()) {
                        System.out.println("Nie podano rozmiaru!");
                        continue;
                    }
                    int suma = bombki.stream()
                            .filter(b -> b.getRozmiar().equals(r))
                            .mapToInt(b -> b.zamowionaIloscOpakowan * b.sztukiWOpakowaniu)
                            .sum();
                    if (suma == 0) {
                        System.out.println("Brak danych o bombkach tego rozmiaru.");
                    } else {
                        System.out.println("Zamówiono sztuk o rozmiarze " + r + ": " + suma);
                    }
                }
                case 3 -> {
                    double wartosc = bombki.stream()
                            .mapToDouble(Nagłówki::wartosc)
                            .sum();
                    System.out.printf("Wartość całkowita zamówienia: %.2f €\n", wartosc);
                }
                case 4 -> {
                    System.out.println("Koniec.");
                    return;
                }
                default -> System.out.println("Nie ma takiej opcji.");
            }
        }
    }
}
