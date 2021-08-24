package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

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
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/cat.png");
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact.withPhoto(photo));
        app.goTo().returnToHomePageByNavigationBar();
        assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.db().contacts();
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deletedContact)));
    }
}
