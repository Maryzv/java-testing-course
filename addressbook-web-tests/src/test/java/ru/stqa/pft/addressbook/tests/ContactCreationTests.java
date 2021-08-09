package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("Тест")
                .withLastName("Тестовый")
                .withAddress("Проспект теста 25")
                .withHomeNumber("3420000")
                .withMobileNumber("89131234567")
                .withEmail("test@test.ru")
                .withAdditionalEmail("test1@test.ru")
                .withGroup("editName");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
