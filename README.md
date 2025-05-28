# Instagram Automation Testing Project

This project contains automated test suites for Instagram's web interface using Selenium WebDriver and TestNG. The tests cover various functionalities including login, search, suggestions, page view, and window handling.

## Project Structure

```
src/
├── main/java/com/Instagram/
│   ├── base/
│   │   └── Base.java                 # Base class with common WebDriver setup
│   ├── login/
│   │   └── Login.java               # Login functionality
│   ├── search/
│   │   └── Search.java              # Search functionality
│   ├── suggest/
│   │   └── Suggest.java             # Suggestions functionality
│   ├── pageview/
│   │   └── PageView.java            # Page view and scrolling functionality
│   ├── window/
│   │   └── WindowHandler.java       # Window/tab handling functionality
│   └── util/
│       └── TestListener.java        # TestNG listener for reporting
└── test/java/com/Instagram/
    ├── loginTest/
    │   └── LoginTest.java           # Login test cases
    ├── searchTest/
    │   └── SearchTest.java          # Search test cases
    ├── suggestTest/
    │   └── SuggestTest.java         # Suggestions test cases
    ├── pageviewTest/
    │   └── PageViewTest.java        # Page view test cases
    └── windowTest/
        └── WindowHandlerTest.java   # Window handling test cases
```

## Features

- **Login Testing**
  - Valid login
  - Invalid credentials
  - Empty fields
  - Special characters handling

- **Search Functionality**
  - Basic search
  - Hashtag search
  - Location search
  - Search results verification
  - Special characters and edge cases

- **Suggestions**
  - Explore page navigation
  - Suggested accounts retrieval
  - Follow/unfollow functionality
  - Multiple account handling

- **Page View**
  - Scrolling (up/down/to bottom/to top)
  - Zoom in/out
  - Page title verification
  - Element scrolling

- **Window Handling**
  - New window/tab management
  - Window switching
  - Window count verification
  - Window sequence operations

## Prerequisites

- Java JDK 8 or higher
- Maven
- Chrome/Firefox browser
- WebDriver (ChromeDriver/GeckoDriver)

## Dependencies

- Selenium WebDriver
- TestNG
- ExtentReports
- WebDriverManager

## Setup

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Navigate to project directory:
```bash
cd QAproject
```

3. Install dependencies:
```bash
mvn clean install
```

## Configuration

1. Update `src/test/resources/config.properties` with your Instagram credentials:
```properties
username=your_username
password=your_password
```

2. Configure browser settings in `src/test/resources/browser.properties`:
```properties
browser=chrome
headless=false
```

## Running Tests

### Run all tests
```bash
mvn test
```

### Run specific test suite
```bash
mvn test -Dsuite=Instagram_Test_Suite
```

### Run specific test class
```bash
mvn test -Dtest=LoginTest
```

## Test Reports

- TestNG reports are generated in `test-output/` directory
- ExtentReports are generated in `test-output/ExtentReport.html`

## Best Practices

1. **Page Object Model**: Each page functionality is encapsulated in its own class
2. **Base Class**: Common WebDriver setup and utilities in Base class
3. **Test Listeners**: Custom listeners for reporting and screenshots
4. **Error Handling**: Comprehensive try-catch blocks with meaningful error messages
5. **Cleanup**: Proper browser cleanup after each test

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For any queries or suggestions, please open an issue in the repository. 