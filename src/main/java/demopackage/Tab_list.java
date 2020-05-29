package demopackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameWork.Log;

public class Tab_list extends webAction{

	//locators

	@FindBy(xpath="//a[text()='Home']")
	public static WebElement home_tab;
	@FindBy(xpath="//a[text()='Request Quotation']")
	public static WebElement request_tab;
	@FindBy(xpath="//a[text()='Retrieve Quotation']")
	public static WebElement retrieve_tab;
	@FindBy(xpath="//a[text()='Profile']")
	public static WebElement profile_tab;
	@FindBy(xpath="//a[text()='Edit Profile']")
	public static WebElement editprofile_tab;
	@FindBy(xpath="//h2[contains(text(),'Broker Insurance WebPage')]")
	public static WebElement home_validation;
	@FindBy(xpath="//h2[contains(text(),'Request a quotation')]")
	public static WebElement req_validation;
	@FindBy(id="getquote")
	public static WebElement retrieve_validation;
	@FindBy(xpath="//strong[text()='Title:']")
	public static WebElement profile_validation;
	@FindBy(xpath="//h1[contains(text(),'Editing')]")
	public static WebElement editprofile_validation;
	
	
	
	public WebElement elements(String dynamicElement) {
		
			
	WebElement home1=driver.findElement(By.xpath("//a[text()=" +dynamicElement+ "]"));
	return home1;
	}
	
	//home tab existence
	
	/*public void validateTab(WebElement tabName,String expTextToBeDisplayed)
	{
		
		switch case (tabName)
		{
		
		cas("Home")
				element="home_tab"
		case("Re Qua"
				elem)= "qu_tab'"
		}
		
		if (element.isDisplayed()) {

			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(home_tab));
			if(wait.until(ExpectedConditions.elementToBeClickable(home_tab)) != null) {	
				home_tab.click();
				String val_home=home_validation.getText();
				System.out.println(val_home);
				if(home_validation.isDisplayed()) {
					message="Home tab is verified";
					Log.getReport(message);
				}


			}
		}
		else {
			Assert.fail("Home_tab validation is not completed successfully");

		}

			
	}
	*/
	
/*public void validateTab_new(String tabName,WebElement valElement,String expTextToBeDisplayed,String msg) throws InterruptedException {
		
		Thread.sleep(3000);

		if (elements(tabName).isDisplayed()) {

			WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement index= wait.until(ExpectedConditions.elementToBeClickable(tabName));
			if(index != null) {	
				tabName.click();
				String val_text=tabelement.getText();
				System.out.println("Text name= " +val_text);
				if(expTextToBeDisplayed.contains(val_text)) {
					
					message= " " + msg +" is verified successfully";
					Log.getReport(message);
				}


			}
		}
		else {
			Assert.fail(" "+msg+ " validation is not completed successfully");

		}

	}

		
		
	}*/

	public void validateTab(WebElement tabName,WebElement valElement,String expTextToBeDisplayed,String msg) throws InterruptedException {
		
		Thread.sleep(3000);

		if (tabName.isDisplayed()) {

			WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement index= wait.until(ExpectedConditions.elementToBeClickable(tabName));
			if(index != null) {	
				tabName.click();
				String val_text=valElement.getText();
				System.out.println("Text name= " +val_text);
				if(expTextToBeDisplayed.contains(val_text)) {
					
					message= " " + msg +" is verified successfully";
					Log.getReport(message);
				}


			}
		}
		else {
			Assert.fail(" "+msg+ " validation is not completed successfully");

		}

	}

		
		
	}
	
	
	
	/*public void tab_home() throws InterruptedException {

		Thread.sleep(3000);

		if (home_tab.isDisplayed()) {

			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(home_tab));
			if(wait.until(ExpectedConditions.elementToBeClickable(home_tab)) != null) {	
				home_tab.click();
				String val_home=home_validation.getText();
				System.out.println(val_home);
				if(home_validation.isDisplayed()) {
					message="Home tab is verified";
					Log.getReport(message);
				}


			}
		}
		else {
			Assert.fail("Home_tab validation is not completed successfully");

		}

	}

	//Request tab existence

	public void tab_req() throws InterruptedException {
		Thread.sleep(3000);

		if(request_tab.isDisplayed()) {
			WebDriverWait wait=new WebDriverWait(driver, 10);
			WebElement waitIndex=wait.until(ExpectedConditions.elementToBeClickable(request_tab));

			if(waitIndex != null) {

				request_tab.click();
				String val_req=req_validation.getText();
				System.out.println(val_req);
				if(req_validation.isDisplayed()) {
					message="Request_tab is verified";
					Log.getReport(message);
				}


			}
		}
		else {
			Assert.fail("Request validation is not completed successfully");
		}
	}
	//Retrieve tab existence

	public void tab_retrieve() throws InterruptedException {

		Thread.sleep(3000);

		if(retrieve_tab.isDisplayed()) {
			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(retrieve_tab));

			if(wait.until(ExpectedConditions.elementToBeClickable(retrieve_tab)) != null) {

				retrieve_tab.click();
				System.out.println("Verfication: Entered into retrive if condition");
				if(retrieve_validation.isDisplayed()) {
					message="Retrieve_tab is verified";
					Log.getReport(message);
				}


			}
		}
		else {
			Assert.fail("Retrieve validation is not completed successfully");
		}
	}

	//Profile tab existence

	public void tab_profile() throws InterruptedException {

		Thread.sleep(3000);

		if(profile_tab.isDisplayed()) {
			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(profile_tab));

			if(wait.until(ExpectedConditions.elementToBeClickable(profile_tab)) != null) {

				profile_tab.click();
				System.out.println("Profile verification: Profile tab is opened");
				if(profile_validation.isDisplayed()) {
					message="Profile_tab is verified";
					Log.getReport(message);
				}


			}
		}
		else {
			Assert.fail("Profile validation is not completed successfully");
		}
	}

	//Edit profile tab existence

	public void tab_editprofile() throws InterruptedException {

		Thread.sleep(3000);

		if(editprofile_tab.isDisplayed()) {
			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(editprofile_tab));

			if(wait.until(ExpectedConditions.elementToBeClickable(editprofile_tab)) != null) {

				editprofile_tab.click();
				System.out.println("Edit Profile verification: edit Profile tab is opened");
				String text=editprofile_validation.getText();
				System.out.println("Output= " +text);
				if(editprofile_validation.isDisplayed()) {
					message="Profile_tab is verified";
					Log.getReport(message);
				}	
			}
		}
		else {
			Assert.fail("Edit Profile validation is not completed successfully");
		}

	}*/



