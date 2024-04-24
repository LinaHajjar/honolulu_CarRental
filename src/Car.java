import java.time.LocalDate;

public class Car {
    String brand;
    String model;
    String fuelType;
    String registrationNb;
    LocalDate firstRegistrationDate;
    double odometer;
    String Description;
    boolean automaticTransmission; //true if automatic
    boolean AC;
    boolean borrowed;
    int seats;

    public Car(){
    }

    public Car(String brand, String model, String fuelType, String registrationNb, LocalDate firstRegistrationDate, double odometer, String Description, boolean automaticTransmission, boolean AC, boolean borrowed, int seats ){
        this.brand=brand;
        this.model=model;
        this.fuelType=fuelType;
        this.registrationNb=registrationNb;
        this.firstRegistrationDate=firstRegistrationDate;
        this.odometer=odometer;
        this.Description=Description;
        this.automaticTransmission=automaticTransmission;
        this.AC=AC;
        this.borrowed=borrowed;
        this.seats=seats;
    }

    public String toString() {
        return (brand + " ; " + model + " ; " + fuelType + " ; " + registrationNb + " ; " + firstRegistrationDate + " ; " + odometer + " ; " +  Description + " ; " +  automaticTransmission + " ; " + AC + " ; " + borrowed+ " ; " + seats + " ; ");
    }

    public String toPrint() {
        return ("brand                   : " + brand+  "\nmodel                   : " + model +"\nfueltype                : " + fuelType + "\nRegistration number     : " +registrationNb + "\nFirst registration date : " + firstRegistrationDate+ "\nodometer                : " + odometer+ "\nDescription             : " +Description+  "\nAutomatic transmission  : " + automaticTransmission + "\nAC                      : " + AC + "\nBorrowed                : " + borrowed + "\nSeats                   : " + seats);
    }
}
