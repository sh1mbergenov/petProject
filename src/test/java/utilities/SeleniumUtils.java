package utilities;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class SeleniumUtils {
    public static void switchToNewTab(WebDriver driver, WebElement element){
        String mainWindowHandle = driver.getWindowHandle();
        String url = driver.getCurrentUrl();
        element.click();
        System.out.println(" url before click : " + url);
        Set<String> windows = driver.getWindowHandles(); //mainWindow
        for (String windowHandle:windows){
            if(!windowHandle.equals(mainWindowHandle)){
                driver.switchTo().window(windowHandle);
            }
        }
        System.out.println("Currently, the driver is on: " + driver.getCurrentUrl());

    }

    /**
     * This method waits for element to be clickable , before clicking on it
     * @param driver - is used to open web application
     * @param element - to be clicked
     */
    public static void click(WebDriver driver,WebElement element){
        FluentWait wait = new FluentWait(driver)
                .ignoring(ElementClickInterceptedException.class)
                .withTimeout(Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * This method accepts alert , if alert is not there
     * it ignores the exception
     * @param driver
     */
    public static void acceptAlert(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        try {
            driver.switchTo().alert().accept();
        }
        catch (NoAlertPresentException e){
            System.out.println("Alert does not exist!");
            e.printStackTrace();
        }
    }
    public static void dismissAlert(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        try {
            driver.switchTo().alert().dismiss();
        }
        catch (NoAlertPresentException e){
            System.out.println("Alert does not exist!");
            e.printStackTrace();
        }
    }

    /**
     * This method returns true if element is present in the DOM
     * @param driver
     * @param locator
     * @return
     */
    public static boolean isElementPresent(WebDriver driver , By locator){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        try {
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * This method will try to click on element multiple times
     * by ignoring StaleElement exception
     * @param driver
     * @param locator
     * @param retries
     */

    public static void clickWithRetries(WebDriver driver , By locator , int retries) {
        int numOfTrials = 0;
        while (numOfTrials<retries){
            try {
                WebElement element = driver.findElement(locator);
                element.click();
                return; // if it is successful exit the method
            } catch (StaleElementReferenceException e) {
                // it is not successful , try again
                numOfTrials++;
                waitForSeconds(1);
            }
            catch (NoSuchElementException e){
                System.out.println("Locator is wrong!");
                e.printStackTrace();
                return;
            }
        }
    }

    public static void waitForSeconds(int numberOfSeconds){
        try {
            Thread.sleep(numberOfSeconds*1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * This method waits for text to appear in the element
     * then verifies if it matches with expected text
     * @param driver takes to the page
     * @param numOfSec to wait for
     * @param element to verify
     * @param text to verify
     */
    public static void verifyTextInElement(WebDriver driver,int numOfSec,WebElement element,String text){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(numOfSec));
        wait.until(ExpectedConditions.textToBePresentInElement(element,text));
        Assert.assertEquals(text,element.getText());
    }
    public static void cleanAndSendKeys(WebDriver driver,WebElement element, String text){
        Actions actions = new Actions(driver);
        actions.keyDown(element, Keys.COMMAND).sendKeys("a");
        actions.keyUp(element, Keys.COMMAND);
        actions.keyDown(element, Keys.BACK_SPACE);
        actions.keyUp(element, Keys.BACK_SPACE);
        actions.build().perform();
        element.sendKeys(text);
    }

}
