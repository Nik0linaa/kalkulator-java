import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Zespol zespol = new Zespol();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Dodaj pracownika");
            System.out.println("2. Pokaż pracowników");
            System.out.println("3. Edytuj pracownika");
            System.out.println("4. Usuń pracownika");
            System.out.println("5. Zlicz wydajność zespołu");
            System.out.println("6. Wyjście");
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
                        if (imie.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\- ]+")) {
                            break;
                        }
                        System.out.println("Imię nie może zawierać cyfr ani znaków specjalnych. Spróbuj jeszcze raz.");
                    }

                    String stanowisko;
                    while (true) {
                        System.out.print("Stanowisko: ");
                        stanowisko = sc.nextLine();
                        if (stanowisko.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\- ]+")) {
                            break;
                        }
                        System.out.println("Stanowisko nie może zawierać cyfr ani znaków specjalnych. Spróbuj jeszcze raz.");
                    }

                    int godziny;
                    while (true) {
                        System.out.print("Liczba godzin: ");
                        try {
                            godziny = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Wprowadź prawidłową liczbę godzin.");
                        }
                    }

                    int bombki;
                    while (true) {
                        System.out.print("Wykonane bombki: ");
                        try {
                            bombki = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Wprowadź prawidłową liczbę bombek.");
                        }
                    }

                    zespol.dodaj(new Pracownik(imie, stanowisko, godziny, bombki));
                    System.out.println("Dodano pracownika.");
                    break;

                case 2:
                    zespol.pokazWszystkich();
                    break;

                case 3:
                System.out.print("Imię pracownika do edycji: ");
                String imieEdytuj = sc.nextLine();

                if (!zespol.czyJestPracownik(imieEdytuj)) {
                    System.out.println("Nie znaleziono pracownika o takim imieniu.");
                    break;  // przerywamy, bo nie ma sensu pytać o resztę
                }

                System.out.print("Nowe stanowisko: ");
                String noweStan = sc.nextLine();

                int noweGodz;
                while (true) {
                    System.out.print("Nowa liczba godzin: ");
                    try {
                        noweGodz = Integer.parseInt(sc.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Podaj poprawną liczbę godzin.");
                    }
                }

                int noweBomb;
                while (true) {
                    System.out.print("Nowa liczba bombek: ");
                    try {
                        noweBomb = Integer.parseInt(sc.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Podaj poprawną liczbę bombek.");
                    }
                }

                if (zespol.edytuj(imieEdytuj, noweStan, noweGodz, noweBomb)) {
                    System.out.println("Zaktualizowano.");
                } else {
                    System.out.println("Nie znaleziono pracownika.");
                }
                break;

                case 4:
                    System.out.print("Imię pracownika do usunięcia: ");
                    String imieUsun = sc.nextLine();
                    if (zespol.usun(imieUsun)) {
                        System.out.println("Usunięto.");
                    } else {
                        System.out.println("Nie znaleziono pracownika.");
                    }
                    break;

                case 5:
                    System.out.println("Łączna liczba bombek: " + zespol.zliczWydajnosc());
                    break;

                case 6:
                    System.out.println("Zakończono.");
                    return;

                default:
                    System.out.println("Błędna opcja.");
            }
        }
    }
}
