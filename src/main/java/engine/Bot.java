package engine;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Bot {
    private WebDriver driver;
    Logger logger = LogManager.getLogger(Bot.class);

    public Bot(String browserType) {
        this.initialize(browserType);
    }

    @Step("Initialize the browser: `{browserType}`")
    private void initialize(String browserType){
        if (browserType.equalsIgnoreCase("chrome")) {
            this.driver = new ChromeDriver();
            logger.info("Chrome browser started");
        } else {
            logger.error("Chrome browser not started");
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    @Step("Quit the browser")
    public void quit() {
        if (this.driver != null) {
            this.driver.quit();
            logger.info("Browser quit");
        }
    }

    @Step("Navigate to `{url}`")
    public Bot navigateTo(String url) {
        this.driver.navigate().to(url);
        logger.info("Navigate to: {}", url);
        return this;
    }

    @Step("Type text `{text}` into element located by `{locator}`")
    public Bot typeText(By locator, String text) {
        this.driver.findElement(locator).sendKeys(text);
        logger.info("Type text `{}` into element located by `{}`", text, locator);
        return this;
    }

    @Step("Click on element located by `{locator}`")
    public Bot click(By locator) {
        this.driver.findElement(locator).click();
        logger.info("Click on element located by `{}`", locator);
        return this;
    }

    @Step("Get DOM property `{property}` of element located by `{locator}`")
    public String getDomProperty(By locator, String property) {
        var actualValue = this.driver.findElement(locator).getDomProperty(property);
        logger.info("Get DOM property `{}` of element located by `{}`: `{}`", property, locator, actualValue);
        return actualValue;
    }

    @Step("Get text of element located by `{locator}`")
    public String getText(By locator) {
        var actualValue = this.driver.findElement(locator).getText();
        logger.info("Get text of element located by `{}`: `{}`", locator, actualValue);
        return actualValue;
    }

}
