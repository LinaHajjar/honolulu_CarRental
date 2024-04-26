import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class honolulu {
    public static void main(String[] args) throws IOException {

        Scanner scan=new Scanner(new File("src/Cars List"));
        Scanner input =new Scanner(System.in);
        ArrayList <Car> listOfCars = readFromFile(scan);

        for(Car c: listOfCars){
            System.out.println(c.toPrint());
            System.out.println();
        }
        writeToFile(listOfCars);
        UI.hovedMenu(scan);

    }//end of main

    public static ArrayList<Car> readFromFile (Scanner scan){

        ArrayList<Car> listOfCar=new ArrayList<>();

        while (scan.hasNextLine()){
            String line=scan.nextLine();
            if(line.equals("End of file")){
                break;
            }
            Scanner linescan=new Scanner(line);

            String brand= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                brand+=linescan.next();
            }
            linescan.next();
            //System.out.println(brand);

            String model = " ";
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                model += linescan.next();
            }
            linescan.next();
            //System.out.println(model);

            String fueltype= " ";
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                fueltype += linescan.next();
            }
            linescan.next();
            //System.out.println(fueltype);

            String registrationNb = " ";
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                registrationNb += linescan.next();
            }
            linescan.next();
            //System.out.println(registrationNb);

            LocalDate firstRegistrationDate=LocalDate.now();
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                firstRegistrationDate = LocalDate.parse(linescan.next());
            }
            linescan.next();
           // System.out.println(firstRegistrationDate);

            int odometer = 0;
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                odometer= linescan.nextInt();
            }
            linescan.next();
            //System.out.println(odometer);

            String description = " ";
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                description += linescan.next() + " ";
            }
            linescan.next();
            //System.out.println(description);

            boolean automaticTransmission = true;
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                automaticTransmission = linescan.nextBoolean();
            }
            linescan.next();
            //System.out.println(automaticTransmission);

            boolean AC = true;
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                AC = linescan.nextBoolean();
            }
            linescan.next();
            //System.out.println(AC);

            boolean borrowed = true;
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                borrowed = linescan.nextBoolean();
            }
            linescan.next();
            //System.out.println(borrowed);

            int seats = 0;
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                seats= linescan.nextInt();
            }
            //linescan.next();

            //System.out.println(seats);

            Car newcar = new Car(brand.trim(), model.trim(), fueltype.trim(), registrationNb.trim(), firstRegistrationDate, odometer, description.trim(), automaticTransmission, AC, borrowed, seats);
            //.trim() til at fjerne de mellemrum der kommer i slutningen af string.
            listOfCar.add(newcar);

        }//end while hasNextLine


        return (listOfCar);

    }//end readFromFile

    //method that write a new car in the txt-file
    public static void writeToFile (ArrayList<Car> carsList) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Cars List"));
        for (Car car: carsList){
            out.write(car.toString());
        }
        out.write("End of file");
        out.close();
    }//end of writetofile


    // vi mangler at lave et arraylist med customerContract
    public static void makeContract(Scanner input, ArrayList<Car>listOfCars, ArrayList<CustomerContract>contracts){
        System.out.println("Please enter the start date of the period you want to rent a car\n use the format year-month-date");
        LocalDate startDate = LocalDate.parse(input.nextLine());
        System.out.println("And for how long would you like to rent the car?");
        int daysOfRental = input.nextInt();
        LocalDate endDate = startDate.plusDays(daysOfRental);
        System.out.println("These are the available cars for the period you've entered");
        int i =1;
        for(Car c:availableCars(listOfCars, startDate, endDate, contracts)){
            System.out.println(i + " " + c);
            i++;
        }
        System.out.println("which car would you like to rent?, please enter a number");
        int carIndex= input.nextInt();

        System.out.println("How long do you expect you will be driving per day?");
        int maxKm = input.nextInt();


    }//end of makeContract

    public static ArrayList<Car>availableCars(ArrayList<Car>allCars, LocalDate startDate, LocalDate endDate,ArrayList<CustomerContract>contracts){
       ArrayList<Car>availableCars = new ArrayList<>();
       ArrayList<Car>unavailableCars = new ArrayList<>();
       for (CustomerContract c:contracts){
           if(startDate.isAfter(c.getRentalStartDate()) && startDate.isBefore(c.getRentalEndDate())){
               unavailableCars.add(c.getCar());
           }
       }
       availableCars.removeAll(unavailableCars);
       return availableCars;
    }


}
