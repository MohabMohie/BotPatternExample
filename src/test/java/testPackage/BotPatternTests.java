package testPackage;

import engine.Bot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.form.SubmittedForm;
import pages.form.WebForm;

public class BotPatternTests {
    private Bot bot;

    @Test
    public void typeAndConfirmTextWasTypedCorrectly() {
        var actualText = new WebForm(bot)
                .navigateTo()
                .fillForm("Selenium", "", "", false)
                .getInputValue();
        Assertions.assertEquals("Selenium", actualText);
    }

    @Test
    public void submitFormAndConfirmItWasSubmittedCorrectly() {
        new WebForm(bot).navigateTo().fillForm("Selenium", "password", "This is my comment\nwith a new line", true);
        var actualMessage = new SubmittedForm(bot).getHeaderText();
        Assertions.assertEquals("Form submitted", actualMessage);
    }

    @BeforeEach
    public void setUp() {
        bot = new Bot("chrome");
    }

    @AfterEach
    public void tearDown() {
        bot.quit();
    }
}
