package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoCompletePage {
    public SauceDemoCompletePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//h2[text()='Thank you for your order!']")
    public WebElement confirmation;
    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

}
