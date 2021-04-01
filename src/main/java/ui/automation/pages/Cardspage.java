package ui.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ARP_System on 3/28/2021.
 */
public class Cardspage {

    WebDriver driver;

    @FindBy(linkText = "trello")
    WebElement BoardLink;

    @FindBy(linkText = "Boards")
    WebElement Boardlogo;

    @FindBy(xpath = "//*[@id='board']/div[1]/div/div[2]/div/div[1]/div/textarea")
    WebElement textArea;

    @FindBy(xpath ="//*[@class='cc-controls u-clearfix']/div/input")
    WebElement Addcard;

    public Cardspage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        if(!Boardlogo.isDisplayed())
            throw new IllegalStateException("This is Not Board Page..!");
    }
    public void clickonAddcard()
    {Addcard.click();}
    public void setTextArea(String txt)
    {
        textArea.sendKeys(txt);
    }
    public void clickonboard()
    {
        BoardLink.click();
    }



}
