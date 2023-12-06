package utilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelFileUtile {
	XSSFWorkbook wb;
	//constructor for reading excel path
	public ExcelFileUtile(String excelpath)throws Throwable
	{
		FileInputStream fi= new FileInputStream(excelpath);
		wb=new XSSFWorkbook(fi);
}
	//method for counting no of rows in a sheet
	public int rowcount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//method for reading cell data
	public String getcelldata(String sheetname,int row,int column)
	{
		String data=" ";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
		}
		//method for writing results
	public void setcelldata(String sheetname,int row,int column,String status,String writeexcel)throws Throwable
	{
	//get sheet from wb
		XSSFSheet ws=wb.getSheet(sheetname);
		//get row from sheet
		XSSFRow rownum=ws.getRow(row);
		//create cell in a row
		XSSFCell cell=rownum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
		if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
		if (status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);	
		}
		FileOutputStream fo= new FileOutputStream(writeexcel);
		wb.write(fo);
	}
	public static void main(String[] args) throws Throwable{
		ExcelFileUtile xl=new ExcelFileUtile("D:/Sample.xlsx");
		int rc =xl.rowcount("Emp");
		System.out.println(rc);
		for(int i=1;i<=rc;i++)
		{
			String fname= xl.getcelldata("Emp",i,0);
			String mname= xl.getcelldata("Emp",i,1);
			String lname=xl.getcelldata("Emp",i,2);
			String Eid=xl.getcelldata("Emp",1,3);
			System.out.println(fname+" "+mname+" "+lname+" "+Eid);
			xl.setcelldata("Emp",i,4,"pass", "D:/Results.xlsx");
			

			
		}
		
	}
}


	



