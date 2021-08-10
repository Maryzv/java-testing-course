package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactUpdateTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Тест")
                    .withLastName("Тестовый")
                    .withAddress("Проспект теста 25")
                    .withHomeNumber("3420000")
                    .withMobileNumber("89131234567")
                    .withEmail("test@test.ru")
                    .withAdditionalEmail("test1@test.ru")
                    .withGroup("test1"));
        }
    }

    @Test
    public void testContactUpdate() {
        Contacts before = app.contact().all();
        ContactData updateContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(updateContact.getId())
                .withFirstName("Тест1")
                .withLastName("Тестовый1")
                .withAddress("Проспект теста 25")
                .withHomeNumber("3420000")
                .withMobileNumber("89131234567")
                .withEmail("test@test.ru")
                .withAdditionalEmail("test1@test.ru")
                .withGroup("test1");
        app.contact().modify(contact);
        app.goTo().returnToHomePage();
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withOut(updateContact).withAdded(contact)));
    }
}
