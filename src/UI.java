import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class UI {

    Scanner scan =new Scanner(System.in);

    public static void hovedMenu(Scanner scan, ArrayList<Car> listOfCars, ArrayList<CustomerContract>contracts,ArrayList<Customer> allCustomers) throws IOException {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        System.out.println("====================================================");
        System.out.println("                     Main  menu                     ");
        System.out.println("====================================================");
        System.out.println("         Enter a number to select an option         ");
        System.out.println("====================================================");
        System.out.println("  Press 1 for : See the list of available cars      "); //sorted by Name, kræver ordrene
        System.out.println("  Press 2 for : See the list of borrowed cars       "); //sorted by rentalEndingDate,  kræver ordrene
        System.out.println("  Press 3 for : See the list of contracts           "); //sorted by contractNb
        System.out.println("  Press 4 for : Add a new car                       ");
        System.out.println("  Press 5 for : Delete a car                        ");
        System.out.println("  Press 6 for : Make a new contract                 ");//make a new order
        System.out.println("  Press 7 for : Edit a car                          ");
        System.out.println("  Press 8 for : Edit a contract                     ");
        System.out.println("  Press 9 for : Search and book                     ");
        System.out.println("  Press 10 for: pick-up a car                       ");
        System.out.println("  Press 11 for: return a car                        ");
        System.out.println("  Press 12 for: End the program                     ");

        int choice= scan.nextInt();
        scan.nextLine();

        switch (choice){
            case 1: //done
                System.out.println("you chose option 1: See the list of available cars. ");
                System.out.println("Please enter the start date of the period you want to rent a car\nUse the format year-month-date");
                startDate=LocalDate.parse(scan.nextLine());
                System.out.println("Please enter end date for the rental period");
                endDate=LocalDate.parse(scan.nextLine());
                ArrayList<Car>availableCars = (honolulu.availableCars(listOfCars,startDate,endDate,contracts));
                for(Car c: availableCars){
                    System.out.println(c.shortPrint());
                    System.out.println();
                }
                hovedMenu(scan, listOfCars, contracts,allCustomers);
                break;

            case 2: //done
                System.out.println("you chose option 2: See the list of borrowed cars. ");
                System.out.println("Please enter the start date of the period you want to see unavailable cars for\nUse the format year-month-date");
                startDate=LocalDate.parse(scan.nextLine());
                System.out.println("Please enter end date for the rental period");
                endDate=LocalDate.parse(scan.nextLine());
                ArrayList<Car>unavailableCars = (honolulu.unavailableCars(listOfCars,startDate,endDate,contracts));
                for(Car c: unavailableCars){
                    System.out.println(c.shortPrint());
                    System.out.println();
                }

                hovedMenu(scan, listOfCars, contracts,allCustomers);
                break;

            case 3: //done
                System.out.println("you chose option 3: See the list of contracts. ");
                for(CustomerContract c: contracts){
                    System.out.println(c.toPrint());
                    System.out.println();
                }

                hovedMenu(scan, listOfCars, contracts,allCustomers);
                break;

            case 4: //done
                System.out.println("you chose option 4: Add a new car. ");
                listOfCars.add(honolulu.addCar(scan));
                honolulu.writeToFile(listOfCars);

                hovedMenu(scan, listOfCars, contracts,allCustomers);
                break;

            case 5: //done
                System.out.println("you chose option 5: delete a car. ");
                System.out.println("this is the list of all the cars:");
                int i=1;
                for (Car c: listOfCars){
                    System.out.println("Car number " + i + ": \n" + c.toPrint());
                    System.out.println();
                    i++;
                }
                System.out.println("Please write the brand of the car you want to remove from the list: ");
                String CarBrandDelete=scan.nextLine();
                System.out.println("Write now Please write the model of the car you want to remove from the list: ");
                String CarModelDelete=scan.nextLine();
                listOfCars.removeIf(car1 -> ((car1.brand.equals(CarBrandDelete)&&(car1.model.equals(CarModelDelete)))));
                System.out.println("list of the cars after you deleted " + CarBrandDelete+ " : " +CarModelDelete+ " :\n");
                int j=1;
                for (Car c: listOfCars) {
                    System.out.println("Car number " + j + ": \n" +c.toPrint());
                    System.out.println();
                    j++;
                }

                honolulu.writeToFile(listOfCars);

                hovedMenu(scan, listOfCars, contracts,allCustomers);
                break;

            case 6: //done
                System.out.println("you chose option 6: Make a new contract. ");
                CustomerContract newContract = honolulu.makeContract(scan, listOfCars, contracts,allCustomers);
                /*for (CustomerContract c:contracts){
                    System.out.println(c.toPrint());
                }*/

                hovedMenu(scan, listOfCars, contracts,allCustomers);
                break;

            case 7: //edit a car
                System.out.println("you chose option 7: edit a car. ");
                System.out.println("this is the list of all the cars:");
                int count=1;
                for (Car c: listOfCars){
                    System.out.println("Car number " + count+ ": \n" + c.toPrint());
                    System.out.println();
                    count++;
                }
                System.out.println("Which car do you want to edit? please enter the number of the car : ");
                int NbEdit=scan.nextInt();

                //Car carToEdit=listOfCars.get(NbEdit-1); = listOfCars.get(NbEdit-1);
                System.out.println("you chose to edit the following car:\n" + listOfCars.get(NbEdit-1));

                honolulu.editCar(scan, listOfCars.get(NbEdit-1));

                System.out.println("Here is the car after you changed some information:");
                System.out.println(listOfCars.get(NbEdit-1).toPrint());

                hovedMenu(scan, listOfCars, contracts,allCustomers);
                break;

            case 8:
                System.out.println("you chose option 8: edit a contract. ");
                System.out.println("this is the list of all the contracts:");
                //int count2=1;
                for (CustomerContract c: contracts){
                    System.out.println(c.toPrint());
                    System.out.println();

                    //count2++;
                }
                System.out.println("Which contract do you want to edit? please enter the number of the contract : ");
                int contractEdit=scan.nextInt();
                CustomerContract contractInQuestion = new CustomerContract();
                for(CustomerContract c:contracts){
                    if(c.getContractNumber()==contractEdit){
                        contractInQuestion = c;
                        break;
                    }
                }

                //CustomerContract customerContractEdit=contracts.get(count2-1);
                honolulu.editContract(scan, listOfCars ,contractInQuestion, contracts, allCustomers);

                hovedMenu(scan, listOfCars, contracts,allCustomers);
                break;

            case 9: //done
                System.out.println("you chose option 9: search and book. ");
                honolulu.searchCar(listOfCars,contracts,scan,allCustomers);

                hovedMenu(scan, listOfCars,contracts,allCustomers);
                break;

            case 10: //pick up done
                System.out.println("you chose option 10: pick up a car. ");
                honolulu.pickUpCar(scan, contracts, listOfCars, allCustomers);
                hovedMenu(scan, listOfCars,contracts,allCustomers);
                break;

            case 11: //return done
                System.out.println("You chose option 11: return a car. ");
                honolulu.returnCar(scan,listOfCars,contracts);
                hovedMenu(scan, listOfCars,contracts,allCustomers);
                break;


            case 12:
                System.exit(0);
                break;

            default:
                System.out.println("you have entered a wrong number, please try again");
                hovedMenu(scan, listOfCars, contracts,allCustomers);

        }//end switch

    }//end of HovedMenu

}
