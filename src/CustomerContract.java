import java.time.LocalDate;
import java.time.Period;

public class CustomerContract {
    int contractNumber;
    Customer customer;
    LocalDate rentalStartDate;
    LocalDate rentalEndDate;
    Car car;
    int maxKm;
    int odometerAtRentalStartDate;


    public CustomerContract(){
    }

    public CustomerContract (int contractNumber, Customer customer, LocalDate rentalStartDate, LocalDate rentalEndDate, Car car, int maxKm, int odometerAtRentalStartDate){
        this.contractNumber=contractNumber;
        this.customer=customer;
        this.rentalStartDate=rentalStartDate;
        this.rentalEndDate=rentalEndDate;
        this.car=car;
        this.maxKm=maxKm;
        this.odometerAtRentalStartDate=odometerAtRentalStartDate;
    }


    public String toString(){
        return (contractNumber + " ; " + customer + " ; " + rentalStartDate + " ; " + rentalEndDate + " ; " + car +" ; "+ maxKm +" ; " + odometerAtRentalStartDate);
    }


    public String toPrint(){
        return("================= Contract =================\n"+
               "¤ Contract number                 : "+ contractNumber+ "\n" +
               customer.toPrint() +"\n"+
               "¤ Rental start date               : "+ rentalStartDate +"\n" +
               "¤ Rental end date                 : "+ rentalEndDate+ "\n" +
                car.toPrint() + "\n"+
               "¤ Max KM                          : "+ maxKm+ "\n" +
               "¤ Odometer at rental start date   : " +odometerAtRentalStartDate +"\n");

    }



    /*static Period	between(LocalDate startDateInclusive, LocalDate endDateExclusive)
    Obtains a Period consisting of the number of years, months, and days between two dates.
    public*/

    public LocalDate getRentalStartDate(){
        return rentalStartDate;
    }
    public LocalDate getRentalEndDate(){
        return rentalEndDate;
    }
    public Car getCar(){
        return car;
    }
    public int duration(){
        Period between = Period.between(rentalStartDate, rentalEndDate);
        int months = between.getMonths();
        int days =between.getDays();
        int totalDays= (months * 30) + days;
        return(totalDays);
    }

    public double calculatePrice(){
        double defaultPrice= 200.0;
        return (defaultPrice + 100 *duration() );
    }

}
