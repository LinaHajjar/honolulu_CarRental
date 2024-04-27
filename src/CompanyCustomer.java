public class CompanyCustomer extends Customer{
    String companyName;
    String companyAddress;
    String companyPhoneNb;
    String companyCRN;

    public CompanyCustomer(){

    }

    public CompanyCustomer(String nameOfDriver, String addressOfDriver, int zipCode, String city, String country, String mobilNr, String email, String companyName, String companyAddress, String companyPhoneNb, String companyCRN){
        super(nameOfDriver, addressOfDriver, zipCode, city, country, mobilNr, email);
        this.companyName=companyName;
        this.companyAddress=companyAddress;
        this.companyPhoneNb=companyPhoneNb;
        this.companyCRN=companyCRN;
    }

    public String toString(){
        return (nameOfDriver + " ; " + addressOfDriver + " ; " + zipCode + " ; " + city + " ; " + country +" ; "+ mobilNr +" ; " + email +" ; "+ companyName+" ; "+ companyAddress +" ; "+companyPhoneNb+ " ; " + companyCRN);
    }

    public String toPrint() {
        return ("---------Company Customer:---------\n" +
                "Name of the driver                : " + nameOfDriver + "\n" +
                "Address of the driver             : " + addressOfDriver + "\n" +
                "Zip Code                          : " + zipCode + "\n" +
                "City                              : " + city + "\n" +
                "Country                           : " + country + "\n" +
                "Mobil number                      : " + mobilNr + "\n" +
                "E-mail                            : " + email + "\n" +
                "Name of the company               : " + companyName+ "\n" +
                "Address of the company            : " + companyAddress+ "\n" +
                "Company's phone number            : " + companyPhoneNb+ "\n" +
                "Company's CRN                     : " +companyCRN+ "\n");

    }


}
