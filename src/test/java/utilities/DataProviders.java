package utilities;

import org.apache.poi.sl.usermodel.Sheet;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
//DataProvider 1: to read data from excel file and return 2D array of data
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path ="src/testData/Opencart_LoginData.xlsx";//taking xl file location
        ExcelUtility xlUtil=new ExcelUtility(path);//creating object for excel using path variable
        int totalRows=xlUtil.getRowCount("Sheet1");//getting total rows
        int totalCols=xlUtil.getCellCount("Sheet1",1);//getting total cols for 1st row
        String logindata[][]=new String[totalRows][totalCols];//created for 2D array to hold data
        for(int i=1;i<=totalRows;i++){//1  read data from execl and copy to array
            for(int j=0;j<totalCols;j++){//0 i--> rows j-->cols
                logindata[i-1][j]=xlUtil.getCellData("Sheet1",i,j);//1,0
            }
        }
        return logindata;//returning 2D array
    }
    //DataProvider 2
    //Dataprovider 3
}
