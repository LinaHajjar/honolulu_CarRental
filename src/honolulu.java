import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class honolulu {
    public static void main(String[] args) throws IOException {

        Scanner scan=new Scanner(new File("src/Cars List"));
        Scanner input =new Scanner(System.in);
        Scanner readFileContract = new Scanner(new File("src/Contracts2"));
        ArrayList <Car> listOfCars = readFromFile(scan);
        ArrayList <Customer> customers =readFromFileCustomers();
        ArrayList<CustomerContract> contracts =readFromFileContracts(readFileContract,customers, listOfCars);

        /*for(Car c: listOfCars){
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
        */


        UI.hovedMenu(input, listOfCars, contracts); //readFromFileContracts(scan) returns contracts that are read from the Contracts txt file

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

    public static Car addCar(Scanner input){
        System.out.println("To add a new car, first we need some information");
        System.out.println("Please enter the brand:");
        String brand= input.nextLine();
        System.out.println("Please enter the model:");
        String model = input.nextLine();
        System.out.println("Please enter the fueltype:");
        String fueltype= input.nextLine();
        System.out.println("Please enter the registration number:");
        String registrationNb = input.nextLine();
        System.out.println("Please enter the date of the first registration, in the format year-month-date::");
        LocalDate firstRegDate=LocalDate.parse(input.nextLine());
        System.out.println("Please enter the kilometers on the odometer");
        int odometer = input.nextInt();
        input.nextLine();
        System.out.println("Please add a description");
        String description = input.nextLine();
        System.out.println("Does the car have automatic transmission? true for yes / false for no");
        boolean automaticTransmission = input.nextBoolean();
        System.out.println("Does the car have air conditioning? true for yes / false for no");
        boolean AC = input.nextBoolean();
        System.out.println("is the car currently rented out? true for yes / false for no");
        boolean borrowed = input.nextBoolean();
        System.out.println("Please enter the number of seats in the car:");
        int seats = input.nextInt();
        Car newCar = new Car(brand,model,fueltype,registrationNb,firstRegDate,odometer,description,automaticTransmission,AC,borrowed,seats);
        return newCar;
    }

    //method that write a new car in the txt-file
    public static void writeToFile (ArrayList<Car> carsList) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Cars List"));
        for (Car car: carsList){
            out.write(car.toString());
        }
        out.write("End of file");
        out.close();
    }//end of writetofile

    public static CustomerContract makeContract(Scanner input, ArrayList<Car>listOfCars, ArrayList<CustomerContract>contracts)throws IOException{
        System.out.println("Please enter the start date of the period you want to rent a car\nUse the format year-month-date");
        LocalDate startDate=LocalDate.parse(input.nextLine());

        System.out.println("And for how many days would you like to rent the car?");
        int daysOfRental = input.nextInt();
        LocalDate endDate = startDate.plusDays(daysOfRental);
        System.out.println("These are the available cars for the period: " + startDate+ " to "+endDate+":");
        int i =1;
        ArrayList<Car>availableCars= availableCars(listOfCars, startDate, endDate, contracts);
        for(Car c:availableCars){
            System.out.println("          Car number: "+i);
            System.out.println(c.toPrint());
            System.out.println();
            i++;
        }
        System.out.println("Which car would you like to rent? please enter the corresponding number ");
        int carIndex= input.nextInt();

        System.out.println("How many km's do you expect you will be driving per day?");
        int maxKm = input.nextInt();

        Customer newCustomer = newCustomer(input);

        CustomerContract newContract = new CustomerContract(+contracts.size()+1,newCustomer,startDate,endDate,availableCars.get(carIndex-1),maxKm,availableCars.get(carIndex-1).getOdometer());
        contracts.add(newContract);
        System.out.println(newContract.toPrint());
        System.out.println("Your booking has been registered, thank you for chosing us for your rental needs");
        FileWriter fw = new FileWriter(new File("src/contracts2"),false);
        for(CustomerContract c:contracts) {
            if (c.getCustomer() instanceof PrivateCustomer) {
                fw.write(c.toFile());
            }
        }
        fw.write("Company Contract\n");
        for(CustomerContract c:contracts) {
            if (c.getCustomer() instanceof CompanyCustomer) {
                fw.write(c.toFile());
            }
        }
        fw.write("End of list");

        fw.close();
        return newContract;

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

    public static Customer newCustomer(Scanner input){
        Customer newCustomer = new Customer();
        System.out.println("Please choose whether its a private customer, or a company customer:");
        System.out.println("Enter 1 for private and 2 for company");
        int customerType = input.nextInt();
        input.nextLine();
        System.out.println("To add a new customer, first we need some information");
        System.out.println("Please enter the name of the driver: ");
        String nameOfDriver = input.nextLine();
        System.out.println("Please enter the address of the driver: ");
        String addressOfDriver = input.nextLine();
        System.out.println("Please enter the zipcode: ");
        int zipCode = input.nextInt();
        input.nextLine();
        System.out.println("Please enter the name of the city: ");
        String city = input.nextLine();
        System.out.println("Please enter the name of the country: ");
        String country = input.nextLine();
        System.out.println("Please enter the phonenumber of the driver: ");
        String mobilNr = input.nextLine();
        System.out.println("Please enter the E-mail of the driver: ");
        String email = input.nextLine();
        if(customerType==1){
            System.out.println("Please enter the drivers license number: ");
            int driversLicenseNumber = input.nextInt();

            System.out.println("Please enter how many years the driver has had his license: ");
            int yearsWithLicense = input.nextInt();
            input.nextLine();
            newCustomer = new PrivateCustomer(nameOfDriver,addressOfDriver,zipCode,city,country,mobilNr,email,driversLicenseNumber,yearsWithLicense);
        }
        else if(customerType == 2){
            System.out.println("Please enter the company name: ");
            String companyName = input.nextLine();
            System.out.println("Please enter the company address: ");
            String companyAddress = input.nextLine();
            System.out.println("Please enter the company phonenumber: ");
            String companyPhoneNr = input.nextLine();
            System.out.println("Please enter the company CRN number: ");
            String companyCRN = input.nextLine();
            newCustomer = new CompanyCustomer(nameOfDriver,addressOfDriver,zipCode,city,country,mobilNr,email,companyName,companyAddress,companyPhoneNr,companyCRN);

        }
        else{
            System.out.println("Wrong input");
        }

        return newCustomer;
    }

    public static ArrayList<Car>unavailableCars(ArrayList<Car>allCars, LocalDate startDate, LocalDate endDate,ArrayList<CustomerContract>contracts){
        ArrayList<Car>unavailableCars = new ArrayList<>();
        for (CustomerContract c:contracts){
            if(overlaps(startDate,endDate,c.getRentalStartDate(),c.getRentalEndDate())){
                unavailableCars.add(c.getCar());
            }
        }
        return unavailableCars;
    }//end of method: availableCars

    public static boolean overlaps(LocalDate s1,LocalDate e1,LocalDate s2,LocalDate e2){
        if(s1.isBefore(e2) && s2.isBefore(e1)){
            return true;
        }
        return false;
    }

    //method that reads the contracts from the file and return an arrayList
    public static ArrayList<CustomerContract> readFromFileContracts (Scanner readFile,ArrayList<Customer> customer, ArrayList<Car> car)throws FileNotFoundException{
        ArrayList<CustomerContract> contracts = new ArrayList<>();
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
                name+=linescan.next()+" ";
            }
            linescan.next();
            //System.out.println(name);

            String tlfNb= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                tlfNb+=linescan.next()+" ";
            }
            linescan.next();

            //System.out.println(tlfNb);
            Customer customer1=new Customer();
            for (Customer c: customer){
                if((c.getNameOfDriver().trim().equals(name.trim())) && (c.getMobilNr().trim().equals(tlfNb.trim()))){
                    customer1=c;
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
                if((c.getRegistrationNb().equals(registrationNb))){
                    car1=c;
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
            if (line.equals("End of list")){
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
                name+=linescan.next()+" ";
            }
            linescan.next();
            //System.out.println(name);

            String tlfNb= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                tlfNb+=linescan.next();
            }
            linescan.next();
            //System.out.println(tlfNb);
            Customer customer2=new Customer();
            for (Customer c: customer){
                if((c.getNameOfDriver().trim().equals(name.trim())) && (c.getMobilNr().trim().equals(tlfNb.trim()))){
                    customer2=c;
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
                if((c.getRegistrationNb().equals(registrationNb))){
                    car2=c;
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


    public static ArrayList <Customer> readFromFileCustomers() throws FileNotFoundException{

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
                name +=linescan.next()+" ";
            }
            linescan.next();



            String address= "testaddress";
            while ((linescan.hasNext()) && !linescan.hasNext(";")){
                address +=linescan.next()+" ";
            }
            linescan.next();

            //linescan.next();
            //System.out.println(address);

            int zipCode= 0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                zipCode=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(zipCode);

            String city= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                city +=linescan.next()+" ";
            }
            linescan.next();
            //System.out.println(city);

            String country= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                country +=linescan.next()+" ";
            }
            linescan.next();
            //System.out.println(country);

            String tlfNb= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                tlfNb=linescan.next();
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
            linescan.next();
            //System.out.println(driverslicenceNumber);

            int yearsWithLicence=0;
            while (linescan.hasNext() && !linescan.hasNext(";")){
                yearsWithLicence=linescan.nextInt();
            }
            linescan.next();
            //System.out.println(yearsWithLicence);

            PrivateCustomer customer=new PrivateCustomer(name.trim(), address.trim(), zipCode, city.trim(), country.trim(), tlfNb.trim(), email, driverslicenceNumber, yearsWithLicence);
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
                name +=linescan.next()+" ";
            }
            linescan.next();
            //System.out.println(name);

            String address= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                address+=linescan.next()+ " ";
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
                city +=linescan.next()+" ";
            }
            linescan.next();
            //System.out.println(city);

            String country= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                country +=linescan.next()+" ";
            }
            linescan.next();
            //System.out.println(country);

            String tlfNb= "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                tlfNb=linescan.next();
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

            String companyPhoneNb="";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                companyPhoneNb=linescan.next();
            }
            linescan.next();
            //System.out.println(companyPhoneNb);

            String companyCRN = "";
            while (linescan.hasNext() && !linescan.hasNext(";")){
                companyCRN+=linescan.next();
            }
            linescan.next();
            //System.out.println(companyCRN);

            CompanyCustomer customer=new CompanyCustomer(name.trim(), address.trim(), zipCode, city.trim(), country.trim(), tlfNb.trim(), email, companyName, companyAddress, companyPhoneNb, companyCRN);
            customers.add(customer);
        }//end while reading private customer contracts

        return customers;
    }//end of readFromFileCustomers




    public static void searchCar(ArrayList<Car>listOfCars,ArrayList<CustomerContract> contracts,Scanner scan)throws IOException{
        ArrayList<Car> matches = new ArrayList<>();
        System.out.println("What would you like to search for? ");
        String searchString = scan.nextLine();
        String[] keywords = searchString.split(",\\s+|\\.");
        for (Car c : listOfCars) {
            for (int i = 0; i < keywords.length; i++) {
                if (containsIgnoreCase(c.getBrand(),keywords[i]) || containsIgnoreCase(c.getModel(),keywords[i]) || containsIgnoreCase(c.getDescription(),keywords[i])) {
                    matches.add(c);
                    break;
                }

            }

        }
        int matchIndex = 1;
        if(matches.isEmpty()){
            System.out.println("No matches");
            return;
        }
        else if(matches.size()==1){
            System.out.println("        1 Match found");
            System.out.println(matches.getFirst().shortPrint());
            System.out.println();
        }
        else {
            int i = 1;
            System.out.println("          Matches found: "+matches.size());
            for (Car c : matches) {
                System.out.println("Match number: "+i);
                System.out.println(c.shortPrint());
                System.out.println();
                i++;
            }
            System.out.println("Please enter the match number of the car you're interested in, and we will check the availability");
            matchIndex = scan.nextInt();
        }
        Car chosenCar = matches.get(matchIndex-1);
        if(!contractForCarExists(chosenCar,contracts)){
            System.out.println("No current bookings for the car");
        }
        else {
            System.out.println("The requested car is unavailable in the following periods:");
            for (CustomerContract c : contracts) {
                if (c.getCar().equals(chosenCar)) {
                    System.out.println("Fra: "+c.getRentalStartDate()+" til "+c.getRentalEndDate());
                    //System.out.println(c.getRentalStartDate().datesUntil(c.getRentalEndDate()).collect(Collectors.toList()));
                }
            }
        }
        System.out.println("Would you like to book the car?\n1 for yes, 2 for no");
        int bookOrNot = scan.nextInt();
        scan.nextLine();
        if (bookOrNot==1){
            contracts.add(makeContract(scan,listOfCars,contracts));
        }
        else{
            return;
        }

    }
    public static boolean containsIgnoreCase(String s1, String s2) {
        return s1.toLowerCase().contains(s2.toLowerCase());
    }

    public static boolean contractForCarExists(Car car,ArrayList<CustomerContract>contracts){
        for(CustomerContract c: contracts){
            if(c.getCar().equals(car)){
                return true;
            }
        }
        return false;
    }
}//end of class
