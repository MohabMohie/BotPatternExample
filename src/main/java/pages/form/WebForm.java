package pages.form;

import engine.Bot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class WebForm {
    private final Bot bot;
    private static final By testInput = By.id("my-text-id");
    private static final By passwordInput = By.name("my-password");
    private static final By textAreaInput = By.name("my-textarea");
    private static final By submitButton = By.cssSelector("button[type='submit']");

    public WebForm(Bot bot) {
        this.bot = bot;
    }

    @Step("Navigate to Web Form page")
    public WebForm navigateTo() {
        bot.navigateTo("https://www.selenium.dev/selenium/web/web-form.html");
        return this;
    }

    @Step("Fill the form with text: {text}, password: {password}, comment: {comment}, submit: {submit}")
    public WebForm fillForm(String text, String password, String comment, boolean submit) {
        bot.typeText(testInput, text)
                .typeText(passwordInput, password)
                .typeText(textAreaInput, comment);
        if (submit) bot.click(submitButton);
        return this;
    }

    @Step("Get the value from the input field")
    public String getInputValue() {
        return bot.getDomProperty(testInput, "value");
    }

}
