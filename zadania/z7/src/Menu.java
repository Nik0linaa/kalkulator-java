import java.util.*;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static Map<String, Map<String, Integer>> klienci = new HashMap<>();
    private static int licznik = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Dodaj klienta i kody bombek");
            System.out.println("2. Usuń klienta");
            System.out.println("3. Pokaż klientów i ich kody");
            System.out.println("4. Wyjście");
            System.out.print("Wybierz opcję: ");

            int wybor;
            try {
                wybor = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź poprawną liczbę!");
                continue;
            }

            switch (wybor) {
                case 1:
                    dodajKlientaIZamowienia();
                    break;
                case 2:
                    usunKlienta();
                    break;
                case 3:
                    pokazKlientow();
                    break;
                case 4:
                    System.out.println("Koniec programu.");
                    return;
                default:
                    System.out.println("Nieznana opcja.");
            }
        }
    }

    private static String generujKod(String kolor, int rozmiar) {
        return String.format("BOM-%s-%d-%d-%05d",
                kolor.toUpperCase(), rozmiar,
                Calendar.getInstance().get(Calendar.YEAR), licznik++);
    }

    private static void dodajKlientaIZamowienia() {
        String imie;
        while (true) {
            System.out.print("Imię: ");
            imie = sc.nextLine();
            if (imie.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\- ]+")) break;
            System.out.println("Imię nie może zawierać cyfr ani znaków specjalnych.");
        }
        if (imie.isEmpty()) {
            System.out.println("Imię nie może być puste.");
            return;
        }

        if (!klienci.containsKey(imie)) {
            klienci.put(imie, new HashMap<>());
            System.out.println("Dodano klienta: " + imie);
        } else {
            System.out.println("Klient już istnieje, dodajemy kolejne bombki.");
        }

        Map<String, Integer> zamowienia = klienci.get(imie);

        while (true) {
            String kolor;
            while (true) {
                System.out.print("Podaj kolor bombki (lub wpisz 'koniec' aby zakończyć): ");
                kolor = sc.nextLine();
                if (kolor.equalsIgnoreCase("koniec")) {
                    return;
                }
                if (kolor.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\- ]+")) break;
                System.out.println("Kolor nie może zawierać cyfr ani znaków specjalnych.");
            }
            int rozmiar;
            while (true) {
                System.out.print("Podaj rozmiar bombki (liczba w cm): ");
                String input = sc.nextLine();
                try {
                    rozmiar = Integer.parseInt(input);
                    if (rozmiar <= 0) {
                        System.out.println("Rozmiar musi być dodatnią liczbą!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("To nie jest poprawna liczba! Spróbuj jeszcze raz.");
                }
            }
            int ilosc;
            while (true) {
                System.out.print("Podaj ilość bombek dla tego kodu: ");
                String input = sc.nextLine();
                try {
                    ilosc = Integer.parseInt(input);
                    if (ilosc <= 0) {
                        System.out.println("Ilość musi być dodatnią liczbą!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("To nie jest poprawna liczba! Spróbuj jeszcze raz.");
                }
            }

            String kod = null;
            for (String k : zamowienia.keySet()) {

                String[] parts = k.split("-");
                if (parts.length >= 3) {
                    String kodKolor = parts[1];
                    int kodRozmiar;
                    try {
                        kodRozmiar = Integer.parseInt(parts[2]);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    if (kodKolor.equalsIgnoreCase(kolor) && kodRozmiar == rozmiar) {
                        kod = k;
                        break;
                    }
                }
            }

            if (kod == null) {

                kod = generujKod(kolor, rozmiar);
                zamowienia.put(kod, ilosc);
            } else {
                zamowienia.put(kod, zamowienia.get(kod) + ilosc);
            }

            System.out.println("Dodano/aktualizowano kod: " + kod + " | Ilość: " + zamowienia.get(kod));
        }
    }

    private static void usunKlienta() {
        System.out.print("Podaj imię klienta do usunięcia: ");
        String imie = sc.nextLine().trim();
        if (klienci.remove(imie) != null) {
            System.out.println("Usunięto klienta: " + imie);
        } else {
            System.out.println("Nie znaleziono klienta.");
        }
    }

    private static void pokazKlientow() {
        if (klienci.isEmpty()) {
            System.out.println("Brak klientów.");
            return;
        }
        for (var entry : klienci.entrySet()) {
            String imie = entry.getKey();
            Map<String, Integer> zamowienia = entry.getValue();
            int sumaBombek = zamowienia.values().stream().mapToInt(Integer::intValue).sum();

            System.out.println("Klient: " + imie + " | Liczba bombek: " + sumaBombek);
            for (var kodEntry : zamowienia.entrySet()) {
                System.out.println("  - " + kodEntry.getKey() + " | Ilość: " + kodEntry.getValue());
            }
        }
    }
}
