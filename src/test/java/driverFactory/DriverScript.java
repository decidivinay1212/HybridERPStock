package driverFactory;

import org.openqa.selenium.WebDriver;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript 
{
	WebDriver driver;
	String inputpath = "./FileInput/Controller.xlsx";
	String outputpath = "./FileOutput/HybridResults.xlsx";
	String TCSheet = "MasterTestCases";
	public void startTest() throws Throwable 
	{
		String Module_Status="";
		String Module_New="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		for(int i=1;i<=xl.rowcount(TCSheet);i++) 
		{
			if(xl.getCellData(TCSheet, i, 2).equalsIgnoreCase("Y")) 
			{
				String TCModule = xl.getCellData(TCSheet, i, 1);
				for(int j=1;j<=xl.rowcount(TCModule);j++) 
				{
					String Description = xl.getCellData(TCModule, j, 0);
					String ObjectType = xl.getCellData(TCModule, j, 1);
					String Ltype = xl.getCellData(TCModule, j, 2);
					String Lvalue = xl.getCellData(TCModule, j, 3);
					String TestData = xl.getCellData(TCModule, j, 4);
					try {
						if(ObjectType.equalsIgnoreCase("startBrowser")) 
						{
							driver = FunctionLibrary.startBrowser();
						}
						if(ObjectType.equalsIgnoreCase("openUrl")) 
						{
							FunctionLibrary.openUrl();
						}
						if(ObjectType.equalsIgnoreCase("waitForElement")) 
						{
							FunctionLibrary.waitForElement(Ltype, Lvalue, TestData);
						}
						if(ObjectType.equalsIgnoreCase("typeAction")) 
						{
							FunctionLibrary.typeAction(Ltype, Lvalue, TestData);
						}
						if(ObjectType.equalsIgnoreCase("clickAction")) 
						{
							FunctionLibrary.clickAction(Ltype, Lvalue);
						}
						if(ObjectType.equalsIgnoreCase("validateTitle")) 
						{
					        FunctionLibrary.validateTitle(TestData);
						}
						if(ObjectType.equalsIgnoreCase("closeBrowser")) 
						{
							FunctionLibrary.closeBrowser();
						}
						xl.setCellData(TCModule, j, 5, "Pass", outputpath);
						Module_Status="True";
						
					}catch (Exception e) 
					{   System.out.println(e.getMessage());
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						Module_New="False";
					}
					if(Module_Status.equalsIgnoreCase("True")) 
					{
					xl.setCellData(TCSheet, i, 3, "Pass", outputpath);
					}
					if(Module_Status.equalsIgnoreCase("False")) 
					{
					xl.setCellData(TCSheet, i, 3, "Fail", outputpath);
					}
				}
			}
			else 
			{
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
		}
	}

}
