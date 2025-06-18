import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Menu {
    public static void main(String[] args) {
        Zamowienia zamowienia = new Zamowienia();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Dodaj zamówienie");
            System.out.println("2. Pokaż wszystkie zamówienia");
            System.out.println("3. Filtruj po kliencie");
            System.out.println("4. Filtruj po dacie");
            System.out.println("5. Filtruj po modelu");
            System.out.println("6. Usuń zamówienie");
            System.out.println("7. Wyjście");
            System.out.print("Wybierz opcję: ");

            int wybor;
            try {
                wybor = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź liczbę!");
                continue;
            }

            switch (wybor) {
                case 1:
                    String imie;
                    while (true) {
                        System.out.print("Imię: ");
                        imie = sc.nextLine();
                        if (imie.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\- ]+")) break;
                        System.out.println("Imię nie może zawierać cyfr ani znaków specjalnych.");
                    }

                    String nazwisko;
                    while (true) {
                        System.out.print("Nazwisko: ");
                        nazwisko = sc.nextLine();
                        if (nazwisko.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\- ]+")) break;
                        System.out.println("Nazwisko nie może zawierać cyfr ani znaków specjalnych.");
                    }

                    int ilosc;
                    while (true) {
                        System.out.print("Ilość bombek: ");
                        try {
                            ilosc = Integer.parseInt(sc.nextLine());
                            if (ilosc > 0) break;
                            else System.out.println("Liczba musi być większa od zera.");
                        } catch (NumberFormatException e) {
                            System.out.println("Wprowadź poprawną liczbę!");
                        }
                    }

                    System.out.print("Model: ");
                    String model = sc.nextLine();

                    System.out.print("Kolor: ");
                    String kolor = sc.nextLine();

                    String termin;
                    while (true) {
                        System.out.print("Termin (YYYY-MM-DD): ");
                        termin = sc.nextLine();
                        try {
                            LocalDate.parse(termin);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Zły format daty. Użyj: YYYY-MM-DD.");
                        }
                    }

                    Dane noweZamowienie = new Dane(imie, nazwisko, ilosc, model, kolor, termin);
                    zamowienia.dodajZamowienie(noweZamowienie);
                    System.out.println("Zamówienie dodane.");
                    break;

                case 2:
                    zamowienia.pokazWszystkie();
                    break;

                case 3:
                    System.out.print("Podaj imię lub nazwisko: ");
                    String klient = sc.nextLine();
                    for (Dane d : zamowienia.filtrujPoImieniuLubNazwisku(klient)) {
                        System.out.println(d);
                    }
                    break;

                case 4:
                    System.out.print("Podaj datę (YYYY-MM-DD): ");
                    String data = sc.nextLine();
                    for (Dane d : zamowienia.filtrujPoDacie(data)) {
                        System.out.println(d);
                    }
                    break;

                case 5:
                    System.out.print("Podaj model: ");
                    String mod = sc.nextLine();
                    for (Dane d : zamowienia.filtrujPoModelu(mod)) {
                        System.out.println(d);
                    }
                    break;
                case 6:
                    System.out.print("Podaj numer zamówienia (ID) do usunięcia: ");
                    try {
                        int idDoUsuniecia = Integer.parseInt(sc.nextLine());
                        if (zamowienia.usunPoID(idDoUsuniecia)) {
                            System.out.println("Zamówienie usunięte.");
                        } else {
                            System.out.println("Nie znaleziono zamówienia o podanym numerze.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("To nie jest poprawny numer!");
                    }
                    break;

                case 7:
                    System.out.println("Zakończono.");
                    return;

                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
}
