import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    Scanner scan =new Scanner(System.in);

    public static void hovedMenu(Scanner scan, ArrayList<Car> listOfCars, ArrayList<CustomerContract>contracts) throws FileNotFoundException {
        System.out.println("===============================================");
        System.out.println("                   Hovedmenu                   ");
        System.out.println("===============================================");
        System.out.println("      Indtast et tal for vælge en mulighed     ");
        System.out.println("===============================================");
        System.out.println("  Press 1: See the list of available cars      "); //sorted by Name, kræver ordrene
        System.out.println("  Press 1: See the list of borrowed cars       "); //sorted by rentalEndingDate,  kræver ordrene
        System.out.println("  Press 2: See the list of contracts           "); //sorted by contractNb
        System.out.println("  Press 3: Add a new car                       ");
        System.out.println("  Press 4: Delete a car                        ");
        System.out.println("  Press 5: Make a new contract                 ");//make a new order
        System.out.println("  Press 6: Edit a car                          ");
        System.out.println("  Press 7: Edit a contract                     ");
        System.out.println("  Press 8: End the program                     ");

        int choice= scan.nextInt();

        switch (choice){
            case 1:
                System.out.println("you chose option 1: ");
                break;

            case 2:
                System.out.println("you chose option 2: ");
                break;

            case 3:
                System.out.println("you chose option 3: ");
                break;

            case 4:
                System.out.println("you chose option 4: ");
                break;

            case 5:
                System.out.println("you chose option (make a new contract).");
                honolulu.makeContract(scan, listOfCars, contracts);
                break;

            case 6:
                System.out.println("you chose option 6: ");
                break;

            case 7:
                System.out.println("you chose option 7: ");
                break;

            case 8:
                System.exit(0);
                break;

            default:
                System.out.println("you have entered a wrong number, please try again");
                hovedMenu(scan, listOfCars, contracts);


        }//end switch




    }//end of HovedMenu




}
