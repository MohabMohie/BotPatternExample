# 🤖 BotPatternExample

[![Maven Central](https://img.shields.io/maven-central/v/com.github.MohabMohie/BotPatternExample.svg)](https://search.maven.org/artifact/com.github.MohabMohie/BotPatternExample)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.35.0-green.svg)](https://selenium.dev/)

A modern, fluent, and maintainable test automation framework demonstrating the **Bot Pattern** - an evolution of the Page Object Model that provides cleaner, more readable, and chainable test code for web applications.

## 🎯 What is the Bot Pattern?

The Bot Pattern is an innovative approach to test automation that combines the best practices of the Page Object Model with fluent interfaces and method chaining. Instead of traditional page objects, we create "Bot" classes that encapsulate both actions and assertions, making tests more readable and maintainable.

**Traditional Page Object:**
```java
// Traditional approach - verbose and scattered
LoginPage loginPage = new LoginPage(driver);
loginPage.enterUsername("testuser");
loginPage.enterPassword("password123");
loginPage.clickSubmit();
DashboardPage dashboard = new DashboardPage(driver);
Assert.assertEquals("Welcome!", dashboard.getWelcomeMessage());
```

**Bot Pattern:**
```java
// Bot Pattern - fluent and expressive
new WebForm(bot)
    .navigateTo()
    .fillForm("testuser", "password123", "comment", true);
    
String message = new SubmittedForm(bot).getHeaderText();
Assert.assertEquals("Form submitted", message);
```

## ✨ Features

- **🎭 Fluent Interface**: Method chaining for more readable test code
- **📝 Built-in Logging**: Comprehensive logging with Log4j2 for debugging
- **📊 Allure Integration**: Beautiful test reports with step-by-step execution details
- **🧪 JUnit 5 Support**: Modern testing framework with latest features
- **🔧 Maven Build**: Easy dependency management and build automation
- **🌐 Cross-browser Ready**: Extensible architecture for multiple browser support
- **📦 Clean Architecture**: Separation of concerns with engine and page layers
- **🔍 Element Location**: Multiple locator strategies (ID, Name, CSS, XPath)
- **⚡ Method Chaining**: Fluent API for seamless action composition

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Core programming language |
| **Apache Maven** | 3.6+ | Build automation and dependency management |
| **Selenium WebDriver** | 4.35.0 | Browser automation |
| **JUnit 5** | 5.13.4 | Testing framework |
| **Allure** | 2.29.0 | Test reporting and documentation |
| **Log4j2** | 2.25.1 | Logging framework |
| **Jackson** | 2.17.1 | JSON processing for configuration |

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 21** or higher
  ```bash
  java -version
  # Should show: openjdk version "21.0.x" or higher
  ```

- **Apache Maven 3.6+**
  ```bash
  mvn -version
  # Should show: Apache Maven 3.6.x or higher
  ```

- **Google Chrome Browser** (for default configuration)
  - ChromeDriver is automatically managed by Selenium WebDriver

## 🚀 Getting Started

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/MohabMohie/BotPatternExample.git
   cd BotPatternExample
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Compile the project**
   ```bash
   mvn compile
   ```

4. **Run the tests**
   ```bash
   mvn test
   ```

5. **Generate Allure reports** (optional)
   ```bash
   mvn allure:report
   mvn allure:serve
   ```

### Usage

#### Basic Bot Usage

```java
import engine.Bot;
import pages.form.WebForm;

// Initialize a bot with Chrome browser
Bot bot = new Bot("chrome");

// Navigate and interact with a web form
String inputValue = new WebForm(bot)
    .navigateTo()
    .fillForm("Sample Text", "password123", "Test comment", false)
    .getInputValue();

// Clean up
bot.quit();
```

#### Complete Test Example

```java
@Test
public void submitFormAndVerifySuccess() {
    // Arrange
    Bot bot = new Bot("chrome");
    
    // Act
    new WebForm(bot)
        .navigateTo()
        .fillForm("TestUser", "securePass", "Integration test comment", true);
    
    // Assert
    String confirmationMessage = new SubmittedForm(bot).getHeaderText();
    Assertions.assertEquals("Form submitted", confirmationMessage);
    
    // Cleanup
    bot.quit();
}
```

#### Creating Custom Page Objects

```java
public class MyCustomPage {
    private final Bot bot;
    private static final By submitButton = By.id("submit-btn");
    
    public MyCustomPage(Bot bot) {
        this.bot = bot;
    }
    
    @Step("Submit the custom form")
    public MyCustomPage submit() {
        bot.click(submitButton);
        return this;
    }
    
    @Step("Get confirmation text")
    public String getConfirmation() {
        return bot.getText(By.className("confirmation"));
    }
}
```

#### Advanced Bot Operations

```java
// Method chaining for complex workflows
bot.navigateTo("https://example.com")
   .typeText(By.id("username"), "testuser")
   .typeText(By.id("password"), "password")
   .click(By.id("login-btn"));

// Extract data from elements
String pageTitle = bot.getText(By.tagName("h1"));
String inputValue = bot.getDomProperty(By.id("my-input"), "value");
```

## 🧪 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=BotPatternTests
```

### Run with Allure Report Generation
```bash
mvn clean test allure:report
mvn allure:serve
```

### Debug Mode
```bash
mvn test -Dmaven.surefire.debug
```

## 📸 Visual Examples

> **Note**: Include screenshots or GIFs here showing:
> - Chrome browser executing tests
> - Allure report dashboard
> - Test execution console output
> - Example web form interactions

## 🤝 Contributing

We welcome contributions! Here's how you can help improve this project:

### Reporting Bugs
1. Check existing [issues](https://github.com/MohabMohie/BotPatternExample/issues)
2. Create a new issue with:
   - Clear bug description
   - Steps to reproduce
   - Expected vs actual behavior
   - Environment details (OS, Java version, browser)

### Suggesting Features
1. Open a [feature request](https://github.com/MohabMohie/BotPatternExample/issues/new)
2. Describe the feature and its benefits
3. Provide examples or mockups if applicable

### Submitting Pull Requests
1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Make your changes and add tests
4. Run tests: `mvn test`
5. Commit with clear message: `git commit -m 'Add amazing feature'`
6. Push to your fork: `git push origin feature/amazing-feature`
7. Open a Pull Request

### Development Guidelines
- Follow existing code style and patterns
- Add tests for new functionality
- Update documentation for API changes
- Ensure all tests pass before submitting

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2024 Mohab Mohie

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

## 📞 Contact & Support

- **GitHub Issues**: [Report bugs or request features](https://github.com/MohabMohie/BotPatternExample/issues)
- **Discussions**: [Community discussions and Q&A](https://github.com/MohabMohie/BotPatternExample/discussions)
- **Email**: For private inquiries, contact the repository owner
- **Documentation**: Check the [Wiki](https://github.com/MohabMohie/BotPatternExample/wiki) for detailed guides

### Getting Help
1. Check the [FAQ section](https://github.com/MohabMohie/BotPatternExample/wiki/FAQ)
2. Search [existing issues](https://github.com/MohabMohie/BotPatternExample/issues)
3. Join [community discussions](https://github.com/MohabMohie/BotPatternExample/discussions)
4. Create a new issue with the `question` label

## 🙏 Acknowledgments

- **Selenium WebDriver Team** - For the robust browser automation framework
- **Allure Framework** - For beautiful and informative test reporting
- **JUnit Team** - For the excellent testing framework
- **Apache Maven** - For reliable build automation
- **Test Automation Community** - For inspiration and best practices
- **Contributors** - Everyone who has contributed to making this project better

### Inspiration
This project was inspired by the need for more maintainable and readable test automation code. Special thanks to the test automation community for sharing knowledge about design patterns and best practices.

---

⭐ **If you find this project helpful, please give it a star!** ⭐

*Made with ❤️ for the test automation community*