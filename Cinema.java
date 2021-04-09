package cinema;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    public static int rows;
    public static int seats;
    public static char[][] matrix;
    public static int soldNum = 0;
    public static double soldPercent = 0.00;
    public static int currentIncome = 0;
    public static int totalIncome;

    static void showMenu() {
        createMatrix();
        String[] menu = new String[]{"1. Show the seats", "2. Buy a ticket", "3. Statistics", "0. Exit"};
        boolean flag = true;

        while (flag) {
            for (String s : menu) {
                System.out.println(s);
            }

            Scanner sc = new Scanner(System.in);
            int action = sc.nextInt();

            switch (action) {
                case 1:
                    drawMatrix(matrix);
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    showStats();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter any number from the list");
                    break;
            }
        }
    }

    static void createMatrix() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();

        matrix = new char[rows][seats];
        for (char[] arr : matrix) {
            Arrays.fill(arr, 'S');
        }

        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            totalIncome = rows / 2 * seats * 10 + (rows - rows / 2)  * seats * 8;
        }
    }

    static void drawMatrix(char[][] array) {
        int rowCnt = 1;
        int seatCnt = 1;
        System.out.println("Cinema:");
        System.out.print(" ");

        while (seatCnt <= array.length + 1) {
            System.out.print(" " + seatCnt);
            seatCnt++;
        }

        System.out.println();

        for (char[] arr : array) {
            System.out.print(rowCnt);
            for (char c : arr) {
                System.out.print(" " + c);
            }
            System.out.println();
            rowCnt++;
        }
    }

    static void buyTicket() {
        int rowNum;
        int seatNum;
        int price;

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a row number:");
            rowNum = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = sc.nextInt();

            if (rowNum > matrix.length || rowNum < 1 || seatNum > matrix[rowNum - 1].length || seatNum < 1) {
                System.out.println("Wrong input!");
            } else {
                if (matrix[rowNum - 1][seatNum - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    matrix[rowNum - 1][seatNum - 1] = 'B';
                    soldNum += 1;
                    break;
                }
            }
        }

        price = rows * seats <= 60 || rowNum <= rows / 2 ? 10 : 8;
        System.out.println("Ticket price: $" + price);
        currentIncome += price;
    }

    static void showStats() {
        soldPercent = (double)soldNum / (rows * seats) * 100;
        System.out.printf("Number of purchased tickets: %d%n", soldNum);
        System.out.printf("Percentage: %.2f%%%n", soldPercent);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    public static void main(String[] args) {
        showMenu();
    }
}
