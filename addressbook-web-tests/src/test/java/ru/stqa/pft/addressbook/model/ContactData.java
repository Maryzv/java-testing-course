package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String homeNumber;
    private final String mobileNumber;
    private final String email;
    private final String additionalEmail;

    public ContactData(String firstName, String lastName, String address, String homeNumber, String mobileNumber, String email, String additionalEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.homeNumber = homeNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.additionalEmail = additionalEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAdditionalEmail() {
        return additionalEmail;
    }
}
