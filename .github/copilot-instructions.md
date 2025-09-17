# BotPatternExample - Selenium WebDriver Test Automation

BotPatternExample is a Java 21 Maven project demonstrating the Page Object Pattern with Selenium WebDriver for web application testing. It uses Chrome browser automation to test forms on selenium.dev and generates Allure reports for test results.

**Always reference these instructions first and fallback to search or bash commands only when you encounter unexpected information that does not match the info here.**

## Working Effectively

### Prerequisites and Environment Setup
- Install Java 21 JDK (required): `export JAVA_HOME=/usr/lib/jvm/temurin-21-jdk-amd64 && export PATH=$JAVA_HOME/bin:$PATH`
- Verify Java version: `java -version` - must show Java 21
- Maven 3.6+ is required: `mvn -version`
- Google Chrome browser with ChromeDriver (auto-managed by Selenium)
- Internet connectivity required for tests (accesses selenium.dev test forms)

### Build and Compile
- Clean and compile: `mvn clean compile` - takes ~25 seconds. NEVER CANCEL. Set timeout to 60+ seconds.
- Dependencies download on first run - takes 5-10 minutes initially. NEVER CANCEL.
- Full build including test compilation: `mvn clean test-compile` - takes ~30 seconds. NEVER CANCEL.

### Running Tests
- **CRITICAL**: Tests require headless Chrome configuration in CI environments
- Run all tests: `mvn test` - takes 10-15 minutes including Chrome setup. NEVER CANCEL. Set timeout to 20+ minutes.
- Tests access external site: https://www.selenium.dev/selenium/web/web-form.html
- **Known Issue**: Tests may fail in headless environments without proper Chrome options configuration
- **Workaround**: Tests require the Bot class to be modified with Chrome headless options for CI:
  ```java
  ChromeOptions options = new ChromeOptions();
  options.addArguments("--headless");
  options.addArguments("--no-sandbox");
  options.addArguments("--disable-dev-shm-usage");
  options.addArguments("--disable-gpu");
  this.driver = new ChromeDriver(options);
  ```

### Test Structure and Validation
- **Main test scenarios to validate after changes:**
  1. Form input validation: Navigate to web form, enter text "Selenium", verify input value matches
  2. Form submission workflow: Fill complete form (text, password, textarea), submit, verify "Form submitted" message
- Test classes: `src/test/java/testPackage/BotPatternTests.java`
- Page objects: `src/main/java/pages/form/WebForm.java`, `src/main/java/pages/form/SubmittedForm.java`
- Bot engine: `src/main/java/engine/Bot.java` (core WebDriver wrapper)

### Allure Reporting
- Generate Allure results: `mvn test` (automatically generates to `target/allure-results`)
- Generate and serve Allure report: `mvn io.qameta.allure:allure-maven:serve` - takes 30-60 seconds. NEVER CANCEL.
- Install Allure commandline: `mvn io.qameta.allure:allure-maven:install`

### Linting and Code Quality (Available but not configured)
- Checkstyle: `mvn checkstyle:check` (not configured by default)
- SpotBugs: `mvn com.github.spotbugs:spotbugs-maven-plugin:check` (not configured by default)
- SonarQube: `mvn sonar:sonar` (requires SonarQube server)

## Common Tasks

### Repository Structure
```
.
├── README.md
├── pom.xml                              # Maven build configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── engine/
│   │   │   │   └── Bot.java            # Core WebDriver wrapper
│   │   │   └── pages/
│   │   │       └── form/
│   │   │           ├── WebForm.java    # Page object for input form
│   │   │           └── SubmittedForm.java # Page object for result page
│   │   └── resources/
│   │       └── log4j2.json            # Logging configuration
│   └── test/
│       └── java/
│           └── testPackage/
│               └── BotPatternTests.java # Main test cases
└── target/                             # Build artifacts (excluded from git)
```

### Key Dependencies (pom.xml)
- Java 21 (maven.compiler.source/target)
- Selenium WebDriver 4.35.0
- JUnit Jupiter 5.13.4
- Allure reporting 2.29.0
- Log4j 2.25.1
- Jackson (for JSON log4j2 configuration)

### Frequent Commands Output
```bash
# Build status check
mvn clean compile
# Expected: BUILD SUCCESS in ~25 seconds

# Run tests  
mvn test
# Expected: 2 tests, may fail in CI without headless Chrome config
# Time: 10-15 minutes total including Chrome setup

# Check dependencies
mvn dependency:tree
# Shows all transitive dependencies

# Java version check
java -version
# Must show: openjdk version "21.x.x"
```

## Working with the Code

### Bot Pattern Architecture
- `Bot.java`: Core WebDriver abstraction with fluent API
- Page Objects: Encapsulate page-specific logic and locators
- Tests: Use Page Objects through Bot instance for clean separation

### Making Changes
- **Always validate changes by running the complete test suite**
- **Before committing**: Verify both test scenarios pass:
  1. Text input/validation test
  2. Form submission workflow test
- **Chrome Configuration**: When modifying Bot.java, preserve Chrome options for CI compatibility
- **Logging**: All actions are logged via Log4j2, check `logs/app.log` for debugging

### Debugging Test Failures
- Chrome startup issues: Check headless options configuration in Bot.java
- Network connectivity: Verify access to selenium.dev
- Element not found: Check if webpage structure changed on selenium.dev test form
- Timing issues: WebDriver waits may need adjustment for slower environments

### Performance Expectations
- **NEVER CANCEL** builds or tests - timing is critical for proper setup
- Initial dependency download: 5-10 minutes (first time only)
- Clean compile: 25-30 seconds
- Test execution: 10-15 minutes (includes Chrome startup and web navigation)
- Allure report generation: 30-60 seconds

## Validation Scenarios

**ALWAYS run these scenarios after making changes to ensure functionality:**

1. **Text Input Validation**: 
   - Navigate to selenium.dev web form
   - Enter "Selenium" in text input field  
   - Verify input value equals "Selenium"

2. **Complete Form Submission**:
   - Navigate to selenium.dev web form
   - Fill text input: "Selenium"
   - Fill password: "password"  
   - Fill textarea: "This is my comment\nwith a new line"
   - Click submit button
   - Verify result page shows "Form submitted" header

These scenarios validate the entire automation framework is working correctly.