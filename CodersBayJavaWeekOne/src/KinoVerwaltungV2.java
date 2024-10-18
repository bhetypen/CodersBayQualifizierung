import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class KinoVerwaltungV2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String adminEntry = "adminSecretEntry1234";
//        final String savedUserName = "admin";
//        final String savedPassword = "admin1234";
//
//        String userName = "";
//        String userPassword = "";
//
//        do {
//            while (!userName.equals(savedUserName)) {
//                System.out.println("Enter your username remember it's case sensitive: ");
//                userName = scanner.nextLine();
//            }
//            System.out.println("Enter your password remember it's case sensitive: ");
//            userPassword = scanner.nextLine();
//        } while (!userPassword.equals(savedPassword));

        String[][] cinemaData = {
                {"1", "Batman", "20:15", "1", "14.00 ", "5"},
                {"2", "Matrix", "22:00", "2", "12.50", "2"},
                {"3", "Inception", "17:00", "3", "9.99", "0"},
                {"4", "Forest Gump", "19:00", "4", "10.99", "10"},
                {"5", "Garfield 3D", "21:00", "5", "16.99", "7"},
        };


        double cheapestTicketPrice = 1000; //initialize with a high value so that it won't affect the comparison on the loop
        double ticketPrice;

        for (int i = 0; i < cinemaData.length; i++) {

            ticketPrice = Double.parseDouble(cinemaData[i][cinemaData[i].length - 2]);

            if (ticketPrice < cheapestTicketPrice) {
                cheapestTicketPrice = ticketPrice;
            }
        }

        double userBudget = 0;
        boolean transactionCompleted = false;
        String userInput = "";


        while (!transactionCompleted) {

            System.out.println("Filmnr   Filmname        Uhrzeit    Saal     Preis    Restplätze");
            System.out.println("--------------------------------------------------------------");

            String ticketStatus;
            int ticketAvailable;

            for (String[] cinemaDatum : cinemaData) {

                ticketAvailable = Integer.parseInt(cinemaDatum[5]);

                if (ticketAvailable > 0) {
                    ticketStatus = "verfügbar";
                } else {
                    ticketStatus = "ausgebucht";
                }

                System.out.printf("%-8s %-15s %-10s %-8s %-8s %-10s\n",
                        cinemaDatum[0] + ".", cinemaDatum[1], cinemaDatum[2], cinemaDatum[3], cinemaDatum[4], ticketStatus);
            }
            System.out.println("--------------------------------------------------------------");

            System.out.println("Wie viel Geld hast du dabei? Bitte gib einen positiven Betrag ein: ");
            userInput = sc.nextLine();

            if (userInput.equals(adminEntry)) {
                System.out.println("-----------------------------------");
                System.out.println("  ** Welcome to admin page! **");
                System.out.println("-----------------------------------");

                char adminEdit;
                boolean isAdmin = false;

                do {
                    System.out.println("Do you want to edit something the movie Details enter y/n");
                    adminEdit = sc.next().charAt(0);

                    sc.nextLine();

                } while (adminEdit != 'n' && adminEdit != 'y');

                if (adminEdit == 'y') {
                    isAdmin = true;
                }

                while (isAdmin) {

                    System.out.println("Which movie would you like to edit");
                    int movieID = sc.nextInt();

                    //edge case warning for movieID
                    if (movieID <= 0) {
                        System.out.println("Sie haben eine ungültige Nummer eingegeben.");
                    } else if (movieID > cinemaData.length) {
                        System.out.println("Derzeit werden nur " + cinemaData.length + " Filme vorgeführt.");
                    }

                    //if movieID is within the range
                    if (movieID >= 0 && movieID <= cinemaData.length) {
                        System.out.println("You chose Film Number " + movieID + "\n");


                        Object[] movieDetails = cinemaData[movieID - 1];

                        System.out.println("1. Movie Title: " + movieDetails[1]);
                        System.out.println("2. Uhrzeit: " + movieDetails[2]);
                        System.out.println("3. Saal: " + movieDetails[3]);
                        System.out.println("4. Preis: " + movieDetails[4]);
                        System.out.println("5. Restplätze: " + movieDetails[5] + "\n");


                        int movieDetailsToEdit;


                        do {
                            System.out.println("Please Choose which movie Details would you like to edit (Enter between 1 to 6, z.B. 1 for Movie Title)");
                            movieDetailsToEdit = sc.nextInt();


                            switch (movieDetailsToEdit) {
                                case 1:
                                    System.out.println("Enter the Movie Title");
                                    sc.nextLine();
                                    String movieTitle = sc.nextLine();
                                    cinemaData[movieID - 1][1] = movieTitle;
                                    break;

                                case 2:
                                    System.out.println("Enter the Uhrzeit");
                                    cinemaData[movieID - 1][2] = sc.next();
                                    break;

                                case 3:
                                    System.out.println("Enter the Saal Number");
                                    cinemaData[movieID - 1][3] = sc.next();
                                    break;

                                case 4:
                                    System.out.println("Enter the Movie Ticket Price");
                                    cinemaData[movieID - 1][4] = sc.next();
                                    break;

                                case 5:
                                    System.out.println("Enter the available Seats");
                                    cinemaData[movieID - 1][4] = sc.next();
                                    break;

                                default:
                                    System.out.println("You enter an invalid Choice");

                            }

                            System.out.println("You successfully edited Film number " + movieDetailsToEdit + ": " + Arrays.deepToString(cinemaData[movieID - 1]));

                        } while (movieDetailsToEdit <= 0 || movieDetailsToEdit > 5 );


                        System.out.println("Do you want to edit more");
                        adminEdit = sc.next().charAt(0);



                        sc.nextLine(); // added to consume the leftover newline character after next()

                        if (adminEdit == 'n') {
                            isAdmin = false;
                        }
                    }


                }

            }

            //boolean isNumber = userInput.matches("[0-9]+(\\.[0-9]+)?");//short way to check for double

            boolean isNumber = true;
            boolean foundDecimal = false;


            for (int i = 0; i < userInput.length() && isNumber; i++) {

                if (userInput.charAt(i) >= '0' && userInput.charAt(i) <= '9') {
                    isNumber = true;
                } else if (userInput.charAt(i) == '.' && !foundDecimal) {
                    foundDecimal = true;
                    isNumber = true;
                } else {
                    isNumber = false;
                }
            }


            if (isNumber) {
                userBudget = Double.parseDouble(userInput); // Parse the input as a double
                // Check if the amount is positive
                if (userBudget > 0 && userBudget > cheapestTicketPrice) {
                    System.out.println("Welcome to " + userBudget);
                    transactionCompleted = true; // Exit the loop if transaction is completed
                } else if (userBudget < cheapestTicketPrice) {
                    System.out.println("The cheapest ticket is: " + cheapestTicketPrice + ". Enter enough Credit");
                } else {
                    System.out.println("Please enter a valid Amount");
                }
            }

        }

//        do {
//            System.out.println("Wie viel Geld hast du dabei? Bitte gib einen positiven Betrag ein: ");
//            try {
//                userBudget = sc.nextDouble();
//
//                //warning when the user enters an invalid number
//                if (userBudget < cheapestTicketPrice && userBudget > 0) {
//                    System.out.println("Ihr Geld reicht nicht aus, Das günstigste Ticket kostet " + cheapestTicketPrice + "€.");
//                } else if (userBudget <= 0) {
//                    System.out.println("Sie haben eine ungültige Nummer eingegeben.");
//                } else if (userBudget > 500) {
//                    System.out.println("Für eine einmalige Transaktion akzeptieren wir nur bis zu 500 Euro.");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Ungültige Eingabe. Bitte gib eine gültige Zahl ein.");
//                sc.next();
//            }
//
//        } while (userBudget < cheapestTicketPrice || userBudget > 500);


    }
}
