import java.time.LocalDate;

public class LuxuaryCar extends Car{

    static double defaultPrice = 500.0;
    public LuxuaryCar(){
    }

    public LuxuaryCar(String brand, String model, String fuelType, String registrationNb, LocalDate firstRegistrationDate, int odometer, String Description, boolean automaticTransmission, boolean AC, boolean borrowed, int seats){
        super(brand, model, fuelType, registrationNb, firstRegistrationDate, odometer, Description, automaticTransmission, AC, borrowed, seats);
        this.defaultPrice=defaultPrice;
    }
}
