package test;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import demopackage.Registration;
import frameWork.Log;

public class TestRegistration extends Registration {
	
	@BeforeClass
	public void testBeforeClass() throws IOException {
		before_Class(); 
	}
	
	@Test
	
	public void Insurance_Registration() {
		testCaseID = "Guru_Demowebsite_Registration";
		Log.startTestCase(testCaseID, "Guru_Insurance_Registration");
		try {
			//initialize the element
			message = " Initialize elements";
			initializeElement();
			Log.getReport(message);
			//Register insurance
			message="Insurance Registration";
			register();
			Log.getReport(message);
			message="Login to Insurance page";
			logintoGuru();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("Guru_Insurance_Registration");		
	}

	@AfterClass
	public void testAfterClass() throws InterruptedException {
		closeDriver();
	}
				
}



