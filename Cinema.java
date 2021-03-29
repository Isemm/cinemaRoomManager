package cinema;
import java.util.Scanner;
public class Cinema {

    public static void showTheSeats(String cinema[][]) {
        System.out.println("Cinema:");
        for (int i = 0; i <= cinema[0].length; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void buyATicket(String cinema[][]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowNum = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNum = scanner.nextInt();
        while (rowNum < 1 || rowNum > cinema.length || seatNum < 1 || seatNum > cinema[0].length) {
            System.out.println("Wrong input!");
            System.out.println("Enter a row number:");
            rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scanner.nextInt();
        }
        while (cinema[rowNum -1][seatNum -1].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            System.out.println("Enter a row number:");
            rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scanner.nextInt();
        }
        cinema [rowNum - 1][seatNum - 1] = "B";
        int price = 0;
        if (cinema.length * cinema[0].length < 60) {
            price = 10;
        } else {
            if (cinema.length % 2 == 0 && rowNum <= cinema.length / 2 || cinema.length % 2 != 0 && rowNum <= (cinema.length) / 2){
                price = 10;
            } else {
                price = 8;
            }
        }
        System.out.println();
        System.out.printf("Ticket price: $%d", price);
        System.out.println();
    }
    public static void statistics(String [][] cinema, int ticketsBought) {
        int cinemaSize = cinema[0].length * cinema.length;
        double percentage = 0;
        int income = 0;
        int totalIncome = 0;
        if (ticketsBought > 0) {
            percentage = ((double)ticketsBought / cinemaSize) * 100 ;
        }
        if (cinemaSize < 60) {
            totalIncome = cinemaSize * 10;
        } else {
            if (cinemaSize % 2 == 0) {
                totalIncome = (cinemaSize / 2) * 8 + cinemaSize * 10;
            } else if (cinemaSize % 2 != 0) {
                totalIncome = cinemaSize / 2 * 10 + (cinemaSize - 1) / 2 * 8;
            }
        }
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
                if (cinema[i][j].equals("B")) {
                    if(cinemaSize < 60) {
                        income += 10;
                    } else if (cinemaSize % 2 == 0 && i < (cinema[0].length / 2)) {
                        income += 10;
                    } else if (cinemaSize % 2 == 0 && i > (cinema[0].length / 2)) {
                        income += 8;
                    } else if (cinemaSize % 2 != 0 && i < (cinema[0].length / 2)) {
                        income += 10;
                    } else if (cinemaSize % 2 != 0 && i >= (cinema[0].length - 1) / 2) {
                        income += 8;
                    }
                }
            }
        }
        System.out.println("Number of purchased tickets: " + ticketsBought);
        System.out.printf("Percentage: %.2f%% %n", percentage);
        System.out.printf("Current income: $%d%n", income);
        System.out.printf("Total income: $%d%n", totalIncome);
        System.out.println();
    }
    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();
        String[][] cinema = new String[rows][seats];
        int ticketsBought = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                 cinema[i][j] = "S";
            }
        }
        showTheSeats(cinema);
        showMenu();
        int menuChoice = scanner.nextInt();
        while (menuChoice != 0) {
            switch (menuChoice) {
                case 1:
                    showTheSeats(cinema);
                    break;
                case 2:
                    buyATicket(cinema);
                    ++ticketsBought;
                    break;
                case 3:
                    statistics(cinema, ticketsBought);
                    break;
            }
            showMenu();
            menuChoice = scanner.nextInt();
        }
    }
}