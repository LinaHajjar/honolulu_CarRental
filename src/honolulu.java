import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class honolulu {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan=new Scanner(new File("src/Cars List"));
        ArrayList <Car> listOfCars = readFromFile(scan);
        System.out.println("brand: "+listOfCars.getFirst().brand);
        System.out.println("model: "+listOfCars.getFirst().model);
        System.out.println("fueltype:"+listOfCars.getFirst().fuelType);
        System.out.println("Reg number: "+listOfCars.getFirst().registrationNb);
        System.out.println("First reg:"+listOfCars.getFirst().firstRegistrationDate);
        System.out.println("odometer: "+listOfCars.getFirst().odometer);
        System.out.println("Desc: "+listOfCars.getFirst().Description);
        System.out.println("Auto:"+listOfCars.getFirst().automaticTransmission);
        System.out.println("AC"+listOfCars.getFirst().AC);
        System.out.println("Rented out:"+listOfCars.getFirst().borrowed);
        System.out.println("Seats:"+listOfCars.getFirst().seats);


        /*
        for(Car c: listOfCars){
            System.out.println(c);
        }*/

    }//end of main

    public static ArrayList<Car> readFromFile (Scanner scan){

        ArrayList<Car> listOfCar=new ArrayList<>();

        while (scan.hasNextLine()){
            String line=scan.nextLine();
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

            double odometer = 0;
            while (linescan.hasNextDouble() && !(linescan.hasNext(";"))){
                odometer= linescan.nextDouble();
            }
            //linescan.next();
            //System.out.println(odometer);

            String description = " ";
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                description += linescan.nextInt() + " ";
            }
            //linescan.next();
            //System.out.println(description);

            boolean automaticTransmission = true;
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                automaticTransmission = linescan.nextBoolean();
            }
            //linescan.next();
            //System.out.println(automaticTransmission);

            boolean AC = true;
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                AC = linescan.nextBoolean();
            }
            //linescan.next();
            //System.out.println(AC);

            boolean borrowed = true;
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                borrowed = linescan.nextBoolean();
            }
            //linescan.next();
            //System.out.println(borrowed);

            int seats = 0;
            while (linescan.hasNext() && !(linescan.hasNext(";"))){
                seats= linescan.nextInt();
            }
            linescan.next();

            //System.out.println(seats);

            Car newcar = new Car(brand.trim(), model.trim(), fueltype.trim(), registrationNb.trim(), firstRegistrationDate, odometer, description.trim(), automaticTransmission, AC, borrowed, seats);
            //.trim() til at fjerne de mellemrum der kommer i slutningen af string.
            listOfCar.add(newcar);

        }//end while hasNextLine

        return (listOfCar);

    }//end readFromFile
}
