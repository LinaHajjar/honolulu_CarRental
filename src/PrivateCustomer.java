public class PrivateCustomer extends Customer {
    int driverslicenceNumber;
    int yearsWithLicence;

    public  PrivateCustomer(){

    }

    public PrivateCustomer(String nameOfDriver, String addressOfDriver, int zipCode, String city, String country, int mobilNr, String email, int driverslicenceNumber, int yearsWithLicence){
        super(nameOfDriver, addressOfDriver, zipCode, city, country, mobilNr, email);
        this.driverslicenceNumber=driverslicenceNumber;
        this.yearsWithLicence=yearsWithLicence;
    }

    public String toString(){
        return (nameOfDriver + " ; " + addressOfDriver + " ; " + zipCode + " ; " + city + " ; " + country +" ; "+ mobilNr +" ; " + email +" ; "+ driverslicenceNumber+" ; "+ yearsWithLicence);
    }

    public String toPrint(){
        return ("---------Private Customer:---------\n" +
                "Name of the driver                : "+ nameOfDriver+ "\n" +
                "Address of the driver             : "+ addressOfDriver + "\n" +
                "Zip Code                          : "+ zipCode + "\n" +
                "City                              : " + city + "\n" +
                "Country                           : "+ country+ "\n" +
                "Mobil number                      : "+ mobilNr+ "\n" +
                "E-mail                            : "+ email + "\n" +
                "Driver's licence number           : "+ driverslicenceNumber+ "\n" +
                "Years with licence                : " +yearsWithLicence+ "\n");
    }



}
