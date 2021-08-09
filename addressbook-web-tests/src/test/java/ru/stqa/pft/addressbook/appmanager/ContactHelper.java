package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomeNumber());
        type(By.name("mobile"), contactData.getMobileNumber());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getAdditionalEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContactItem(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactItemById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptAlertDeleteContact() {
        wd.switchTo().alert().accept();
    }

    public void editContact(int index) {
        wd.findElements(By.name("entry")).get(index).findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void editContactById(int id) {
        wd.findElement(By.xpath("//td/input[@value='" + id + "']/../../td//img[@title='Edit']")).click();
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void returnToContactListPage() {
        click(By.linkText("home page"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToContactListPage();
    }

    public void delete(ContactData contact) {
        selectContactItemById(contact.getId());
        deleteContact();
        acceptAlertDeleteContact();
    }

    public void modify(ContactData contact) {
        editContactById(contact.getId());
        fillContactForm(contact, false);
        updateContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(columns.get(0).findElement(By.name("selected[]")).getAttribute("value"));
            String lastName = columns.get(1).getText();
            String firstName = columns.get(2).getText();

            ContactData contact = new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName);
            contacts.add(contact);
        }

        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(columns.get(0).findElement(By.name("selected[]")).getAttribute("value"));
            String lastName = columns.get(1).getText();
            String firstName = columns.get(2).getText();

            ContactData contact = new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName);;
            contacts.add(contact);
        }

        return contacts;
    }
}
