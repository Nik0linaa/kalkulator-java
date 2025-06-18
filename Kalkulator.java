import java.util.Scanner;

public class Kalkulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Podaj pierwszą liczbę: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Wprowadź poprawną liczbę!");
                scanner.next();
                continue;
            }
            double a = scanner.nextDouble();

            System.out.print("Podaj drugą liczbę: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Wprowadź poprawną liczbę!");
                scanner.next();
                continue;
            }
            double b = scanner.nextDouble();

            System.out.println("Dodawanie: " + (a + b));
            System.out.println("Odejmowanie: " + (a - b));
            System.out.println("Mnożenie: " + (a * b));
            if (b != 0) {
                System.out.println("Dzielenie: " + (a / b));
            } else {
                System.out.println("Nie można dzielić przez zero!");
            }

            System.out.print("Chcesz liczyć dalej? (tak/nie): ");
            String odpowiedz = scanner.next();

            if (!odpowiedz.equalsIgnoreCase("tak")) {
                break;
            }
        }
        scanner.close();
    }
}
