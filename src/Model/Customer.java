package Model;

public class Customer {
    String CustomerId;
    String CustomerName;
    String CustomerAddress;
    String CustomerCity;
    Double CustomerSalary;
    String CustomerProvince;
    String CustomerPostalCode;


    public Customer() {
    }

    public Customer(String customerId, String customerName, String customerAddress, String customerCity, String customerProvince, String customerPostalCode, Double customerSalary) {
        CustomerId = customerId;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
        CustomerCity = customerCity;
        CustomerProvince = customerProvince;
        CustomerPostalCode = customerPostalCode;
        CustomerSalary = customerSalary;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return CustomerCity;
    }

    public void setCustomerCity(String customerCity) {
        CustomerCity = customerCity;
    }

    public String getCustomerProvince() {
        return CustomerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        CustomerProvince = customerProvince;
    }

    public String getCustomerPostalCode() {
        return CustomerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        CustomerPostalCode = customerPostalCode;
    }

    public Double getCustomerSalary() {
        return CustomerSalary;
    }

    public void setCustomerSalary(Double customerSalary) {
        CustomerSalary = customerSalary;
    }
}