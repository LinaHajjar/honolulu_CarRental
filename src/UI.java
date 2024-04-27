import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    Scanner scan =new Scanner(System.in);

    public static void hovedMenu(Scanner scan, ArrayList<Car> listOfCars, ArrayList<CustomerContract>contracts) throws IOException {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        System.out.println("===============================================");
        System.out.println("                   Main menu                   ");
        System.out.println("===============================================");
        System.out.println("      Enter a number to select an option       ");
        System.out.println("===============================================");
        System.out.println("  Press 1: See the list of available cars      "); //sorted by Name, kræver ordrene
        System.out.println("  Press 2: See the list of borrowed cars       "); //sorted by rentalEndingDate,  kræver ordrene
        System.out.println("  Press 3: See the list of contracts           "); //sorted by contractNb
        System.out.println("  Press 4: Add a new car                       ");
        System.out.println("  Press 5: Delete a car                        ");
        System.out.println("  Press 6: Make a new contract                 ");//make a new order
        System.out.println("  Press 7: Edit a car                          ");
        System.out.println("  Press 8: Edit a contract                     ");
        System.out.println("  Press 9: End the program                     ");

        System.out.println("Enter please");
        int choice= scan.nextInt();
        scan.nextLine();

        switch (choice){
            case 1:
                System.out.println("you chose option 1: ");
                System.out.println("Please enter the start date of the period you want to rent a car\nUse the format year-month-date");
                startDate=LocalDate.parse(scan.nextLine());
                System.out.println("Please enter end date for the rental period");
                endDate=LocalDate.parse(scan.nextLine());
                ArrayList<Car>availableCars = (honolulu.availableCars(listOfCars,startDate,endDate,contracts));
                for(Car c: availableCars){
                    System.out.println(c.shortPrint());
                    System.out.println();
                }
                break;

            case 2:
                System.out.println("you chose option 2: ");
                System.out.println("Please enter the start date of the period you want to see unavailable cars for\nUse the format year-month-date");
                startDate=LocalDate.parse(scan.nextLine());
                System.out.println("Please enter end date for the rental period");
                endDate=LocalDate.parse(scan.nextLine());
                ArrayList<Car>unavailableCars = (honolulu.availableCars(listOfCars,startDate,endDate,contracts));
                for(Car c: unavailableCars){
                    System.out.println(c.shortPrint());
                    System.out.println();
                }
                break;

            case 3:
                System.out.println("you chose option 3: ");
                for(CustomerContract c: contracts){
                    System.out.println(c.toPrint());
                    System.out.println();
                }
                break;

            case 4:
                System.out.println("you chose option 4: ");
                listOfCars.add(honolulu.addCar(scan));
                honolulu.writeToFile(listOfCars);
                break;

            case 5:
                System.out.println("you chose option 5: ");
                contracts.add(honolulu.makeContract(scan, listOfCars, contracts));
                break;

            case 6:
                System.out.println("you chose option 6: ");
                honolulu.makeContract(scan, listOfCars, contracts);
                break;

            case 7:
                System.out.println("you chose option 7: ");
                break;

            case 8:

                break;
            case 9:
                System.exit(0);
                break;

            default:
                System.out.println("you have entered a wrong number, please try again");
                hovedMenu(scan, listOfCars, contracts);


        }//end switch

    }//end of HovedMenu

}
