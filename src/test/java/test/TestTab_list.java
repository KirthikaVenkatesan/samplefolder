package test;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import demopackage.Tab_list;
import frameWork.Log;

public class TestTab_list extends Tab_list{
	
	@BeforeClass
	public void testBeforeClass() throws IOException {
		before_Class(); 
	}
	
@Test
	
	public void tab_verification() {
		testCaseID = "Guru_Demowebsite_tab verification";
		Log.startTestCase(testCaseID, "Guru_Insurance_tab verification");
		try {
			//initialize the element
			message = " Initialize elements";
			initializeElement();
			Log.getReport(message);
			//login to guru
			message="Login to Insurance page";
			logintoGuru();
			//tab validation for home
			validateTab(home_tab,home_validation,"Broker Insurance WebPage","Home_tab");
			//tab validation for request
			validateTab(request_tab,req_validation,"Request","request_tab");
			//tab validation for retrieve 
			validateTab(retrieve_tab,req_validation,"Retrieve","retrieve_tab");
			//tab validation for profile
			validateTab( profile_tab,profile_validation,"Title","Profile_tab");
			//tab validation for edit profile
			validateTab( editprofile_tab,editprofile_validation,"Editing","Edit_Profile_tab");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("Guru_Insurance_tab verification");		
	}

	@AfterClass
	public void testAfterClass() throws InterruptedException {
		closeDriver();
	}
				
}


