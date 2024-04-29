package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoProductCart {
    public SauceDemoProductCart() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(id = "shopping_cart_container")
    public WebElement cartButton;
    @FindBy(xpath = "//button[text()='Remove']")
    public WebElement removeButton;
    @FindBy(id = "continue-shopping")
    public WebElement continueShopping;
    @FindBy(css = "button#checkout")
    public WebElement checkoutButton;


}
