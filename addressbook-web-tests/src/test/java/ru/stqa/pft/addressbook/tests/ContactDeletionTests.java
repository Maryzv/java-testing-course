package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Тест", "Тестовый", "Проспект теста 25", "3420000", "89131234567", "test@test.ru", "test1@test.ru", "test1"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContactItem(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlertDeleteContact();
        app.getNavigationHelper().returnToHomePageByNavigationBar();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
