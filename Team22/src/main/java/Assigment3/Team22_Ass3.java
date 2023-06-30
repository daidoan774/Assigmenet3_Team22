package Assigment3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Team22_Ass3 {
    public WebDriver edriver;
    public WebDriverWait ewait;

    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    public WebElement userName;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    public WebElement passW;
    @FindBy(how = How.XPATH, using = "//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")
    public WebElement btnLogin;
    @FindBy(how = How.XPATH, using = "//a[@class='oxd-main-menu-item']/span[text()='My Info']")
    public WebElement btnInfor;

    @FindBy(how = How.XPATH, using = "//label[text()='Employee Full Name']/../following-sibling::div//input[@name='firstName']")
    public WebElement firstname;
    @FindBy(how = How.XPATH, using = "//label[text()='Employee Full Name']/../following-sibling::div//input[@name='middleName']")
    public WebElement midlename;
    @FindBy(how = How.XPATH, using = "//label[text()='Employee Full Name']/../following-sibling::div//input[@name='lastName']")
    public WebElement lastname;
    @FindBy(how = How.XPATH, using = "//label[text()='Employee Id']/../following-sibling::div//input[@class='oxd-input oxd-input--active']")
    public WebElement eloId;
    @FindBy(how = How.XPATH, using = "//label[text()='Nickname']/../following-sibling::div//input[@class='oxd-input oxd-input--active']")
    public WebElement nickName;
    @FindBy(how = How.XPATH, using = "//label[text()='Other Id']/../following-sibling::div//input[@class='oxd-input oxd-input--active']")
    public WebElement otherId;
    @FindBy(how = How.XPATH, using = "//label[text()=\"Driver's License Number\"]/../following-sibling::div//input[@class='oxd-input oxd-input--active']")
    public WebElement driverLicenseNum;
    @FindBy(how = How.XPATH, using = "//label[text()='License Expiry Date']/../following-sibling::div//div[@class=\"oxd-date-wrapper\"]//input[@placeholder=\"dd-mm-yyyy\"]")
    public WebElement licenseExp;
    @FindBy(how = How.XPATH, using = "//label[text()='SSN Number']/../following-sibling::div//input[@class='oxd-input oxd-input--active']")
    public WebElement SSNNum;
    @FindBy(how = How.XPATH, using = "//label[text()='Nationality']/../following-sibling::div//div[@class='oxd-select-text oxd-select-text--active']")
    public WebElement Nationality;
    @FindBy(how = How.XPATH, using = "//label[text()='Marital Status']/../following-sibling::div//div[@class='oxd-select-text oxd-select-text--active']")
    public WebElement maritalStt;
    @FindBy(how = How.XPATH, using = "//label[text()='Date of Birth']/../following-sibling::div//input[@class='oxd-input oxd-input--active']")
    public WebElement DateOfBirth;
    @FindBy(how = How.XPATH, using = "//label[text()='Gender']/../following-sibling::div//div[@class='oxd-radio-wrapper']//input[@type='radio' and @value ='2']")
    public WebElement Gender;
    @FindBy(how = How.XPATH, using = "//label[text()='Military Service']/../following-sibling::div//input[@class='oxd-input oxd-input--active']")
    public WebElement militaryService;
    @FindBy(how = How.XPATH, using = "//label[text()='Smoker']/../following-sibling::div//div//label//input[@type='checkbox']")
    public WebElement Smoker;
    @FindBy(how = How.XPATH, using = "//div[@class='oxd-topbar-header-userarea']/child::ul/child::li//child::span[@class='oxd-userdropdown-tab']")
    public WebElement userArea;

    public Team22_Ass3() {
        edriver = new ChromeDriver();
        PageFactory.initElements(edriver, this);
        ewait = new WebDriverWait(edriver, Duration.ofSeconds(5));
    }

    @Before
    public void setUp() {
        edriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void test() throws Exception {
        enterText(userName, "Admin");
        enterText(passW, "admin123");
        clic(btnLogin);
        clic(btnInfor);

        enterText(firstname, "Automation");
        enterText(midlename, "22");
        enterText(lastname, "5");
        enterText(nickName,"Testing");
        enterText(eloId, "22");
        enterText(otherId, "220");
        enterText(driverLicenseNum,"11111");
        getCeledar(licenseExp);
        enterText(SSNNum,"300");
        getSelect(Nationality,"2");
        getSelect(maritalStt,"1");
        getCeledar(DateOfBirth);
        clic(Gender);
        enterText(militaryService,"1");
        clic(Smoker);

    }

    @After
    public void tearDown() throws InterruptedException {

    }

    public void waitUntilElementVisible(WebElement element) {
        int tryTimes = 0;
        while (tryTimes < 2) {
            try {
                ewait.until(ExpectedConditions.visibilityOf(element));
                break;
            } catch (StaleElementReferenceException se) {
                tryTimes++;
                if (tryTimes == 2)
                    throw se;
            }
        }
    }

    public void clic(WebElement element) {
        waitUntilElementVisible(element);
        element.click();
    }

    public void enterText(WebElement element, String text) {
        waitUntilElementVisible(element);
        if (!element.getAttribute("value").isEmpty()) {
            element.clear();
            element.sendKeys(text);
        } else {
            element.click();
            element.sendKeys(text);
        }
    }
    public void getCeledar(WebElement element){
        element.clear();
        waitUntilElementVisible(element);
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter time = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = localDate.format(time);
        element.sendKeys(formattedDate);
    }
    public void getSelect(WebElement element, String num){
        waitUntilElementVisible(element);
        element.click();
        Select select = new Select(element);
        select.selectByValue(num);
        element.click();
    }

}