package test;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import demopackage.Webtable;
import frameWork.Log;

public class TestWebtable extends Webtable {
	
	
	@BeforeClass
	public void testBeforeClass() throws IOException {
		before_Class(); 
	}
	@Test
	public void table_verification() {
		testCaseID = "AutomationTesting_Demowebsite_Webtable verification";
		Log.startTestCase(testCaseID, "Automation demo Webtable verification");
		try {
			//initialize the element
			message = " Initialize elements";
			initializeElement();
			Log.getReport(message);
			table();
	}catch (Exception e) {
		e.printStackTrace();
		
	}
		Log.endTestCase("Automation demo Webtable verification");		

}
}