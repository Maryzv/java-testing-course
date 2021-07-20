package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectContactItem();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlertDeleteContact();
    }
}
