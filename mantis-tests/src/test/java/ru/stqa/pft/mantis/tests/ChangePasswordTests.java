package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        app.login().login("administrator", "root");
        app.settings().goToSettings();
        app.settings().selectUserManagementTab();
        app.settings().openLastUserPage();

        String user = app.settings().getUsernameOnUserPage();
        String email = app.settings().getEmailOnUserPage();
        String newPassword = "newpassword";

        app.settings().resetUserPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);

        String resetPasswordLink = findResetPasswordLink(mailMessages, email);
        app.user().editPasswordByLink(resetPasswordLink, newPassword);

        assertTrue(app.newSession().login(user, newPassword));
    }

    private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
