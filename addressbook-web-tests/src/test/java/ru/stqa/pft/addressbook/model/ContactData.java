package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstName")
    private String firstName;

    @Expose
    @Column(name = "lastName")
    private String lastName;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "home")
    @Type(type = "text")
    private String homeNumber;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobileNumber;

    @Column(name = "work")
    @Type(type = "text")
    private String workNumber;

    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    private String additionalEmail;

    @Column(name = "email3")
    @Type(type = "text")
    private String otherAdditionalEmail;

    @Expose
    @Transient
    private String group;

    @Transient
    private String allNumbers;

    @Transient
    private String allEmails;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAllNumbers() {
        return allNumbers;
    }

    public int getId() {
        return id;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public String getOtherAdditionalEmail() {
        return otherAdditionalEmail;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withOtherAdditionalEmail(String otherAdditionalEmail) {
        this.otherAdditionalEmail = otherAdditionalEmail;
        return this;
    }

    public ContactData withWorkNumber(String workNumber) {
        this.workNumber = workNumber;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withAllNumbers(String allNumbers) {
        this.allNumbers = allNumbers;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
        return this;
    }

    public ContactData withMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withAdditionalEmail(String additionalEmail) {
        this.additionalEmail = additionalEmail;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
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

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(mobileNumber, that.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, mobileNumber);
    }
}
