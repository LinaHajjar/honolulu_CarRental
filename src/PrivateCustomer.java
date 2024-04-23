public class PrivateCustomer extends Customer {
    int driverslicenceNummer;
    int yearsWithLicence;

    public  PrivateCustomer(){

    }

    public PrivateCustomer(String nameOfDriver, String addressOfDriver, int zipCode, String city, String country, int mobilNr, String email, int driverslicenceNummer, int yearsWithLicence){
        super(nameOfDriver, addressOfDriver, zipCode, city, country, mobilNr, email);
        this.zipCode=zipCode;
        this.yearsWithLicence=yearsWithLicence;
    }



}
