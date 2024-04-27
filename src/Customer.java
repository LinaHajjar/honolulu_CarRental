public class Customer {
    String nameOfDriver;
    String addressOfDriver;
    int zipCode;
    String city;
    String country;
    int mobilNr;
    String email;

    public Customer(){

    }
    public Customer(String nameOfDriver, String addressOfDriver, int zipCode, String city, String country, int mobilNr, String email ){
        this.nameOfDriver=nameOfDriver;
        this.addressOfDriver=addressOfDriver;
        this.zipCode=zipCode;
        this.city=city;
        this.country=country;
        this.mobilNr=mobilNr;
        this.email=email;
    }

    public  int getMobilNr (){
        return mobilNr;
    }

    public String getNameOfDriver (){
        return nameOfDriver;
    }

    public String toString(){
        return (nameOfDriver + " ; " + addressOfDriver + " ; " + zipCode + " ; " + city + " ; " + country +" ; "+ mobilNr +" ; " + email);
    }

    public String toPrint(){
        return ("Name of the driver                : "+ nameOfDriver + "\n" +
                "Address of the driver             : "+ addressOfDriver + "\n" +
                "Zip Code                          : "+zipCode + "\n" +
                "City                              : "+city + "\n" +
                "Country                           : "+country + "\n" +
                "Mobil number                      : "+mobilNr + "\n" +
                "E-mail                            : "+email + "\n");
    }

}
