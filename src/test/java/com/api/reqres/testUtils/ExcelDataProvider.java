package com.api.reqres.testUtils;

import org.testng.annotations.DataProvider;
import com.api.reqres.utilty.ExcelReader;

public class ExcelDataProvider {
    @DataProvider(name = "excelUserData")
    public static Object[][] getExcelUserData() {
        String path = "src/test/resources/testdata/CreateUserData.xlsx";
        return ExcelReader.readExcelData(path, "Sheet1");
    }
}
