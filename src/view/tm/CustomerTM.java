package view.tm;

public class CustomerTM {
    String Id;
    String Name;
    String Address;
    String City;
    String province;
    String postalCode;
    Double salary;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, String city, String province, String postalCode, Double salary) {
        Id = id;
        Name = name;
        Address = address;
        City = city;
        this.province = province;
        this.postalCode = postalCode;
        this.salary = salary;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}