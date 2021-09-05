package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SettingsHelper extends HelperBase {

    public SettingsHelper(ApplicationManager app) {
        super(app);
    }

    public void goToSettings() {
        click(By.xpath("//div[@id='sidebar']//li[7]"));
    }

    public void selectUserManagementTab() {
        click(By.xpath("//div[@class='page-content']//li[2]"));
    }

    public void openLastUserPage() {
        click(By.xpath("//div[@class='table-responsive']//tbody//tr[last()]/td/a"));
    }

    public void resetUserPassword() {
        click(By.xpath("//form[@id='manage-user-reset-form']//input[@type='submit']"));
    }

    public String getUsernameOnUserPage() {
        return value(By.xpath("//input[@id='edit-username']"));
    }

    public String getEmailOnUserPage() {
        return value(By.xpath("//input[@id='email-field']"));
    }
}
