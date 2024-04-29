package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoOverviewPage {
    public SauceDemoOverviewPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(css = "button#cancel")
    public WebElement cancelButton;
    @FindBy(css = "button#finish")
    public WebElement finishButton;
}
