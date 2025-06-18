import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Map<String, Integer> zasoby = new HashMap<>();

    public static void main(String[] args) {
        zasoby.put("szklo", 0);
        zasoby.put("farba_czerwona", 0);
        zasoby.put("farba_zielona", 0);
        zasoby.put("brokat", 0);
        zasoby.put("tasiemka", 0);
        zasoby.put("zawieszki", 0);

        System.out.println("=== Fabryka bombek  ===");

        while (true) {



            System.out.println("\nCo chcesz zrobić?");
            System.out.println("1. Doładuj zasoby");
            System.out.println("2. Wyprodukuj bombkę");
            System.out.println("3. Wyjście");
            System.out.print("Twój wybór: ");
            String wybor = sc.nextLine();

            if (wybor.equals("1")) {
                doladujZasoby();
            } else if (wybor.equals("2")) {
                produkujBombke();
            } else if (wybor.equals("3")) {
                System.out.println("koniec!");
                break;
            } else {
                System.out.println("Nieznana opcja, spróbuj jeszcze raz.");
            }
        }
    }

    private static void wyswietlZasoby() {
        zasoby.forEach((mat, ile) -> System.out.println("- " + mat + ": " + ile));
    }

    private static void doladujZasoby() {
        System.out.println("Podaj nazwę zasobu do doładowania: ");
        String mat = sc.nextLine().toLowerCase();


        if (!zasoby.containsKey(mat)) {
            zasoby.put(mat, 0);
            System.out.println("Dodano nowy zasób: " + mat);
        }

        System.out.print("Podaj ilość do doładowania: ");
        int ile = pobierzDodatniaLiczbe();
        zasoby.put(mat, zasoby.get(mat) + ile);
        System.out.println("Doładowano " + ile + " jednostek zasobu " + mat);
    }

    private static void produkujBombke() {
        System.out.print("Podaj nazwę klienta: ");
        String klient = sc.nextLine();

        System.out.println("Podaj ilość szkła (g) na bombkę:");
        int szklo = pobierzDodatniaLiczbe();

        System.out.println("Podaj kolor farby:");
        String farba = sc.nextLine().toLowerCase();

        System.out.println("Podaj ilość farby (ml) na bombkę:");
        int farbaIlosc = pobierzDodatniaLiczbe();

        System.out.println("Podaj ilość brokatu (g) na bombkę:");
        int brokat = pobierzDodatniaLiczbe();

        System.out.println("Podaj ilość tasiemek (szt) na bombkę:");
        int tasiemka = pobierzDodatniaLiczbe();

        System.out.println("Podaj ilość zawieszek (szt) na bombkę:");
        int zawieszki = pobierzDodatniaLiczbe();


        Map<String, Integer> potrzebne = new HashMap<>();
        potrzebne.put("szklo", szklo);
        potrzebne.put("farba_" + farba, farbaIlosc);
        potrzebne.put("brokat", brokat);
        potrzebne.put("tasiemka", tasiemka);
        potrzebne.put("zawieszki", zawieszki);

        boolean mozna = true;
        for (var e : potrzebne.entrySet()) {
            String mat = e.getKey();
            int ile = e.getValue();
            int stan = zasoby.getOrDefault(mat, 0);
            if (stan < ile) {
                System.out.println("Brak zasobu: " + mat + ". Potrzeba " + ile + ", masz " + stan);
                mozna = false;
            }
        }

        if (!mozna) {
            System.out.println("Nie da się wyprodukować bombki, uzupełnij zasoby.");
            return;
        }


        for (var e : potrzebne.entrySet()) {
            String mat = e.getKey();
            int ile = e.getValue();
            zasoby.put(mat, zasoby.get(mat) - ile);
        }

        System.out.println("Wyprodukowano bombkę dla klienta " + klient + " z następującymi materiałami:");
        potrzebne.forEach((k, v) -> System.out.println(" - " + k + ": " + v));
    }

    private static int pobierzDodatniaLiczbe() {
        while (true) {
            String input = sc.nextLine();
            try {
                int liczba = Integer.parseInt(input);
                if (liczba < 0) {
                    System.out.print("Podaj liczbę >= 0: ");
                    continue;
                }
                return liczba;
            } catch (NumberFormatException e) {
                System.out.print("To nie jest liczba! Spróbuj jeszcze raz: ");
            }
        }
    }
}
