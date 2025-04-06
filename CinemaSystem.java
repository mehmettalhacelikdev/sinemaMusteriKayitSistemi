import java.util.Scanner;

public class CinemaSystem {
    static final int MAX_FILMS = 10;
    static final int MAX_CUSTOMERS = 20;

    static String[] filmNames = new String[MAX_FILMS];
    static String[] filmDurations = new String[MAX_FILMS];
    static String[] filmGenres = new String[MAX_FILMS];
    static int filmCount = 0;

    static String[] customerNames = new String[MAX_CUSTOMERS];
    static String[] customerEmails = new String[MAX_CUSTOMERS];
    static int customerCount = 0;

    static int[][] tickets = new int[MAX_CUSTOMERS][1]; // [customerIndex][filmIndex]

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Sinema Müşteri Kayıt Sistemi ---");
            System.out.println("1. Film Ekle");
            System.out.println("2. Müşteri Ekle");
            System.out.println("3. Bilet Oluştur");
            System.out.println("4. Filmleri Listele");
            System.out.println("5. Müşterileri Listele");
            System.out.println("6. Biletleri Listele");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // dummy read

            switch (choice) {
                case 1 -> addFilm(scanner);
                case 2 -> addCustomer(scanner);
                case 3 -> createTicket(scanner);
                case 4 -> listFilms();
                case 5 -> listCustomers();
                case 6 -> listTickets();
                case 0 -> System.out.println("Çıkış yapılıyor...");
                default -> System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }

        } while (choice != 0);

        scanner.close();
    }

    static void addFilm(Scanner scanner) {
        if (filmCount >= MAX_FILMS) {
            System.out.println("Maksimum film sayısına ulaşıldı.");
            return;
        }

        System.out.print("Film adı: ");
        filmNames[filmCount] = scanner.nextLine();

        System.out.print("Film süresi: ");
        filmDurations[filmCount] = scanner.nextLine();

        System.out.print("Film türü: ");
        filmGenres[filmCount] = scanner.nextLine();

        filmCount++;
        System.out.println("Film başarıyla eklendi.");
    }

    static void addCustomer(Scanner scanner) {
        if (customerCount >= MAX_CUSTOMERS) {
            System.out.println("Maksimum müşteri sayısına ulaşıldı.");
            return;
        }

        System.out.print("Müşteri adı: ");
        customerNames[customerCount] = scanner.nextLine();

        System.out.print("Müşteri email: ");
        customerEmails[customerCount] = scanner.nextLine();

        customerCount++;
        System.out.println("Müşteri başarıyla eklendi.");
    }

    static void createTicket(Scanner scanner) {
        if (customerCount == 0 || filmCount == 0) {
            System.out.println("Lütfen önce müşteri ve film ekleyin.");
            return;
        }

        System.out.println("\nMüşteriler:");
        for (int i = 0; i < customerCount; i++) {
            System.out.println(i + " - " + customerNames[i]);
        }

        System.out.print("Müşteri numarası: ");
        int customerIndex = scanner.nextInt();
        scanner.nextLine();

        if (customerIndex < 0 || customerIndex >= customerCount) {
            System.out.println("Geçersiz müşteri numarası.");
            return;
        }

        System.out.println("\nFilmler:");
        for (int i = 0; i < filmCount; i++) {
            System.out.println(i + " - " + filmNames[i]);
        }

        System.out.print("Film numarası: ");
        int filmIndex = scanner.nextInt();
        scanner.nextLine();

        if (filmIndex < 0 || filmIndex >= filmCount) {
            System.out.println("Geçersiz film numarası.");
            return;
        }

        tickets[customerIndex][0] = filmIndex;
        System.out.println("Bilet başarıyla oluşturuldu.");
    }

    static void listFilms() {
        if (filmCount == 0) {
            System.out.println("Henüz film eklenmedi.");
            return;
        }

        System.out.println("\n--- Film Listesi ---");
        for (int i = 0; i < filmCount; i++) {
            System.out.println((i + 1) + ". " + filmNames[i] + " | Süre: " + filmDurations[i] + " | Tür: " + filmGenres[i]);
        }
    }

    static void listCustomers() {
        if (customerCount == 0) {
            System.out.println("Henüz müşteri eklenmedi.");
            return;
        }

        System.out.println("\n--- Müşteri Listesi ---");
        for (int i = 0; i < customerCount; i++) {
            System.out.println((i + 1) + ". " + customerNames[i] + " | Email: " + customerEmails[i]);
        }
    }

    static void listTickets() {
        boolean found = false;
        System.out.println("\n--- Bilet Listesi ---");
        for (int i = 0; i < customerCount; i++) {
            int filmIndex = tickets[i][0];
            if (filmIndex >= 0 && filmIndex < filmCount) {
                System.out.println(customerNames[i] + " -> " + filmNames[filmIndex]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Henüz bilet oluşturulmamış.");
        }
    }
}
