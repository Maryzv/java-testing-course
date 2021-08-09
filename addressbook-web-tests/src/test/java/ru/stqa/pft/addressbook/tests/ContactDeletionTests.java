package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

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
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().returnToHomePageByNavigationBar();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deletedContact)));
    }
}
