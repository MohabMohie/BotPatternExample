package pages.form;

import engine.Bot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SubmittedForm {
    private final Bot bot;
    private static final By headerText = By.tagName("h1");

    public SubmittedForm(Bot bot) {
        this.bot = bot;
    }

    @Step("Get the header text from the submitted form page")
    public String getHeaderText() {
        return bot.getText(headerText);
    }
}
