import java.util.*;

public class KinoVerwaltungV2Version2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String adminEntry = "admin1234";
        final int pin = 5555;
        final int sweepstakePrice = 5;

        String[][] cinemaData = {
                {"1", "Batman", "20:15", "1", "14.25 ", "5"},
                {"2", "Matrix", "22:00", "2", "12.50", "2"},
                {"3", "Inception", "17:00", "3", "9.99", "0"},
                {"4", "Forest Gump", "19:00", "4", "10.99", "10"},
                {"5", "Garfield 3D", "21:00", "5", "16.99", "7"},
        };

        ArrayList<Integer> chosenMovies = new ArrayList<>();

        //double expensiveTicketPrice = 1000;
        double cheapestTicketPrice = 1000; //initialize with a high value so that it won't affect the comparison on the loop
        double ticketPrice;
        //int cheapestMovieAvailability = 0;
        int ticketAvailable;

        for (int i = 0; i < cinemaData.length; i++) {

            ticketPrice = Double.parseDouble(cinemaData[i][cinemaData[i].length - 2]);
            ticketAvailable = Integer.parseInt(cinemaData[i][cinemaData[i].length - 1]);


            if (ticketPrice < cheapestTicketPrice && ticketAvailable != 0) {
                cheapestTicketPrice = ticketPrice;

                // cheapestMovieAvailability = Integer.parseInt(cinemaData[i][5]);
            }


        }

        System.out.println("Cheapest Ticket Price: " + cheapestTicketPrice);


        double userBudget = 0;
        boolean transactionCompleted = false;
        String userInput = "0";


        while (!transactionCompleted) {

            System.out.println("-----------------------------------------------");
            System.out.println("         **** Welcome to KinoPlexx ***");
            System.out.println("-----------------------------------------------\n");

            System.out.println("Wie viel Geld hast du dabei? Bitte gib einen positiven Betrag ein: ");
            userInput = sc.nextLine();

            if (userInput.equals(adminEntry)) {
                System.out.println("-----------------------------------");
                System.out.println("  ** Welcome to admin page! **");
                System.out.println("-----------------------------------");

                System.out.println("\nEnter your 4 digit pin code");

                int pinCode = 0;

                while (pinCode != pin) {
                    pinCode = sc.nextInt();

                    if (pinCode == pin) {
                        System.out.println("\n*** Log in Successful! ***\n");
                    } else {
                        System.out.println("Please try again. Enter your 4 digit pin code.");
                    }
                }


                char adminEdit;
                boolean isAdmin = false;

                do {
                    System.out.println("Do you want to edit a Movie? Enter [y/n]?");
                    adminEdit = sc.next().charAt(0);

                    sc.nextLine();

                } while (adminEdit != 'n' && adminEdit != 'y');

                if (adminEdit == 'y') {
                    isAdmin = true;
                }

                while (isAdmin) {
                    //admin view of the cinema board
                    System.out.printf("\n%-12s %-20s %-10s %-6s %-10s %-15s%n",
                            "Film Number", "Movie Title", "Time", "Hall", "Price", "Available Seats");
                    System.out.println("----------------------------------------------------------------------");

                    for (String[] movie : cinemaData) {
                        System.out.printf("%-12s %-20s %-10s %-6s %-10s %-18s%n",
                                movie[0], movie[1], movie[2], movie[3], movie[4], movie[5]);
                    }
                    System.out.println("----------------------------------------------------------------------\n");

                    System.out.println("Which movie would you like to edit? Choose from 1 to " + cinemaData.length);
                    int movieID = sc.nextInt();

                    //edge case warning for movieID
                    if (movieID <= 0) {
                        System.out.println("Sie haben eine ungültige Nummer eingegeben.");
                    } else if (movieID > cinemaData.length) {
                        System.out.println("Derzeit werden nur " + cinemaData.length + " Filme vorgeführt.");
                    }

                    //if movieID is within the range
                    if (movieID >= 0 && movieID <= cinemaData.length) {
                        System.out.println("\nYou are about to edit Film Number " + movieID + "\n");

                        String[] movieDetails = cinemaData[movieID - 1];

                        System.out.println("1. Movie Title: " + movieDetails[1]);
                        System.out.println("2. Uhrzeit: " + movieDetails[2]);
                        System.out.println("3. Saal: " + movieDetails[3]);
                        System.out.println("4. Preis: " + movieDetails[4]);
                        System.out.println("5. Restplätze: " + movieDetails[5] + "\n");


                        int movieDetailsToEdit;


                        do {
                            System.out.println("Please choose which movie details would you like to edit (Enter between 1 to 5, z.B. 1 for Movie Title).");
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
                                    cinemaData[movieID - 1][4] = String.format(Locale.US, "%.2f", Double.parseDouble(sc.next()));
                                    ;
                                    break;

                                case 5:
                                    System.out.println("Enter the available Seats:");
                                    cinemaData[movieID - 1][5] = sc.next();
                                    break;

                                default:
                                    System.out.println("You enter an invalid Choice");

                            }

                            System.out.println("You successfully edited Film number " + movieDetailsToEdit + ": " + Arrays.deepToString(cinemaData[movieID - 1]));


                        } while (movieDetailsToEdit <= 0 || movieDetailsToEdit > 5);

                        System.out.println("\nDo you want to edit more movie details [y/n]");
                        adminEdit = sc.next().charAt(0);

                        sc.nextLine(); // added to consume the leftover newline character after next

                        if (adminEdit == 'n') {
                            System.out.println("\n****** Exiting the admin page... *******\n");
                            isAdmin = false;
                        }
                    }

                }

            }

            //boolean isNumber = userInput.matches("[0-9]+(\\.[0-9]+)?");//short way to check for double

            boolean isNumber = true;
            boolean foundDecimal = false;

            for (int i = 0; i < userInput.length() && isNumber; i++) {

                if (userInput.charAt(i) >= '0' && userInput.charAt(i) <= '9') { //alternative checking (Character.isDigit(userInput.charAt(i)))
                } else if (userInput.charAt(i) == '.' && !foundDecimal) {
                    foundDecimal = true;
                } else if (userInput.charAt(i) == '-') {
                    System.out.println("Please enter a valid Amount");
                    isNumber = false;
                } else {
                    isNumber = false;
                }
            }

            if (isNumber) {
                userBudget = Double.parseDouble(userInput); // Parse the input as a double
                //String formattedUserBudget = String.format(Locale.US, "%.2f", userBudget);


                // Check if the amount is positive
                if (userBudget > 0 && userBudget > cheapestTicketPrice) {


                    boolean menuOption = false;

                    do {

                        System.out.println("\nWas möchten Sie als Nächstes tun? Bitte wählen Sie eine der folgenden Optionen:");

                        String menu = """
                                ------------------------------
                                       1. Ticket kaufen
                                       2. Film ansehen
                                       3. Gewinnspiel
                                       4. Kino verlassen
                                ------------------------------
                                """;

                        System.out.print(menu);
                        System.out.println("\nIhr aktuelles Guthaben: " + userInput + " €");

                        System.out.println("Geben Sie eine Zahl zwischen 1 und 4 ein: ( z.B. 1 für Ticket kaufen)");
                        int userChoice;

                        boolean changeBudget = true;

                        do {
                            userChoice = sc.nextInt();

                            if (userChoice == 1 && Double.parseDouble(userInput) < cheapestTicketPrice) {

                                System.out.println("Current Guthaben: " + userInput + "\n The cheapest Kino Ticket is: " + cheapestTicketPrice);
                                System.out.println("Geben Sie eine Zahl zwischen 2 und 4 ein: ( z.B. 2 für Film Ansehen)");
                                userChoice = 0;

                            } else if (userChoice > 4) {
                                System.out.println("Please enter a valid Choice, We only have 4 Choices");

                            } else if (userChoice < 1) {
                                System.out.println("Please enter a valid Choice, We only have 4 Choices)");
                            }

                        } while ((userChoice < 1 || userChoice > 4));


                        switch (userChoice) {
                            //ticket kaufen choice
                            case 1:

                                int movieChoice;


                                do {
                                    // print the Kino Board
                                    System.out.println("--------------------------------------------------------------------");
                                    System.out.printf("%-8s %-18s %-10s %-6s %-8s %-12s%n",
                                            "Filmnr", "Filmname", "Uhrzeit", "Saal", "Preis", "Restplätze");
                                    System.out.println("--------------------------------------------------------------------");

                                    String ticketStatus;
                                    //int ticketAvailable;

                                    for (String[] cinemaDatum : cinemaData) {

                                        ticketAvailable = Integer.parseInt(cinemaDatum[5]);

                                        if (ticketAvailable > 0) {
                                            ticketStatus = "verfügbar";
                                        } else {
                                            ticketStatus = "ausgebucht";
                                        }

                                        ticketPrice = Double.parseDouble(cinemaDatum[4].trim());

                                        System.out.printf("%-8s %-18s %-10s %-6s %-8s %-12s%n",
                                                cinemaDatum[0] + ".", cinemaDatum[1], cinemaDatum[2], cinemaDatum[3], cinemaDatum[4], ticketStatus);
                                    }
                                    System.out.println("--------------------------------------------------------------------\n");

                                    //user Input for Movie Choice
                                    System.out.println("Welchen (nicht ausgebuchten) Film möchtest du sehen 1 - " + cinemaData.length + " ? (0 zum abbrechen)");
                                    movieChoice = sc.nextInt();


                                    if (movieChoice < 0) {
                                        System.out.println("Sie haben eine ungültige Nummer eingegeben.");
                                    } else if (movieChoice > cinemaData.length) {
                                        System.out.println("Derzeit werden nur " + cinemaData.length + " Filme vorgeführt.");
                                    } else if (movieChoice == 0) {
                                        System.out.print("Ticketverkauf endet... Danke für Ihren Besuch.\n");
                                    }


                                    //if movieChoice between the choices only 1 - to array length this will run
                                    if (movieChoice > 0 && movieChoice <= cinemaData.length) {
                                        ticketPrice = Double.parseDouble(cinemaData[movieChoice - 1][4]);
                                        ticketAvailable = Integer.parseInt(cinemaData[movieChoice - 1][cinemaData[0].length - 1]); // to update the current ticket Availability

                                        System.out.println("ticketAvailable: " + ticketAvailable);

                                        if (userBudget < ticketPrice) {
                                            System.out.println("Sie haben nicht genug Guthaben für diesen Film. Der Ticketpreis ist " + ticketPrice + "€, und Sie haben nur " + String.format("%.2f", userBudget) + "€.");
                                            ;
                                        } else {

                                            if (ticketAvailable == 0) {
                                                System.out.println("\nIhr ausgewählter Film " + movieChoice + " ist leider vollständig ausgebucht.");
                                            }

                                            if (ticketAvailable > 0) {
                                                int ticketCount; //the number of tickets the user entered for a certain movie
                                                double totalTicketCost; //the total amount of the purchased Tickets


                                                do {
                                                    System.out.println("Es sind noch " + ticketAvailable + " Tickets um jeweils " + ticketPrice + "€ dafür verfügbar. Wie viele möchtest du kaufen?");
                                                    ticketCount = sc.nextInt();

                                                    totalTicketCost = ticketCount * ticketPrice;
                                                    double ticketsWithinBudget = userBudget / ticketPrice; // to determine how many tickets the user can buy still

                                                    //edge cases warning for amount of tickets input
                                                    if (ticketCount <= 0) {
                                                        System.out.println("Sie haben eine ungültige Nummer eingegeben.");
                                                    } else if (ticketCount > ticketAvailable) {
                                                        System.out.println("Sie haben mehr Tickets eingegeben, als verfügbar sind.");
                                                    } else if (totalTicketCost > userBudget) {
                                                        System.out.println(
                                                                "Gesamtkosten von " + ticketCount + " Tickets: " + totalTicketCost + "\nSie haben nur " + String.format("%.2f", userBudget) +
                                                                        "€. Sie können nur noch " + ticketsWithinBudget + " Tickets kaufen. Bitte geben Sie eine geringere Anzahl an Tickets ein."
                                                        );
                                                    }

                                                } while (ticketCount > ticketAvailable || ticketCount <= 0 || totalTicketCost > userBudget);


                                                //to add chosen Movies to the chosenMovies Arraylist
                                                for (int i = 0; i < ticketCount; i++) {
                                                    chosenMovies.add(movieChoice);
                                                }

                                                ticketAvailable -= ticketCount; //update the available ticket
                                                cinemaData[movieChoice - 1][cinemaData[0].length - 1] = String.valueOf(ticketAvailable); //updates the monitor if ausgebucht or verfügbar
                                                totalTicketCost = ticketCount * ticketPrice;
                                                userBudget -= totalTicketCost; //update the remaining user Budget

                                                System.out.println("Sie kaufen " + ticketCount + " Tickets um " + totalTicketCost + "€ und hast jetzt noch " + userBudget + "€.");
                                            }
                                        }

                                    }
                                    //shows the actual user Credit and the movies Chosen

                                    System.out.println("==========================");
                                    System.out.println("Aktuelles Guthaben: " + String.format(Locale.US, "%.2f" ,userBudget) + "€");
                                    System.out.println("Gekaufter Tickets: " + chosenMovies);
                                    System.out.println("==========================\n");


                                    if (userBudget >= sweepstakePrice && userBudget < cheapestTicketPrice) {

                                        System.out.println("Your Budget is not enough to buy the cheapest ticket which cost" + cheapestTicketPrice +
                                                "\nAber Sie können für " + sweepstakePrice + "€ an gewinnspiel teilnehmen."
                                        );


                                        movieChoice = 0;

//                                    System.out.println("--------------------------------------------------------------");
//                                    System.out.println(" Sie haben nicht genug Guthaben für weiteren Einkauf.\n Vielen Dank für den Ticketkauf. Ticketverkauf endet.. ");
//                                    movieChoice = 0; // this will exit the ticketverkauf when the userbudget falls below the ticketPrice
//                                    System.out.println("--------------------------------------------------------------");
                                    }

                                    if (userBudget < sweepstakePrice) {
                                        System.out.println(" Sie haben nicht genug Guthaben für weiteren Einkauf.\n Vielen Dank für den Ticketkauf. Ticketverkauf endet..");
                                        movieChoice = 0;
                                    }


                                } while (movieChoice != 0);

                                userInput = String.valueOf(userBudget);
                                break;


                            case 2: //To Show Movie Tickets

                                int selectedFilmToWatch;

                                ArrayList<String> scheduledMovies = new ArrayList<>(); //movies chosen to be watch alone or with Friends


                                do {
                                    System.out.println("Liste der Filme, die Sie gekauft haben:");
                                    System.out.println("............................................................");


                                    for (int i = 0; i < cinemaData.length; i++) {

                                        int filmNumber = Integer.parseInt(cinemaData[i][0]);
                                        String movieTitle = cinemaData[i][1];

                                        int ticketsBought = 0;

                                        for (int chosenMovie : chosenMovies) {
                                            if (chosenMovie == filmNumber) {
                                                ticketsBought++;
                                            }
                                        }

                                        if (ticketsBought > 0) {

                                            System.out.println("Filmnr: " + filmNumber + " | Movie Titel: " + movieTitle + " | Ticketsanzahl: " + ticketsBought + ".");
                                            System.out.println("............................................................");
                                        }

                                    }

                                    System.out.println("\nWelchen Film (für den du noch ein Ticket hast) möchtest du sehen? (0 für abbrechen)\n");
                                    selectedFilmToWatch = sc.nextInt();


                                    if (chosenMovies.contains(selectedFilmToWatch)) {

                                        int remainingTickets = 0;
                                        for (int i = 0; i < chosenMovies.size(); i++) {
                                            if (chosenMovies.get(i) == selectedFilmToWatch) {
                                                remainingTickets++;
                                            }
                                        }

                                        if (remainingTickets > 0) {
                                            System.out.println("You have " + remainingTickets + " ticket(s) for Filmnr " + selectedFilmToWatch);

                                            System.out.println("Wie viele Tickets wird sie nutzen");
                                            int ticketsToWatch = sc.nextInt();

                                            if (ticketsToWatch > remainingTickets) {
                                                System.out.println("You have only " + remainingTickets + " ticket(s) for Filmnr " + selectedFilmToWatch);
                                            } else {
                                                for (int i = 0; i < ticketsToWatch; i++) {
                                                    chosenMovies.remove(Integer.valueOf(selectedFilmToWatch));
                                                    scheduledMovies.add(String.valueOf(cinemaData[selectedFilmToWatch - 1][1]));
                                                }

                                                remainingTickets -= ticketsToWatch;
                                                System.out.println("You now have " + remainingTickets + " ticket(s) left for Filmnr " + selectedFilmToWatch);
                                            }


                                        } else {
                                            System.out.println("You don't have any tickets left for Filmnr " + selectedFilmToWatch);
                                        }

                                        //System.out.println("You still have. ticket " + selectedFilmToWatch);
                                        //chosenMovies.remove(Integer.valueOf(selectedFilmToWatch));
                                        System.out.println("\nDu schaust dir den Film " + scheduledMovies + " an. Viel Spaß!");

                                        if (chosenMovies.isEmpty()) {
                                            System.out.println("Keine weiteren Kinotickets zum Auschecken.");
                                            selectedFilmToWatch = 0;
                                        }

                                    }

                                } while (selectedFilmToWatch != 0);


                                break;
                            case 3:
                                if (userBudget > sweepstakePrice) {
                                    System.out.println("Welcome to Kinoplex case 3 Gewinnspiel");
                                }
                                break;
                            case 4:
                                System.out.println("Welcome to Kinoplex");
                                break;

                            default:
                                System.out.println("Invalid input");
                        }

                        //transactionCompleted = true; // Exit the loop if transaction is completed

                    } while (!menuOption);

                } else if (userBudget < cheapestTicketPrice && userBudget > 0) {
                    System.out.println("The cheapest ticket is: " + cheapestTicketPrice + ". Enter enough Credit");
                } else {
                    System.out.println("Please enter a valid Amount");
                }


            } //end if(isNumber)


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
}
