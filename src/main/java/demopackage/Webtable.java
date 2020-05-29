package demopackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Webtable extends webAction {
	
	//locators
	@FindBy(linkText="WebTable")
	public static WebElement webtableLink;
	@FindBy(xpath="//div[@ng-style='Viewport.rowStyle(rowRenderIndex)']//child::div[1]//child::div[6][@ng-class]//div//i[@class='fa fa-pencil']")
	public static WebElement edit;
	@FindBy(xpath="//div//div//div//div[5]//div[1]//div[6]//user-click-select[1]//div[2]//cancel-click[1]//button[1]")
	public static WebElement cancelbtn;
	@FindBy(xpath="//body//div//div//div//div//div//div//div//div[1]//div[1]//div[6]//user-click-select[1]//div[1]//del-click[1]//button[1]//i[1]")
	public static WebElement delicon;
	
	
	public void table() throws InterruptedException {
		Actions act=new Actions(driver);
		webtableLink.click();
		Thread.sleep(3000);
		List<WebElement> row=driver.findElements(By.xpath("//div[@ng-style='Viewport.rowStyle(rowRenderIndex)']"));
		//i=row count
		for (int i=1;i<=row.size();i++) {
			System.out.println("Output Size for text: " +row.size() );
				List<WebElement> rowValue=driver.findElements(By.xpath("//div["+i+"][@ng-style='Viewport.rowStyle(rowRenderIndex)']//child::div[1]//child::div[@ng-class]"));
				//j=row cell value
				for (int j=1;j<=rowValue.size();j++) {
					WebElement cellValue=driver.findElement(By.xpath("//div["+i+"][@ng-style='Viewport.rowStyle(rowRenderIndex)']//child::div[1]//child::div["+j+"][@ng-class]"));
					System.out.println("Row Value: " +rowValue.size());
					String cellValue1=cellValue.getText();
					System.out.println("Cell Value:" +cellValue1);
				}
				Thread.sleep(2000);
				act.contextClick(delicon);
				
					/*List<WebElement> editIcon=driver.findElements(By.xpath("//div[@ng-style='Viewport.rowStyle(rowRenderIndex)']//child::div[1]//child::div[6][@ng-class]//div//i[@class='fa fa-pencil']"));
					//edit icon
					int k = i;
					if(k==i) {
						WebElement Eicon=driver.findElement(By.xpath("//div["+k+"][@ng-style='Viewport.rowStyle(rowRenderIndex)']//child::div[1]//child::div[6][@ng-class]//div//i[@class='fa fa-pencil']"));
						if(i==3) {
							System.out.println("K value:" +k);
							Thread.sleep(2000);
							act.doubleClick(edit).build().perform();
							Thread.sleep(8000);
							cancelbtn.click();
							
						}
						
					}*/
			}	
		}	
	}

