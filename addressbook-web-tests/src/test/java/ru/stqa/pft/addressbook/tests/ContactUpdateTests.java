package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactUpdateTests extends TestBase {

    @Test
    public void testContactUpdate() {
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Новое имя", "Новая фамилия", "Новый адрес", "33300000", "89527778899", "new@test.ru", "new1@test.ru"));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().returnToHomePage();
    }
}
