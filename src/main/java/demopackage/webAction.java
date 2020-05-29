package demopackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import frameWork.ExtentReport;
import frameWork.Log;
import frameWork.PropertyReader;
import frameWork.ScreenShotCapture;
import test.PatriciaTestNGXML;

public class webAction  {

	//Driver declaration
	WebDriver driver;

	//Variable declaration
	String currentPath;
	public int stepNum = 1;
	public static ExtentTest test;
	public String message = "";
	public String testCaseStatus;
	String excelCellValue;
	public String testCaseID;
	String title;
	String cellValue;
	String cell_Value;
	String cell_Value_in;
	String cell_Value1;//newly created
	String numbericexcelCellValue;//newly created

	//Creating objects
	ExtentReport extentReportObject = new ExtentReport();
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm");
	public static DOMConfigurator domconfig;

	//Config properties
	//public String url_to = PropertyReader.readProperty("url");
	public String url_to=PropertyReader.readProperty("urlNew");
	//public String user = PropertyReader.readProperty("Username");
	public String password = PropertyReader.readProperty("password");

	public webAction() {
		domconfig = new DOMConfigurator();
		DOMConfigurator.configure("log4j.xml");
	}
	HashMap<String, String> excelHashMap= new HashMap<String, String>();
	public static HashMap<String, String> browserHashMap= new HashMap<String, String>();//newly created
	public static HashMap<String, String> testHashMap= new HashMap<String, String>();//newly created
	
	//Locators
	@FindBy(name="email")
	public static WebElement mail;
	@FindBy(name="password")
	public static WebElement pwd;
	@FindBy(name="submit")
	public static WebElement loginbtn;
	@FindBy(xpath="//div[contains(@class,'content')]//following::h4")
	public static WebElement validation;
	@FindBy(linkText="Insurance Project")
	public static WebElement insurance;
	@FindBy(xpath="//input[@value='Log out']")
	public static WebElement logout;
	
	@BeforeSuite()
	public void before_suite() throws IOException {
		try {
			extentReportObject.publishReports();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("FAIL-Could not initiate extend reports");
		}
	}
	//This method is for before class operations
		public void before_Class() throws IOException{
			try {
				launchDriver();
				getUrl();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("System could not navigate to application URL");
			}
			
			
		}

	//This method is to logout and close driver
	public void closeDriver() throws InterruptedException {

		/*driverWait();
		logout.click();
		driverWait();*/
		if (driver != null) {
			driver.close();
		}
		ExtentReport.extent.flush();
	}
	/**
	 * This method is used to launch the driver
	 * @throws IOException 
	 */
	
	public void launchDriver() throws IOException {

		excelFileReaderOfNumeric("Guru_SmokeTest_01");
		String BrowserSetup=excelHashMap.get("Browser");
		try {	
			if (BrowserSetup.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			} else{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
			Assert.fail("Could not open the browser");
		}
	}

	//This method is to navigate to the homepage URL
	public void getUrl() {
		try {
			driver.navigate().to(url_to);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail(" Could not navigate to the Application Login page");
		}
	}
	//This method is to Initialize the elements
	public boolean initializeElement() {
		boolean returnValue = false;
		try {
			PageFactory.initElements(driver, this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	//This method is to login to the application
	
	public void logintoGuru() {
		
		try {
			insurance.click();
			Thread.sleep(4000);
			mail.sendKeys(excelHashMap.get("Mail_Id"));
			pwd.sendKeys(excelHashMap.get("Password"));
			loginbtn.click();
			String id_name=excelHashMap.get("Mail_Id");
			System.out.println("mail id :" +id_name);
			Thread.sleep(3000);
			String validation_text=validation.getText();
			System.out.println(validation_text);
			if (validation_text.equals(id_name)) {
				Log.getReport(message);
				}
			else {
				
				Assert.fail("Validation get failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Login is not completed successfully");
			
		}
	}
/*	public boolean loginToGuru() throws IOException, InterruptedException {
		boolean val=false;
		try {
			loginUsername.sendKeys(user);
			loginPassword.sendKeys(password);
			btnLogin.click();
			driverWait();
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail(" Could not login to the application");
		}
		return val;
	}*/

	//This method is to wait the driver upto the expected conditions
	public void waittime(WebElement element) {
		WebDriverWait driverwait=new WebDriverWait(driver, 10);
		driverwait.until(ExpectedConditions.elementToBeClickable(element));
	}

	//newly created excel file reader
	
	public void excelFileReaderOfNumeric(String varTest) throws IOException{

		String filePath =  System.getProperty("user.dir")+"\\src\\data\\TestNGXML.xlsx";
		String fileName =  PropertyReader.readProperty("fileName");
		String sheetName =  PropertyReader.readProperty("sheetName");
		String column_Value= PropertyReader.readProperty("setofData_ColumnNumber");
		int c_Value = Integer.parseInt(column_Value);
		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook Workbook = null;    
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			Workbook = new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){
			Workbook = new HSSFWorkbook(inputStream);
		}
		Sheet WorkSheet = Workbook.getSheet(sheetName);
		int rowCount = WorkSheet.getLastRowNum()-WorkSheet.getFirstRowNum();

		for (int i = 0; i < rowCount+1; i++) {
			Row row = WorkSheet.getRow(i);
			Cell cell= row.getCell(0);
			cell_Value = cell.toString();
			System.out.println(cell_Value);
			if (cell_Value.equalsIgnoreCase(varTest))
			{
				//for (int j = 0; j < rowCount+1; j++) {
				Row row1 = WorkSheet.getRow(i);//next column  
				Cell cell1= row1.getCell(1);
				cell_Value1 = cell1.toString();
				Cell cell_Out_Value= row1.getCell(c_Value);
				numbericexcelCellValue=cell_Out_Value.toString();
				excelHashMap.put(cell_Value1, numbericexcelCellValue);

				inputStream.close();

			}

		}	

	}

//This method is to write the values in excel file
	public void excelFileWriter(String inputVariable, String inputValue) throws IOException{
		String filePath_in =  System.getProperty("user.dir")+"\\src\\data\\TestNGXML.xlsx";
		String fileName_in =  PropertyReader.readProperty("fileName_writer");
		String sheetName_in =  PropertyReader.readProperty("sheetName_writer");
		String column_Value= PropertyReader.readProperty("setofData_ColumnNumber");
		int c_Value = Integer.parseInt(column_Value);


		FileInputStream inputStream = new FileInputStream(filePath_in);
		Workbook Workbook = null;    
		String fileExtensionName = fileName_in.substring(fileName_in.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			Workbook = new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){
			Workbook = new HSSFWorkbook(inputStream);
		}
		Sheet WorkSheet = Workbook.getSheet(sheetName_in);
		int rowCount = WorkSheet.getLastRowNum()-WorkSheet.getFirstRowNum();
		for (int i = 0; i < rowCount+1; i++) {
			Row row = WorkSheet.getRow(i);
			//System.out.println(row);
			Cell cell= row.getCell(1);
			cell_Value = cell.toString();
			if (cell_Value.equalsIgnoreCase(inputVariable)) {
				if (inputValue.contains("/")) {
					String date[]=inputValue.split("/");
					row.createCell(c_Value).setCellValue(date[0]);
					//create  new row for year
					Row row_year = WorkSheet.getRow(i+1);
					row_year.createCell(c_Value).setCellValue(date[1]);
				}else if (inputValue!=null) {
					row.createCell(c_Value).setCellValue(inputValue);
				}
				
				inputStream.close();
				FileOutputStream outputStream = new FileOutputStream(filePath_in);
				Workbook.write(outputStream);
				outputStream.close();
			
			}
		}
	}

	//Initialize the robot class
	protected Robot keyboardAction() throws AWTException {
		Robot keyboard=new Robot();
		return keyboard;
	}

	//This method is to wait the driver 
	public void driverWait() throws InterruptedException {
		Thread.sleep(2000);
	}

	//This method is to generate filename for report
	public static String generateFileName(ITestResult result) {
		Date date = new Date();
		String fileName = result.getName() + "_" + sdf.format(date);
		return fileName;
	}

	@AfterMethod
	//This method is to get the result or save the screenshot path
	public void getResult(ITestResult result) {
		try {
			String screenShotPath = "";
			// creating object for ScreenShotCapture
			ScreenShotCapture screenshotObject = new ScreenShotCapture();
			if (result.getStatus() == ITestResult.FAILURE) {
				Log.error(message);
				test.log(LogStatus.FAIL, " FAIL - " + message );
				// Logging into JIRA for Failure
				test.log(LogStatus.FAIL, result.getThrowable());
				if (driver != null) {
					screenShotPath = screenshotObject.capture(driver, generateFileName(result));
					test.log(LogStatus.FAIL,
							"Snapshot below: " + result.getMethod() + test.addScreenCapture(screenShotPath + ".png"));
				}
				testCaseStatus = "FAIL";
			} else {
				testCaseStatus = "PASS";
				test.log(LogStatus.PASS, "PASS-Test case Executed Successfully");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

