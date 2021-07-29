package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupUpdateTests extends TestBase {

    @Test
    public void testGroupUpdate() {
        app.getNavigationHelper().gotoGroupPage();

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test2"));
        }

        app.getGroupHelper().selectGroupItem();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("editName", null, "editFooter"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
