package ui.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogSignUp {

	WebDriver driver;
	@FindBy(partialLinkText="Trello")
	WebElement Logo;
	
	@FindBy(xpath="//*[@id='hero']/div/div/div[2]/form/div[1]/input")
	WebElement SignupemailBox;
	
	@FindBy(xpath="//*[@id='hero']/div/div/div[2]/form/div[2]/button")
	WebElement FreeSignupBtn;
	
	@FindBy(id="displayName")
	WebElement fullNm;
	
	@FindBy(id="password")
	WebElement pass;
	
	@FindBy(id="signup-submit")
	WebElement SignupBtn;

	@FindBy(id="login-submit")
	WebElement LoginContinue;

	@FindBy(linkText = "Log in")
	WebElement SignInlink;

	@FindBy(id = "user")
	WebElement useremail;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(id = "login")
	WebElement submit;
	@FindBy(id = "login-submit")
	WebElement Loginsubmit;

	public LogSignUp (WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		//if(!Logo.isDisplayed())
		//	throw new IllegalStateException("This is Not Home Page..!");
	}

	public void clicksignlink()
	{
		SignInlink.click();
	}

	public void setUseremail(String useremailid)
	{
		useremail.sendKeys(useremailid);
	}

	public void setUserpass(String userpass)
	{
		password.sendKeys(userpass);
	}

	public void clicksubmit()
	{
		submit.click();
	}

	public void Clickloginsubmit()
	{
		Loginsubmit.click();
	}

	public WebElement validpassfield()
	{
		return password;
	}

	public void clickfreesignup()
	{
		FreeSignupBtn.click();
	}
	public void clickonContinue()
	{
		LoginContinue.click();
	}
	public void clicksignup()
	{
		SignupBtn.click();
	}
	
	public void setMailText(String emailText)
	{
		SignupemailBox.sendKeys(emailText);
	}
	public void setPassword(String Pass)
	{
		pass.sendKeys(Pass);
	}
	public void setFullnm(String FullName)
	{
		fullNm.sendKeys(FullName);
	}
}
