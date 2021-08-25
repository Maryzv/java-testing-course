package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

public class AddingContactToGroupTests extends TestBase {
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
                    .withPhoto(photo));
        }
    }

    @Test
    public void testAddingContactToGroup() {
        app.goTo().returnToHomePageByNavigationBar();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData selectedContact = contacts.iterator().next();
        Groups contactGroups = selectedContact.getGroups();

        if (groups.size() == contactGroups.size()) {
            createNewGroup();
            groups = app.db().groups();
        }

        GroupData selectedGroup = getGroupWithoutContact(groups, selectedContact);
        app.contact().addContactToGroup(selectedContact, selectedGroup);

        ensureThatContactAddedToGroup(selectedContact, selectedGroup);
    }

    private void ensureThatContactAddedToGroup(ContactData selectedContact, GroupData selectedGroup) {
        selectedContact = app.db().contactById(selectedContact.getId());
        Assert.assertTrue(selectedContact.getGroups().stream().anyMatch((g) -> g.getId() == selectedGroup.getId()));
    }

    private GroupData getGroupWithoutContact(Groups groups, ContactData contact) {
        for (GroupData group : groups) {
            if (group.getContacts().stream().noneMatch((c) -> c.getId() == contact.getId())) {
                return group;
            }
        }

        Assert.fail();
        return new GroupData();
    }

    private void createNewGroup() {
        app.goTo().groupPage();
        app.group().create(new GroupData()
                .withName("test2")
                .withHeader("test3")
                .withFooter("test4"));
        app.goTo().returnToHomePageByNavigationBar();
    }
}
