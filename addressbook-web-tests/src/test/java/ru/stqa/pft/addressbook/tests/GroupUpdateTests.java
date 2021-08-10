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
        app.goTo().groupPage();

        if (app.group().all().size() == 0) {
            app.group().create(new GroupData()
                    .withName("test1")
                    .withHeader("test2")
                    .withFooter("test3"));
        }
    }

    @Test
    public void testGroupUpdate() {
        Groups before = app.group().all();
        GroupData updateGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(updateGroup.getId())
                .withName("editName")
                .withHeader("editHeader")
                .withFooter("editFooter");
        app.group().modify(group);
        assertEquals(app.group().count(), before.size());
        Groups after = app.group().all();

        assertThat(after, equalTo(before.withOut(updateGroup).withAdded(group)));
    }
}
