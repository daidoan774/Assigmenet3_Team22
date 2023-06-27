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

    public Team22_Ass3(){
        edriver = new ChromeDriver();
        PageFactory.initElements(edriver, this);
        ewait = new WebDriverWait(edriver, Duration.ofSeconds(5));
    }
    @Before
    public void setUp(){
        edriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @Test
    public void test() throws Exception{
        waitUntilElementVisible(userName);
        waitUntilElementVisible(passW);
        userName.sendKeys("Admin");
        passW.sendKeys("admin123");
        btnLogin.click();

    }

    @After
    public void tearDown() throws InterruptedException{
    }
    public void waitUntilElementVisible(WebElement element){
        int tryTimes = 0;
        while (tryTimes < 2){
            try {
                ewait.until(ExpectedConditions.visibilityOf(element));
                break;
            }
            catch (StaleElementReferenceException se){
                tryTimes ++;
                if (tryTimes == 2)
                    throw se;
            }
        }
    }
}

