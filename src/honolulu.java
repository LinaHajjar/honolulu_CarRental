import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.*;

public class honolulu {
    public static void main(String[] args) throws IOException {

        Scanner scan=new Scanner(new File("src/Cars List"));
        Scanner input =new Scanner(System.in);
        ArrayList <Car> listOfCars = readFromFile(scan);
        ArrayList <Customer> customers =readFromFileCustomers(scan);
        ArrayList<CustomerContract> contracts =readFromFileContracts(input,customers, listOfCars);

        for(Car c: listOfCars){
            System.out.println(c.toPrint());
            System.out.println();
        }
        writeToFile(listOfCars);

        for(Customer c: customers){
            System.out.println(c.toPrint());
            System.out.println();
        }

        for(CustomerContract c: contracts){
            System.out.println(c.toPrint());
            System.out.println();
        }

        //UI.hovedMenu(input, listOfCars, readFromFileContracts(scan, customers,listOfCars)); //readFromFileContracts(scan) returns contracts that are read from the Contracts txt file

    }//end of main

    //readFromFile: Cars List to arrayList<Car>
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

    public static void makeContract(Scanner input, ArrayList<Car>listOfCars, ArrayList<CustomerContract>contracts){
        System.out.println("Please enter the start date of the period you want to rent a car\n use the format year-month-date");
        input.nextLine();
        LocalDate startDate=LocalDate.parse(input.nextLine());




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
       ArrayList<Car>availableCars=new ArrayList<>();
       availableCars.addAll(allCars);
       ArrayList<Car>unavailableCars = new ArrayList<>();
       for (CustomerContract c:contracts){
           if(startDate.isAfter(c.getRentalStartDate()) && startDate.isBefore(c.getRentalEndDate())){
               unavailableCars.add(c.getCar());
           }
       }
       availableCars.removeAll(unavailableCars);
       return availableCars;
    }//end of method: availableCars

    //method that reads the contracts from the file and return an arrayList
    public static ArrayList<CustomerContract> readFromFileContracts (Scanner scan, ArrayList<Customer> customer, ArrayList<Car> car)throws FileNotFoundException{
        ArrayList<CustomerContract> contracts = new ArrayList<>();
        Scanner readFile = new Scanner(new File("src/Contracts"));

        //reading the first part of the contracts which is: (private) CustomerContracts
        while (readFile.hasNextLine()){
            String line=readFile.nextLine();
            if (line.equals("Company Contract")){
                break;
            }

            Scanner linescan=new Scanner(line);
            int contractNb=0;
            while ((linescan.hasNext()) && !linescan.hasNext(";")){
                contractNb=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(contractNb);

            String name= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                name+=linescan.next();
            }
            linescan.next();
            //System.out.println(name);

            int tlfNb= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                tlfNb=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(tlfNb);
            Customer customer1=new Customer();
            for (Customer c: customer){
                int j=0;
                if((c.getNameOfDriver().equals(name)) && (c.getMobilNr()==tlfNb)){
                    customer1=c;
                    j=1;
                }
                if (j==1){
                    break;
                }
            }

            LocalDate rentalStartDate = LocalDate.now();
            while (linescan.hasNext() && !linescan.hasNext(";")){
                rentalStartDate=LocalDate.parse(linescan.next());
            }
            linescan.next();
            //System.out.println(rentalStartDate);

            LocalDate rentalEndDate = LocalDate.now();
            while (linescan.hasNext() && !linescan.hasNext(";")){
                rentalEndDate=LocalDate.parse(linescan.next());
            }
            linescan.next();
            //System.out.println(rentalEndDate);

            String registrationNb = "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                registrationNb+=linescan.next();
            }
            linescan.next();
            //System.out.println(name);

            Car car1=new Car();
            for (Car c: car){
                int j=0;
                if((c.getRegistrationNb().equals(registrationNb))){
                    car1=c;
                    j=1;
                }
                if (j==1){
                    break;
                }
            }

            int maxKm= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                maxKm=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(maxKm);

            int odometer= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                odometer=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(odometer);

            CustomerContract newcontract=new CustomerContract(contractNb, customer1, rentalStartDate, rentalEndDate, car1, maxKm, odometer);
            contracts.add(newcontract);
        }//end while reading private customer contracts

        //Now reading the second part of the contracts which is: (company) CustomerContracts
        while (readFile.hasNextLine()){
            String line=readFile.nextLine();
            if (line.equals("end of list")){
                break;
            }

            Scanner linescan=new Scanner(line);
            int contractNb=0;
            while ((linescan.hasNext()) && !linescan.hasNext(";")){
                contractNb=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(contractNb);

            String name= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                name+=linescan.next();
            }
            linescan.next();
            //System.out.println(name);

            int tlfNb= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                tlfNb=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(tlfNb);
            Customer customer2=new Customer();
            for (Customer c: customer){
                int j=0;
                if((c.getNameOfDriver().equals(name)) && (c.getMobilNr()==tlfNb)){
                    customer2=c;
                    j=1;
                }
                if (j==1){
                    break;
                }
            }

            LocalDate rentalStartDate = LocalDate.now();
            while (linescan.hasNext() && !linescan.hasNext(";")){
                rentalStartDate=LocalDate.parse(linescan.next());
            }
            linescan.next();
            //System.out.println(rentalStartDate);

            LocalDate rentalEndDate = LocalDate.now();
            while (linescan.hasNext() && !linescan.hasNext(";")){
                rentalEndDate=LocalDate.parse(linescan.next());
            }
            linescan.next();
            //System.out.println(rentalEndDate);

            String registrationNb = "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                registrationNb+=linescan.next();
            }
            linescan.next();
            //System.out.println(name);

            Car car2=new Car();
            for (Car c: car){
                int j=0;
                if((c.getRegistrationNb().equals(registrationNb))){
                    car2=c;
                    j=1;
                }
                if (j==1){
                    break;
                }
            }

            int maxKm= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                maxKm=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(maxKm);

            int odometer= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                odometer=linescan.nextInt();
            }
            //linescan.next();
            //System.out.println(odometer);

            CustomerContract newcontract=new CustomerContract(contractNb, customer2, rentalStartDate, rentalEndDate, car2, maxKm, odometer);
            contracts.add(newcontract);
        }//end while reading company customer contracts

        return contracts;
    }//end of method readFromFileContracts


    public static ArrayList <Customer> readFromFileCustomers(Scanner scan) throws FileNotFoundException{

        ArrayList<Customer> customers = new ArrayList<>();
        Scanner readFile = new Scanner(new File("src/Customer"));

        //reading the first part of the Customers which is: (private) Customers
        while (readFile.hasNextLine()){
            String line=readFile.nextLine();
            if (line.equals("Company Customer")){
                break;
            }

            Scanner linescan=new Scanner(line);
            String name = "";
            while ((linescan.hasNext()) && !linescan.hasNext(";")){
                name +=linescan.next();
            }
            linescan.next();
            //System.out.println(name);

            String address= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                address+=linescan.next();
            }
            linescan.next();
            //System.out.println(address);

            int zipCode= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                zipCode=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(zipCode);

            String city= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                city +=linescan.next();
            }
            linescan.next();
            //System.out.println(city);

            String country= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                country +=linescan.next();
            }
            linescan.next();
            //System.out.println(country);

            int tlfNb= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                tlfNb=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(tlfNb);

            String email= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                email +=linescan.next();
            }
            linescan.next();
            //System.out.println(email);

            int driverslicenceNumber = 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                driverslicenceNumber=linescan.nextInt();
            }
            //linescan.next();
            //System.out.println(driverslicenceNumber);

            int yearsWithLicence=0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                yearsWithLicence=linescan.nextInt();
            }
            //linescan.next();
            //System.out.println(yearsWithLicence);

            PrivateCustomer customer=new PrivateCustomer(name, address, zipCode, city, country, tlfNb, email, driverslicenceNumber, yearsWithLicence);
            customers.add(customer);
        }//end while reading private customer contracts

        //reading the second part of the Customers which is: Company Customers
        while (readFile.hasNextLine()){
            String line=readFile.nextLine();
            if (line.equals("End Of File")){
                break;
            }

            Scanner linescan=new Scanner(line);
            String name = "";
            while ((linescan.hasNext()) && !linescan.hasNext(";")){
                name +=linescan.next();
            }
            linescan.next();
            //System.out.println(name);

            String address= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                address+=linescan.next();
            }
            linescan.next();
            //System.out.println(address);

            int zipCode= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                zipCode=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(zipCode);

            String city= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                city +=linescan.next();
            }
            linescan.next();
            //System.out.println(city);

            String country= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                country +=linescan.next();
            }
            linescan.next();
            //System.out.println(country);

            int tlfNb= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                tlfNb=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(tlfNb);

            String email= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                email +=linescan.next();
            }
            linescan.next();
            //System.out.println(email);

            String companyName = "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                companyName +=linescan.next();
            }
            linescan.next();
            //System.out.println(companyName);

            String companyAddress= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                companyAddress+=linescan.next();
            }
            linescan.next();
            //System.out.println(companyAddress);

            int companyPhoneNb=0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                companyPhoneNb=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(companyPhoneNb);

            String companyCRN = "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                companyCRN+=linescan.next();
            }
            //linescan.next();
            //System.out.println(companyCRN);

            CompanyCustomer customer=new CompanyCustomer(name, address, zipCode, city, country, tlfNb, email, companyName, companyAddress, companyPhoneNb, companyCRN);
            customers.add(customer);
        }//end while reading private customer contracts

        return customers;
    }//end of readFromFileCustomers

}//end of class
