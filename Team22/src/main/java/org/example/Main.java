package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;

public class Main {
    public static ChromeDriver driver = new ChromeDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    public static void main(String[] args) throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        getTex("//input[@name='username']", "Admin");
        getTex("//input[@name='password']", "admin123");
        click("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']");

        click("//a[@class='oxd-main-menu-item']/span[text()='My Info']");
//        clea("//label[text()='Employee Full Name']/../following-sibling::div//input[@name='firstName']");
//        clea("//label[text()='Employee Full Name']/../following-sibling::div//input[@name='middleName']");
//        clea("//label[text()='Employee Full Name']/../following-sibling::div//input[@name='lastName']");

        getTex("//label[text()='Employee Full Name']/../following-sibling::div//input[@name='firstName']", "Automation");
        getTex("//label[text()='Employee Full Name']/../following-sibling::div//input[@name='middleName']", "22");
        getTex("//label[text()='Employee Full Name']/../following-sibling::div//input[@name='lastName']", "5");

        getTex("//label[text()='Nickname']/../following-sibling::div//input[@class='oxd-input oxd-input--active']", "Testing");
        getTex("//label[text()='Employee Id']/../following-sibling::div//input[@class='oxd-input oxd-input--active']","22");
        getTex("//label[text()='Other Id']/../following-sibling::div//input[@class='oxd-input oxd-input--active']","220");
        getTex("//label[text()=concat('Driver', \"'\", 's License Number')]/../following-sibling::div//input[@class='oxd-input oxd-input--active']", "11111");
        getdaTe("//label[text()='License Expiry Date']/../following-sibling::div//input[@placeholder='yyyy-mm-dd']");
        getTex("//label[text()='SSN Number']/../following-sibling::div//input[@class='oxd-input oxd-input--active']","300");
        getSelect("//label[text()='Nationality']/../following::div//div[@class='oxd-select-text oxd-select-text--active']","Afghan");
        getSelect("//label[text()='Marital Status']/../following::div//div[@class='oxd-select-wrapper']//div[@class='oxd-select-text oxd-select-text--active']//div[@clear='false']","Single");
        getdaTe("//label[text()='Date of Birth']/../following-sibling::div//input[@class='oxd-input oxd-input--active']");
        getRadio("//label[text()='Gender']/../following-sibling::div//div[@class='oxd-radio-wrapper']//input[@type='radio' and @value ='1']");
        getTex("//label[text()='Military Service']/../following-sibling::div//input[@class='oxd-input oxd-input--active']","1");
        click("//div[@class='oxd-checkbox-wrapper']//input[@type='checkbox']");
//        click("//div[@class='oxd-topbar-header-userarea']/child::ul/child::li//child::span[@class='oxd-userdropdown-tab']");
    }


    public static void click(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void getTex(String xpath, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        while (element.getAttribute("value").isEmpty()) {
            element.sendKeys(text);
        }
        element.clear();
    }
    public static void getdaTe(String xpath)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(time);
        element.clear();
        element.sendKeys(formattedDate);
    }

    public static void getSelect(String xpath,String num) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        Select select = new Select(element);
        select.selectByVisibleText(num);
    }
    public static void getRadio(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement radioBtn = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(radioBtn));

        String value = radioBtn.getAttribute("value");
        boolean isSelected = radioBtn.isSelected();

        if (!isSelected || !value.equals("1")) {
            radioBtn.click();
        }

    }

}