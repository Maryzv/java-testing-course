package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactUpdateTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        File photo = new File("src/test/resources/cat.png");
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Тест")
                    .withLastName("Тестовый")
                    .withAddress("Проспект теста 25")
                    .withHomeNumber("3420000")
                    .withMobileNumber("89131234567")
                    .withEmail("test@test.ru")
                    .withAdditionalEmail("test1@test.ru")
                    .withGroup("test1")
                    .withPhoto(photo));
        }
    }

    @Test
    public void testContactUpdate() {
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/cat.png");
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
                .withGroup("test1")
                .withPhoto(photo);
        app.contact().modify(contact);
        app.goTo().returnToHomePage();
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(updateContact).withAdded(contact)));
    }
}
