package demopackage;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import frameWork.Log;
import frameWork.PropertyReader;

public class AgileProject extends webAction {

	//locators

	@FindBy(xpath="//marquee")
	public static WebElement cusTabVal;
	@FindBy(xpath="//p[1]")
	public static WebElement miniValText;
	@FindBy(xpath="//label[contains(text(),'User-ID')]")
	public static WebElement unMsg;
	@FindBy(xpath="//label[contains(text(),'Password')]")
	public static WebElement pwdMsg;
	@FindBy(name="accountno")
	public static WebElement accNo;



	public WebElement locValue(String ele) {
		WebElement element =driver.findElement(By.xpath("//input[@value='"+ele+"']"));
		return element;	
	}

	public WebElement locators(String ele) {
		WebElement element =driver.findElement(By.xpath("//input[@name='"+ele+"']"));
		return element;	
	}

	public WebElement link(String eleLink) {
		WebElement element =driver.findElement(By.linkText(eleLink));
		return element;		
	}

	public void validation(String UN,String pwd,String linkText,String loginbtn,String resetbtn) throws AWTException, InterruptedException {
		String unValidation="User-ID must not be blank";
		String pwdValidation="Password must not be blank";
		link(linkText).click();
		Thread.sleep(4000);
		/*locators(UN).click();
		keyboardAction().keyPress(KeyEvent.VK_BACK_SPACE);
		keyboardAction().keyRelease(KeyEvent.VK_BACK_SPACE);
		Thread.sleep(2000);
		String usernameMsg=unMsg.getText();
		System.out.println("empty username txt msg: " +usernameMsg);
		Thread.sleep(2000);
		if(unValidation.equals(usernameMsg)) {
			message="Username text msg is validated with the message: " +usernameMsg;
			Log.getReport(message);
		}else {
			message="Username text msg is not validated with the message: " +usernameMsg;
			Log.getReport(message);
		}
		locators(pwd).click();
		keyboardAction().keyPress(KeyEvent.VK_BACK_SPACE);
		keyboardAction().keyRelease(KeyEvent.VK_BACK_SPACE);
		Thread.sleep(2000);
		String passwordMsg=pwdMsg.getText();
		System.out.println("empty password txt msg: " +passwordMsg);
		Thread.sleep(2000);
		if(pwdValidation.equals(passwordMsg)) {
			message="Password text msg is validated with the message: " +passwordMsg;
			Log.getReport(message);
		}else {
			message="Password text msg is not validated with the message: " +passwordMsg;
			Log.getReport(message);
		}
		locValue(resetbtn).click();
		Thread.sleep(4000);*/
		locators(UN).sendKeys(excelHashMap.get("username"));
		locators(pwd).sendKeys(excelHashMap.get("passwrd"));
		//locators(UN).sendKeys("1303");
		//locators(pwd).sendKeys("Guru99");
		locValue(loginbtn).click();
		message="Login into the Agile Project seccessfully";
		Log.getReport(message);
	}

	public void customTab(String linkText) throws InterruptedException {

		link(linkText).click();
		String customText=cusTabVal.getText();
		String valCusText="Welcome To";
		if(customText.contains(valCusText)) {
			message="Custom tab validation has been completed with the text: " +"\""+customText;
			Log.getReport(message);
		}else {
			message="Custom tab validation has not been completed with the text: " +"\""+customText;
			Log.getReport(message);
		}



	}

	public void miniStatement(String linkText,String subBtn ) throws InterruptedException {

		link(linkText).click();
		Select dropdown=new Select(accNo);
		dropdown.selectByVisibleText(excelHashMap.get("accountNO"));
		// dropdown.selectByVisibleText("3308");
		locators(subBtn).click();
		String miniTextManual="Last Three";
		String miniStmtTextVal=miniValText.getText();
		if(miniStmtTextVal.contains(miniTextManual)) {
			message="mini statement is validated with the text: " +miniStmtTextVal;
			Log.getReport(message);
		}else {
			message="mini statement is not validated with the text: " +miniStmtTextVal;
			Log.getReport(message);
		}

	}

	int rowCount;
	String headingName;
	String CellName;
	/*public void webTable() throws IOException {



			List<WebElement> rowCount=driver.findElements(By.xpath("//body//tr//tr"));

			for(int k=1;k<=rowCount.size();k++) {
				System.out.println("RowCOunt--------->"+rowCount.size());	
				WebElement heading=driver.findElement(By.xpath("//body//tr//tr[1]//th["+k+"]"));
			    headingName=heading.getText();
				System.out.println("HeadingName--------->"+headingName);

			}
			for(int i=2;i<rowCount.size();i++) {
				System.out.println("RowCOunt--------->"+rowCount.size());	
				for(int j=1;j<=rowCount.size();j++) {
					WebElement rowCell=driver.findElement(By.xpath("//body//tr//tr["+i+"]//td["+j+"]"));
				    CellName=rowCell.getText();
					System.out.println("CellValue--------->"+CellName);
					}
			}
		}
	 */

	public void excelwebtable() throws IOException, InterruptedException {
		//public void excelFileWriter(String inputVariable, String inputValue) throws IOException{
		String filePath_in =  System.getProperty("user.dir")+"\\src\\data\\TestNGXML.xlsx";
		String fileName_in =  PropertyReader.readProperty("fileName_table");
		String sheetName_in =  PropertyReader.readProperty("sheetName_table");
		//String column_Value= PropertyReader.readProperty("setofData_ColumnNumber");
		//int c_Value = Integer.parseInt(column_Value);


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
		List<WebElement> title=driver.findElements(By.xpath("//table//tbody//tr//tr[1]//th"));
		Row row=WorkSheet.createRow(0);
		for (int j = 1; j <=title.size(); j++) {
			WebElement test=driver.findElement(By.xpath("//table//tbody//tr//tr[1]//th["+j+"]"));
			String text=test.getText();
			System.out.println("Title of the Cell Value: " +text );
			row.createCell(j-1).setCellValue(text);
			//cell.setCellValue(text);
		}
		int rowCount1 = driver.findElements(By.xpath("//table//tbody//tr//tr")).size();
		for(int k=2;k<=rowCount1;k++) {	
			Thread.sleep(1000);
			List<WebElement> heading=driver.findElements(By.xpath("//table//tbody//tr//tr["+k+"]//td"));
			Row row1=WorkSheet.createRow(k-1);
			for (int i = 1; i <=heading.size(); i++) {
				WebElement test=driver.findElement(By.xpath("//table//tbody//tr//tr["+k+"]//td["+i+"]"));
				String text=test.getText();
				System.out.println("Row Num: " +k+ " Col Num: " +i+ " cell Value: " +text );
				row1.createCell(i-1).setCellValue(text);
			}

		}
		//inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(filePath_in);
		Workbook.write(outputStream);
		outputStream.close();

	}
}

/*for (int i = 0; i < rowCount1-1; i++) {
					Row row = WorkSheet.getRow(i);
					//newly created

					for(int j=0;j<rowCount1;j++) {
					if(i==0) {	
					Cell cell= row.getCell(j);
					row.createCell(j).setCellValue(inputValueheading);
					}else {
						Cell cell= row.getCell(j);
						row.createCell(j).setCellValue(inputvaluetext);

					}
					}
				}*/


//old one
/*cell_Value = cell.toString();
					if (cell_Value.equalsIgnoreCase(inputVariable)) {
						if (inputValue.contains("/")) {
							String date[]=inputValue.split("/");
							row.createCell(c_Value).setCellValue(date[0]);
							//create  new row for year
							Row row_year = WorkSheet.getRow(i+1);
							row_year.createCell(c_Value).setCellValue(date[1]);
						}else if (inputValue!=null) {
							row.createCell(c_Value).setCellValue(inputValue);
						}*/

/*inputStream.close();
						FileOutputStream outputStream = new FileOutputStream(filePath_in);
						Workbook.write(outputStream);
						outputStream.close();

					}*/













































