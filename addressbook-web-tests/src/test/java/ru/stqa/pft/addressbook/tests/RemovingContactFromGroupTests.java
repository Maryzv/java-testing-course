package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

public class RemovingContactFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("test2")
                    .withHeader("test3")
                    .withFooter("test4"));
            app.goTo().returnToHomePageByNavigationBar();
        }

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
                    .withPhoto(photo));
        }
    }

    @Test
    public void testRemovingContactToGroup() {
        Groups groups = app.db().groups();
        GroupData selectedGroup = groups.stream().filter((g) -> g.getContacts().size() > 0).findFirst().orElse(null);

        if (selectedGroup == null) {
            selectedGroup = groups.iterator().next();
            addContactToGroup(selectedGroup);
            selectedGroup = app.db().groupById(selectedGroup.getId());
        }

        ContactData selectedContact = selectedGroup.getContacts().iterator().next();
        app.contact().removeContactFromGroup(selectedContact, selectedGroup);

        ensureThatContactRemovedFromGroup(selectedGroup, selectedContact);
    }

    private void ensureThatContactRemovedFromGroup(GroupData selectedGroup, ContactData selectedContact) {
        selectedContact = app.db().contactById(selectedContact.getId());
        Assert.assertTrue(selectedContact.getGroups().stream().noneMatch((g) -> g.getId() == selectedGroup.getId()));
    }

    public void addContactToGroup(GroupData group) {
        ContactData contact = app.db().contacts().iterator().next();
        app.contact().addContactToGroup(contact, group);
        app.goTo().returnToHomePageByNavigationBar();
    }
}
