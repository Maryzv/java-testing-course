package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().createContact(new ContactData("Тест", "Тестовый", "Проспект теста 25", "3420000", "89131234567", "test@test.ru", "test1@test.ru", "test1"));
  }
}
