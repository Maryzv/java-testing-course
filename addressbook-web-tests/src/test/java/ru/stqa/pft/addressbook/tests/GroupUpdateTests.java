package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupUpdateTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("test1")
                    .withHeader("test2")
                    .withFooter("test3"));
        }
    }

    @Test
    public void testGroupUpdate() {
        Groups before = app.db().groups();
        GroupData updateGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(updateGroup.getId())
                .withName("editName")
                .withHeader("editHeader")
                .withFooter("editFooter");
        app.goTo().groupPage();
        app.group().modify(group);
        assertEquals(app.group().count(), before.size());
        Groups after = app.db().groups();

        assertThat(after, equalTo(before.withOut(updateGroup).withAdded(group)));
    }
}
