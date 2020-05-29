package test;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import demopackage.AgileProject;
import frameWork.Log;

public class TestAgileProject extends AgileProject{
	
	@BeforeClass
	public void testBeforeClass() throws IOException {
		before_Class(); 
	}
	
@Test
	
	public void tab_verification() {
		testCaseID = "Guru_Demowebsite_Agile Project verification";
		Log.startTestCase(testCaseID, "Guru_Agile Project verification");
		try {
			//initialize the element
			message = " Initialize elements";
			initializeElement();
			Log.getReport(message);
			//login into agile
			
			validation("uid","password","Agile Project","LOGIN","RESET");
			//customTab("Customer");
			miniStatement("Mini Statement","AccSubmit");
			//webTable();
			excelwebtable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("Guru_Agile Project verification");		
	}

	@AfterClass
	public void testAfterClass() throws InterruptedException {
		closeDriver();
	}
			
}



