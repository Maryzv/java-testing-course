package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactUpdateTests extends TestBase {

    @Test
    public void testContactUpdate() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Тест", "Тестовый", "Проспект теста 25", "3420000", "89131234567", "test@test.ru", "test1@test.ru", "test1"));
        }

        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Новое имя", "Новая фамилия", "Новый адрес", "33300000", "89527778899", "new@test.ru", "new1@test.ru", null), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().returnToHomePage();
    }
}
