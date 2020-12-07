package TestUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestUtil {
	
	static Workbook book;
	static org.apache.poi.ss.usermodel.Sheet sheet;
	
	public static String TESTDATA_SHEET_PATH="C:\\Selenium Automation\\DataProviderInTestNG"
			+ "\\src\\com\\testData\\OrangeHrmTestData.xlsx";
	
	public static Object[][] getTestData(String sheetName){
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			book=WorkbookFactory.create(file);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetName);
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}

}
