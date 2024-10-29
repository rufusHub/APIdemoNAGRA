package org.nagra.trigger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Runner3 {

	 public static void main(String[] args) throws IOException {

	        XmlSuite xS = new XmlSuite(); // suite object
	        xS.setName("Suite"); // name set

	        XmlTest xT = new XmlTest(xS); // test object

	        FileInputStream file = new FileInputStream(new File("C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\API_demo\\DC3.xlsx"));
	        Workbook workbook = new XSSFWorkbook(file);
	        Sheet sheet = workbook.getSheetAt(0);

	        int rows = sheet.getPhysicalNumberOfRows();

	        ArrayList<XmlClass> al = new ArrayList<XmlClass>();

	        for (int i = 1; i < rows; i++) {
	            Row row = sheet.getRow(i);
	            Cell c = row.getCell(4);

	            if (c.getStringCellValue().equals("Y")) {
	                Cell pkg = row.getCell(2);
	                Cell class_name = row.getCell(3);

	                String v = pkg.getStringCellValue() + "." + class_name.getStringCellValue();

	                XmlClass c1 = new XmlClass(v); // xmlclass

	                al.add(c1);
	            }
	        }

	        System.out.println(al.size());

	        xT.setClasses(al);

	        ArrayList<XmlTest> al2 = new ArrayList<XmlTest>();
	        al2.add(xT);

	        xS.setTests(al2);

	        ArrayList<XmlSuite> al3 = new ArrayList<XmlSuite>();
	        al3.add(xS);

	        TestNG t = new TestNG();
	        t.setXmlSuites(al3);
	        t.run();

	        workbook.close();
	        file.close();
	    }
}
