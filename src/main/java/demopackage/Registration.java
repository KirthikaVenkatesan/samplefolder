package demopackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Registration extends webAction {
	
	//locators
	@FindBy(linkText="Insurance Project")
	public static WebElement insurance;
	@FindBy(linkText="Register")
	public static WebElement regbtn;
	@FindBy(id="user_title")
	public static WebElement title;
	@FindBy(name="firstname")
	public static WebElement name;
	@FindBy(name="lastname")
	public static WebElement surname;
	@FindBy(name="phone")
	public static WebElement phone_no;
	@FindBy(name="year")
	public static WebElement year;
	@FindBy(name="month")
	public static WebElement month;
	@FindBy(name="date")
	public static WebElement date;
	@FindBy(xpath="//input[@value='Full']")
	public static WebElement rdibtn;
	@FindBy(name="licenceperiod")
	public static WebElement license;
	@FindBy(id="user_occupation_id")
	public static WebElement occupation;
	@FindBy(name="street")
	public static WebElement street;
	@FindBy(name="city")
	public static WebElement city;	
	@FindBy(name="county")
	public static WebElement country;
	@FindBy(name="post_code")
	public static WebElement pin;
	@FindBy(name="email")
	public static WebElement mail;
	@FindBy(name="password")
	public static WebElement pwd;
	@FindBy(name="c_password")
	public static WebElement cnfrm_pwd;
	@FindBy(id="resetform")
	public static WebElement reset;
	
public void register() throws InterruptedException {
	
	try {
	insurance.click();
	Thread.sleep(3000);
	regbtn.click();
	Select dropdown=new Select(title);
	dropdown.selectByVisibleText("Mr");

	name.sendKeys(excelHashMap.get("First_Name"));
	surname.sendKeys(excelHashMap.get("Sur_Name"));
	phone_no.sendKeys(excelHashMap.get("Mobile_no").replace("\"", ""));
	Select dropdown1=new Select(year);
	dropdown1.selectByValue("1990");
	Select dropdown2=new Select(month);
	dropdown2.selectByValue("12");
	Select dropdown3=new Select(date);
	dropdown3.selectByValue("21");
	rdibtn.click();
	Select dropdown4=new Select(license);
	dropdown4.selectByVisibleText("2");
	Select dropdown5=new Select(occupation);
	dropdown5.selectByVisibleText("Writer");
	street.sendKeys(excelHashMap.get("Street_Name"));
	city.sendKeys(excelHashMap.get("City_Name"));
	country.sendKeys(excelHashMap.get("Country_Name"));
	mail.sendKeys(excelHashMap.get("Mail_Id"));
	pwd.sendKeys(excelHashMap.get("Password"));
	cnfrm_pwd.sendKeys(excelHashMap.get("Confirm_Pwd"));
	Thread.sleep(2000);
	reset.click();
	driver.navigate().back();
	
}catch(Exception e) {
	e.printStackTrace();
	Assert.fail("Registration has not completed successfully");
}
	
}



}
