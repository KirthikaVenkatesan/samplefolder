package demopackage;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.xslf.model.geom.IfElseExpression;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameWork.Log;

public class Payment_Gateway extends webAction{

	//locator
	@FindBy(linkText="Payment Gateway Project")
	public static WebElement PayGateway;
	@FindBy(name="quantity")
	public static WebElement Count;
	@FindBy(xpath="//input[@type='submit']")
	public static WebElement submit_btn;
	@FindBy(xpath="//span[contains(@style,'color')]")
	public static WebElement balance;
	@FindBy(xpath="//table//tbody//tr//td[2]")
	public static WebElement Order;
	@FindBy(xpath="//h3[text()='Price: $20']")
	public static WebElement Price;


	public WebElement locators(String element) {
		WebElement tabs=driver.findElement(By.linkText(element));
		return tabs;
	}

	public WebElement textVal(String textValEle) {
		WebElement text=driver.findElement(By.xpath("//h2[contains(text(),'"+textValEle+"')]"));
		return text;
	}

	public WebElement payProcess(String element) {
		WebElement text=driver.findElement(By.name(element));
		return text;
	}

	/*String mod;
public void list_Ele() throws IOException, AWTException, InterruptedException {

	//excelFileWriter("CreditCard_NO", "Testing");

	List<WebElement> Creditcard_Details=driver.findElements(By.xpath("//h4"));
//System.out.println("Total Number of elements: "+Creditcard_Details.size());	
	Thread.sleep(3000);
	keyboardAction().keyPress(KeyEvent.VK_PAGE_DOWN);
	keyboardAction().keyRelease(KeyEvent.VK_PAGE_DOWN);
for(int i = 0;i<Creditcard_Details.size();i++) {
String Crecard_Details=Creditcard_Details.get(i).getText();	
System.out.println("Output : "+Crecard_Details);

// mod=Crecard_Details.substring(Crecard_Details.indexOf("-")).replace("-", " ");

if (Crecard_Details.contains(":-")) {
	String details []=Crecard_Details.split(":- ");
	System.out.println(details [0]+" "+details [1]);
	excelFileWriter(details[0],details[1]);
}
else if (Crecard_Details.contains("//")) {
	String details []=Crecard_Details.split(":- ");
	String text=details[0];
	String date=details[1];
	System.out.println("entered Text: "+details[0]+" "+details[1]);
	String date_Details []=date.split("/");
	System.out.println("Entered date:" +date_Details [0]+" "+date_Details [1]);
	excelFileWriter(details[0],date_Details [0]);
	excelFileWriter("month",date_Details [1]);
} 
else {
	System.out.println("Entered");
	String details []=Crecard_Details.split(" ");
	System.out.println(details[0]+details[1]);
	excelFileWriter(details[0]+details[1],details[2]);
}

}
}*/

	String Credit_Limit;

	public void payTab_Validation(String tabName,String EleVal,String expTextToBeDisplayed,String msg ) throws InterruptedException, AWTException, IOException {

		PayGateway.click();
		Thread.sleep(3000);

		String Parent_Window=driver.getWindowHandle();
		String Parent_Title=driver.getTitle();
		//System.out.println("Parent window Name= " +Parent_Title);

		if (locators(tabName).isDisplayed()) {

			WebDriverWait wait=new WebDriverWait(driver, 10);
			WebElement index= wait.until(ExpectedConditions.elementToBeClickable(locators(tabName)));
			if(index != null) {	
				locators(tabName).click();
				ArrayList<String> tab=new ArrayList<String>(driver.getWindowHandles());
				//System.out.println("Output:" +tab.size());
				if(tab.size()>=2 &&(tabName=="Generate Card Number")) {
					driver.switchTo().window(tab.get(1));
					Thread.sleep(2000);
					String val_text=textVal(EleVal).getText();
					//System.out.println("Text name= " +val_text);
					if(val_text.contains(expTextToBeDisplayed)) {
						message= " " + msg +" is verified successfully--> along with the text : "+val_text;
						Log.getReport(message);
					}else {
						Assert.fail(" "+msg+ " validation is not completed successfully");

					}
					List<WebElement> Creditcard_Details=driver.findElements(By.xpath("//h4"));
					Thread.sleep(3000);
					keyboardAction().keyPress(KeyEvent.VK_PAGE_DOWN);
					keyboardAction().keyRelease(KeyEvent.VK_PAGE_DOWN);
					for(int i = 0;i<Creditcard_Details.size();i++) {
						String Crecard_Details=Creditcard_Details.get(i).getText();	
						if (Crecard_Details.contains(":-")) {
							String details []=Crecard_Details.split(":- ");
							System.out.println(details [0]+" "+details [1]);
							excelFileWriter(details[0],details[1]);
							message="Retrieving Credit card Details: "+Crecard_Details;
							Log.getReport(message);
						}
						else if (Crecard_Details.contains("//")) {
							String details []=Crecard_Details.split(":- ");
							String text=details[0];
							String date=details[1];
							System.out.println("entered Text: "+details[0]+" "+details[1]);
							String date_Details []=date.split("/");
							System.out.println("Entered date:" +date_Details [0]+" "+date_Details [1]);
							excelFileWriter(details[0],date_Details [0]);
							excelFileWriter("month",date_Details [1]);
							message="Retrieving Credit card Details: "+Crecard_Details;
							Log.getReport(message);
						} 
						else {
							//System.out.println("Entered")
							String details []=Crecard_Details.split(" ");
							Credit_Limit=details[2].substring(1,4);
							System.out.println("Credit_Card Limit: "+Credit_Limit);
							System.out.println(details[0]+details[1]);
							excelFileWriter(details[0]+details[1],Credit_Limit);
							message="Retrieving Credit card Details: "+Crecard_Details;
							Log.getReport(message);
						}

					}
					Thread.sleep(2000);
					/*keyboardAction().keyPress(KeyEvent.VK_CONTROL);
			keyboardAction().keyPress(KeyEvent.VK_W);
			keyboardAction().keyRelease(KeyEvent.VK_W);
			keyboardAction().keyRelease(KeyEvent.VK_CONTROL);*/
					driver.switchTo().window(tab.get(0));

				}
				else{
					Thread.sleep(2000);
					String val_text=textVal(EleVal).getText();
					Thread.sleep(2000);
					//System.out.println("Text name= " +val_text);
					if(val_text.contains(expTextToBeDisplayed)) {

						message= " " + msg +" is verified successfully--> along with the text : " +val_text;
						Log.getReport(message);
					}else {
						Assert.fail(" "+msg+ " validation is not completed successfully");

					}



				}

			}


		}}

	String quantity_No;
	String Price_value1;
	String Order_no;
	public void purchase() throws InterruptedException, AWTException, IOException {
		excelFileReaderOfNumeric("Guru_SmokeTest_02");
		PayGateway.click();
		Thread.sleep(2000);
		locators("Cart").click();
		Thread.sleep(2000);
		keyboardAction().keyPress(KeyEvent.VK_PAGE_DOWN);
		keyboardAction().keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(2000);
		Select dropdown=new Select(Count);
		dropdown.selectByValue(excelHashMap.get("Quantity"));
		quantity_No=excelHashMap.get("Quantity");
		String Price_rate=Price.getText();
		System.out.println("Output-------------->" +Price_rate);
		Price_value1=Price_rate.replace("$"," ").substring(7).trim();
		System.out.println("Output------------------->" +Price_value1);
		submit_btn.click();
		payProcess("card_nmuber").sendKeys(excelHashMap.get("Card Number"));
		keyboardAction().keyPress(KeyEvent.VK_PAGE_DOWN);
		keyboardAction().keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(6000);
		Select dropdown1=new Select(payProcess("month"));
		//dropdown1.getOptions();
		dropdown1.selectByVisibleText(excelHashMap.get("Exp"));
		Select dropdown2=new Select(payProcess("year"));
		dropdown2.selectByValue(excelHashMap.get("Exp_year"));
		payProcess("cvv_code").sendKeys(excelHashMap.get("CVV"));
		Thread.sleep(4000);
		payProcess("submit").click();
		message="Credit card details have been entered successfully";
		Log.getReport(message);
		Order_no=Order.getText();
		excelFileWriter("Delivery", Order_no);
		message="Payment succesfully completed and the Order_no is retrieved :" +Order_no;
		Log.getReport(message);
		//locators("Home").click();


	}	

	String	balance_final;
	String Credit_lim;
	int bal_amt;
	public void balance_validation() throws InterruptedException, IOException {
		Credit_lim=excelHashMap.get("CreditLimit");
		String balance_final1=excelHashMap.get("balance_final");
		int Credit_lim_int=Integer.parseInt(Credit_lim);
		int quantity_No_int = Integer.parseInt(quantity_No);
		int Price_rate_int =Integer.parseInt(Price_value1);
		int Cre_balance_int =Integer.parseInt(balance_final1);

		//if(excelHashMap.get("CreditLimit").equals(excelHashMap.get("balance_final"))) {
		if(Cre_balance_int==0) {
			System.out.println("verification for quantity No---------->" +quantity_No_int);
			System.out.println("verification for price rate---------->"+Price_rate_int);
			System.out.println("verification for previous bal amt---------->"+Cre_balance_int);
			bal_amt=Credit_lim_int -(quantity_No_int*Price_rate_int);
			message="credit card balance is calculated :"+bal_amt;
			Log.getReport(message);
		}else {
			System.out.println("verification for quantity No---------->" +quantity_No_int);
			System.out.println("verification for price rate---------->"+Price_rate_int);
			System.out.println("verification for previous bal amt---------->"+Cre_balance_int);
			bal_amt=Cre_balance_int-((quantity_No_int)*(Price_rate_int));


			message="credit card balance is calculated :"+bal_amt;
			Log.getReport(message);
		}
	}


	public void balance() throws InterruptedException, AWTException, IOException {
		excelFileReaderOfNumeric("Guru_SmokeTest_03");

		String bal_amt_str=	Integer.toString(bal_amt);

		locators("Check Credit Card Limit").click();
		Thread.sleep(2000);
		payProcess("card_nmuber").sendKeys(excelHashMap.get("Card Number"));
		payProcess("submit").click();
		Thread.sleep(2000);
		String Cre_balance_new=balance.getText();
		excelFileWriter("balance_final", Cre_balance_new);
		if( bal_amt_str.equals(Cre_balance_new)) {
			System.out.println("verification---------->"+bal_amt_str);
			System.out.println("verification---------->"+Cre_balance_new); 
			message="balance validation is validated sucessfully";
			Log.getReport(message);

		}else {
			System.out.println("verification amt calculated manually---------->"+bal_amt_str);
			System.out.println("verification new balance amt---------->"+Cre_balance_new); 
			message="balance validation is not validated sucessfully";
			Log.getReport(message);

		}
		System.out.println("Credit Card Balance: " +Cre_balance_new);
		String Order_no_excel=excelHashMap.get("Delivery");

		List<WebElement> Order_nos=driver.findElements(By.xpath("//table//tbody//tr//td[6]"));
		for(int i = 0;i<Order_nos.size();i++) {
			String Order_Details=Order_nos.get(i).getText();	
			System.out.println("Entered into if condition and Ordered No through table: "+Order_Details+ " and Oder_no through excel:" +Order_no_excel);
			//if((excelHashMap.get("Delivery").replace("\"", "")).equals(Order_Details)) {
			if((Order_no_excel).equals(Order_Details)) {
				WebElement Order_row=driver.findElement(By.xpath("//table//tbody["+(i+1)+"]"));
				//for(int j = 0;j<Order_row.size();j++) {
				//System.out.println("text size:"+Order_row.size());
				String Order_rowText=Order_row.getText();
				System.out.println("Retrieved Order row Value: "+Order_rowText);
				message="Retrieved the entire row text for the particular Order No : " +Order_no+ "------->" +Order_rowText;  
				Log.getReport(message);
			}

		}

	}



}






/*
if (locators(tabName).isDisplayed()) {

WebDriverWait wait=new WebDriverWait(driver, 10);
WebElement index= wait.until(ExpectedConditions.elementToBeClickable(locators(tabName)));
if(index != null) {	
	locators(tabName).click();

	System.out.println("cart entered");
	Thread.sleep(2000);
	String val_text=textVal(EleVal).getText();
	System.out.println("Text name= " +val_text);
	if(val_text.contains(expTextToBeDisplayed)) {

		message= " " + msg +" is verified successfully";
		Log.getReport(message);
	}
	else {
		Assert.fail(" "+msg+ " validation is not completed successfully");

	}

}
}
else {
Assert.fail(" "+msg+ " validation is not completed successfully");

}
}*/
