package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import frameWork.PropertyReader;
import demopackage.webAction;

public class PatriciaTestNGXML {

	public static int b;
	public static int testB;
	public void xml(String vartest) throws IOException {
		//System.out.println(BrowserSetup);
		String execution_value;
		String cell_Value;
		int a = 0;
		int testA=0;
		String variablename;
		String variablevalue;
		/*//excelFileReader("Test");
		excelFileReaderOfNumeric("TestNGXML");*/
		HashMap<String, String> excelHashMap= new HashMap<String, String>();
		//HashMap<String, String> StringHashMap= new HashMap<String, String>();
		String filePath =  System.getProperty("user.dir")+"\\src\\data\\TestNGXML.xlsx";
		String fileName =  PropertyReader.readProperty("fileName");
		String sheetName =  PropertyReader.readProperty("sheetName");
		//String column_Value= PropertyReader.readProperty("setofData_ColumnNumber");
		//int c_Value = Integer.parseInt(column_Value);
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
		//creating suite
		XmlSuite xmlsuite_patricia = new XmlSuite();
		//xmlsuite_patricia.setName(excelHashMap.get("Suite Name"));
		//System.out.println(excelHashMap.get("Suite Name"));
		for (int i = 0; i < rowCount+1; i++) {
			Row row = WorkSheet.getRow(i);
			//System.out.println(columnnum);
			Cell cell= row.getCell(0);
			cell_Value = cell.toString();
			Cell execution = row.getCell(3);
			execution_value=execution.toString();
			//System.out.println(cell_Value);
			if (cell_Value.equalsIgnoreCase(vartest)&& execution_value.equalsIgnoreCase("Yes"))
			{	
				//System.out.println(a);
				webAction.browserHashMap.put(""+a, row.getCell(4).toString());
				webAction.testHashMap.put(""+testA, row.getCell(1).toString());
				//System.out.println(incrementSetupsforBrowser());
				//System.out.println(browserHashMap.get(incrementcallforBrowser()));
				//creating test
				a++;
				testA++;
				int columnnum=row.getLastCellNum()-row.getFirstCellNum();
				//System.out.println(columnnum);
				XmlTest xmltest_patricia = new XmlTest(xmlsuite_patricia);
				//xmltest_patricia.setName(excelHashMap.get("Test Name"));
				//System.out.println(excelHashMap.get("Parameters Name"));
				for (int j = 0; j < columnnum; j++) {
					Row rowvar=WorkSheet.getRow(0);
					Cell var=rowvar.getCell(j);
					variablename=var.toString();
					Cell clm=row.getCell(j);
					variablevalue=clm.toString();
					//geting variables
					excelHashMap.put(variablename, variablevalue);
					//System.out.println(variablename+"  "+variablevalue);
					//System.out.println(BrowserSetup);
				}
				if (excelHashMap.get("Parameters Name")!=null) {

					String[] variablenames=excelHashMap.get("Parameters Name").split(",");
					String[] variablevalues=excelHashMap.get("Parameters Value").split(",");

					for (int k = 0; k < variablevalues.length; k++)
					{
						//System.out.println(variablevalues[k]);
						xmltest_patricia.addParameter(variablenames[k].trim(),variablevalues[k].trim());
					}
				}
				//xmltest_patricia.addParameter(excelHashMap.get("Parameter2_Variable"), excelHashMap.get("Parameter2_Vaue"));
				//xmltest_patricia.addParameter(excelHashMap.get("Parameter3_Variable"), excelHashMap.get("Parameter3_Vaue"));
				String class_V = excelHashMap.get("TestClassName");
				//System.out.println(class_V);
				XmlClass xmlclass_patricia = new   XmlClass(class_V);
				java.util.List<XmlClass> test= new ArrayList<XmlClass>();
				test.add(xmlclass_patricia);
				xmltest_patricia.setXmlClasses(test);	
				//System.out.println(a);
			}	
		}	
		TestNG testxml = new TestNG();
		java.util.List<XmlSuite> testsuite= new ArrayList<XmlSuite>();
		testsuite.add(xmlsuite_patricia);
		testxml.setXmlSuites(testsuite);
		testxml.run();	
	}
	public static void main(String[] args) throws IOException {

		PatriciaTestNGXML obj = new PatriciaTestNGXML();
		//extentReportObject.publishReports();
		obj.xml("Poc_suite");
		//ExtentReport.extent.flush();
	
	}
}
