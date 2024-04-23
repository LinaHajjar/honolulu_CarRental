public class CompanyCustomer extends Customer{
    String companyName;
    String companyAddress;
    int companyPhoneNb;
    String companyCRN;

    public CompanyCustomer(){

    }

    public CompanyCustomer(String nameOfDriver, String addressOfDriver, int zipCode, String city, String country, int mobilNr, String email, String companyName, String companyAddress, int companyPhoneNb, String companyCRN){
        super(nameOfDriver, addressOfDriver, zipCode, city, country, mobilNr, email);
        this.companyName=companyName;
        this.companyAddress=companyAddress;
        this.companyPhoneNb=companyPhoneNb;
        this.companyCRN=companyCRN;
    }

}
