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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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


}

