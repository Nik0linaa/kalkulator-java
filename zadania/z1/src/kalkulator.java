import java.util.Scanner;

public class kalkulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Kalkulator produkcji bombek ===");
            System.out.println("1. Oblicz maksymalną produkcję bombek");
            System.out.println("2. Wyjście");
            System.out.print("Wybierz opcję: ");

            String wybor = scanner.nextLine();

            if (wybor.equals("2")) {
                System.out.println("koniec!");
                break;
            } else if (wybor.equals("1")) {
                liczBombki(scanner);
            } else {
                System.out.println("Nieznana opcja, spróbuj jeszcze raz.");
            }
        }
    }

    private static void liczBombki(Scanner scanner) {
        System.out.print("\nPodaj ilość szkła (g): ");
        int szklo = pobierzDodatniaLiczbe(scanner);

        String kolor;
        while (true) {
            System.out.print("Kolor farby: ");
            kolor = scanner.nextLine();
            if (kolor.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\- ]+")) {
                break;
            }
            System.out.println("Kolor farby nie może zawierać cyfr ani znaków specjalnych. Spróbuj jeszcze raz.");
        }

        System.out.print("Podaj ilość farby (ml): ");
        int farba = pobierzDodatniaLiczbe(scanner);

        System.out.print("Podaj ilość brokatu (g): ");
        int brokat = pobierzDodatniaLiczbe(scanner);

        System.out.print("Podaj ilość zawieszek (szt.): ");
        int zawieszki = pobierzDodatniaLiczbe(scanner);

        int klasyczna = Math.min(szklo / 100, Math.min(farba / 10, zawieszki));
        int malowana = Math.min(szklo / 100, Math.min(farba / 20, zawieszki));
        int brokatowa = Math.min(Math.min(szklo / 100, farba / 10), Math.min(brokat / 5, zawieszki));

        System.out.println("\n--- Maksymalna produkcja bombek ---");
        System.out.printf("Klasyczna   : %d szt.\n", klasyczna);
        System.out.printf("Malowana    : %d szt.\n", malowana);
        System.out.printf("Z brokatem  : %d szt.\n", brokatowa);
        System.out.println("\nKolor farby: " + kolor);
        System.out.println("Produkcja obliczona na podstawie podanych zasobów.");
    }

    private static int pobierzDodatniaLiczbe(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                int liczba = Integer.parseInt(input);
                if (liczba <= 0) {
                    System.out.print("To musi być liczba dodatnia! Spróbuj jeszcze raz: ");
                    continue;
                }
                return liczba;
            } catch (NumberFormatException e) {
                System.out.print("To nie jest poprawna liczba! Spróbuj jeszcze raz: ");
            }
        }
    }
}
