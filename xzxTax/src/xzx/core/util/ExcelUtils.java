package xzx.core.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import xzx.nsfw.user.entity.User;

public class ExcelUtils {
	public static void exportExcel(List<User> userList, ServletOutputStream outputStream) {
		try {
			//1.创建工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0,0,0,4);
			
			//1.2头标题样式
			HSSFCellStyle style1 = creatCellStyle(workbook,(short) 16);
			
			//1.3列标题样式
			HSSFCellStyle style2 = creatCellStyle(workbook,(short) 13);
			
			//2.创建工作表
			HSSFSheet workSheet = workbook.createSheet("用户列表");
			//2.1加载合并单元格对象
			workSheet.addMergedRegion(cellRangeAddress);
			//设置列宽
			workSheet.setDefaultColumnWidth(20);;
			//3.创建行
			//3.1创建头标题行，并且设置头标题
			HSSFRow row1 = workSheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			//加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("用户列表");
			//3.2创建列标题行，并且设置列标题
			HSSFRow row2 = workSheet.createRow(1);
			String[] titles = {"用户名","账号","所属部门","性别","电子邮箱"};
			for(int i = 0;i<titles.length;i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			//4.操作列标题行，将用户列表写入excel
			if(userList!=null){
				for(int i = 0;i < userList.size();i++){
					HSSFRow row3 = workSheet.createRow(i+2);
					HSSFCell cell31 = row3.createCell(0);
					cell31.setCellValue(userList.get(i).getName());
					HSSFCell cell32 = row3.createCell(1);
					cell32.setCellValue(userList.get(i).getAccount());
					HSSFCell cell33 = row3.createCell(2);
					cell33.setCellValue(userList.get(i).getDept());
					HSSFCell cell34 = row3.createCell(3);
					cell34.setCellValue(userList.get(i).isGender()?"男":"女");
					HSSFCell cell35 = row3.createCell(4);
					cell35.setCellValue(userList.get(i).getEmail());
					
				}
			}
			//5.输出
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static HSSFCellStyle creatCellStyle(HSSFWorkbook workbook,short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//1.2.1创建字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(fontSize);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//加载字体
		style.setFont(font);
		return style;
	}
}
