package com.data0123.work;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * @author xiaohuqi@126.com 2017-11-21 11:23
 **/
public class GzkpDataProcessMain {
	public static void main(String[] args) {
		try{
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

			FileInputStream fis = new FileInputStream(new File("D:/work/data/810.xls")); // 文件流
			FileOutputStream fos = new FileOutputStream("D:/work/data/8-10.xls");

			HSSFWorkbook readHssfWorkbook = new HSSFWorkbook(fis);
//			XSSFWorkbook writeXssfWorkbook = new XSSFWorkbook();

			int sheetCount = readHssfWorkbook.getNumberOfSheets(); // Sheet的数量

			for(int sn=0;sn<sheetCount;sn++) {
				HSSFSheet readSheet = readHssfWorkbook.getSheetAt(sn);   // 遍历第一个Sheet
				//			XSSFSheet writeSheet = writeXssfWorkbook.createSheet(readSheet.getSheetName());
				//画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
				//			XSSFDrawing patriarch = writeSheet.createDrawingPatriarch();


				//			HSSFWorkbook writeXssfWorkbook = new HSSFWorkbook();
				//			HSSFSheet sheet1 = writeXssfWorkbook.createSheet(readSheet.getSheetName());
				//			HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();

				HSSFPatriarch patriarch = readSheet.createDrawingPatriarch();

				int rowNo = 0;
				for (Row row : readSheet) {
					// 跳过第一行的目录
					if (rowNo == 0) {
						++rowNo;
						continue;
					}
					// 如果当前行没有数据，跳出循环
					if (row.getCell(0).toString().equals("")) {
						return;
					}
					String rowValue = "";

					for (int colNo = 0; colNo <= 6; colNo++) {
						Cell cell = row.getCell(colNo);
						if (cell.toString() == null) {
							continue;
						}
						int cellType = cell.getCellType();
						String cellValue = "";
						switch (cellType) {
							case Cell.CELL_TYPE_STRING:     // 文本
								cellValue = cell.getRichStringCellValue().getString();
								break;
							case Cell.CELL_TYPE_NUMERIC:    // 数字、日期
								if (DateUtil.isCellDateFormatted(cell)) {
									cellValue = fmt.format(cell.getDateCellValue());
								} else {
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cellValue = String.valueOf(cell.getRichStringCellValue().getString());
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN:    // 布尔型
								cellValue = String.valueOf(cell.getBooleanCellValue());
								break;
							case Cell.CELL_TYPE_BLANK: // 空白
								cellValue = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_ERROR: // 错误
								cellValue = "错误#";
								break;
							case Cell.CELL_TYPE_FORMULA:    // 公式
								// 得到对应单元格的公式
								//cellValue = cell.getCellFormula() + "#";
								// 得到对应单元格的字符串
								cell.setCellType(Cell.CELL_TYPE_STRING);
								cellValue = String.valueOf(cell.getRichStringCellValue().getString());
								break;
							default:
								cellValue = "#";
								break;

						}
						rowValue += cellValue + "\t";

						//					String cellValue = row.getCell(6).getRichStringCellValue().getString().trim();

						if (colNo == 6) {
							if (cellValue.equals("")) {
								continue;
							}
							String[] ta = cellValue.split(",");
							for (int i = 0; i < ta.length; i++) {
								String fileName = ta[i];
								BufferedImage bufferImg = null;
								ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

								File file = new File("D:/work/data/10/" + fileName + ".jpg");
								if (file.exists()) {
									bufferImg = ImageIO.read(file);
									ImageIO.write(bufferImg, "jpg", byteArrayOut);
								} else {
									file = new File("D:/work/data/10/" + fileName + ".png");
									bufferImg = ImageIO.read(file);
									ImageIO.write(bufferImg, "png", byteArrayOut);
								}

								HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 250, (short) (7 + i), rowNo, (short) (7 + i), rowNo);
								//					anchor.setAnchorType(3);

								patriarch.createPicture(anchor, readHssfWorkbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

							}
						}
					}

					System.out.println(rowNo++);

					if (rowNo == 15) {
						//					break;
					}
				}
			}

			readHssfWorkbook.write(fos);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void test1(String[] args) {
		try{
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

			// 同时支持Excel 2003、2007
			FileInputStream fis = new FileInputStream(new File("D:/work/data/810.xlsx")); // 文件流
//			FileOutputStream fos = new FileOutputStream(new File("D:/work/data/811.xlsx"));;

			XSSFWorkbook readXssfWorkbook = new XSSFWorkbook(fis);
//			XSSFWorkbook writeXssfWorkbook = new XSSFWorkbook();

			int sheetCount = readXssfWorkbook.getNumberOfSheets(); // Sheet的数量

			int rowNo = 0;

			XSSFSheet readSheet = readXssfWorkbook.getSheetAt(0);   // 遍历第一个Sheet
//			XSSFSheet writeSheet = writeXssfWorkbook.createSheet(readSheet.getSheetName());
			//画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
//			XSSFDrawing patriarch = writeSheet.createDrawingPatriarch();

			// 为跳过第一行目录设置count

			FileOutputStream fos = new FileOutputStream("D:/work/data/817.xls");

			HSSFWorkbook writeXssfWorkbook = new HSSFWorkbook();
			HSSFSheet sheet1 = writeXssfWorkbook.createSheet(readSheet.getSheetName());
			HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();

			for (Row row : readSheet) {
				// 跳过第一行的目录
				if (rowNo ++ == 0) {
					continue;
				}
				// 如果当前行没有数据，跳出循环
				if (row.getCell(0).toString().equals("")) {
					return;
				}

				for(int colNo=0;colNo<=6;colNo++){
					Cell cell = row.getCell(colNo);
					if (cell.toString() == null) {
						continue;
					}
					int cellType = cell.getCellType();
					String cellValue = "";
					switch (cellType) {
						case Cell.CELL_TYPE_STRING:     // 文本
							cellValue = cell.getRichStringCellValue().getString();
							break;
						case Cell.CELL_TYPE_NUMERIC:    // 数字、日期
							if (DateUtil.isCellDateFormatted(cell)) {
								cellValue = fmt.format(cell.getDateCellValue());
							} else {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								cellValue = String.valueOf(cell.getRichStringCellValue().getString());
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN:    // 布尔型
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_BLANK: // 空白
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_ERROR: // 错误
							cellValue = "错误#";
							break;
						case Cell.CELL_TYPE_FORMULA:    // 公式
							// 得到对应单元格的公式
							//cellValue = cell.getCellFormula() + "#";
							// 得到对应单元格的字符串
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cellValue = String.valueOf(cell.getRichStringCellValue().getString());
							break;
						default:
							cellValue = "#";
					}



					if(colNo == 6) {
						String rowValue = row.getCell(6).getRichStringCellValue().getString().trim();
						if (rowValue.equals("")) {
							continue;
						}
						String[] ta = rowValue.split(",");
						for (int i = 0; i < ta.length; i++) {
							String fileName = ta[i];
							BufferedImage bufferImg = null;
							ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

							File file = new File("D:/work/data/10/" + fileName + ".jpg");
							if (file.exists()) {
								bufferImg = ImageIO.read(file);
								ImageIO.write(bufferImg, "jpg", byteArrayOut);
							} else {
								file = new File("D:/work/data/10/" + fileName + ".png");
								bufferImg = ImageIO.read(file);
								ImageIO.write(bufferImg, "png", byteArrayOut);
							}

							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 250, 250, (short) (7 + i), rowNo, (short) (7 + i), rowNo);
							//					anchor.setAnchorType(3);

							patriarch.createPicture(anchor, writeXssfWorkbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

							//anchor主要用于设置图片的属性
							//					XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255,(short) (7 + i), rowNo, (short) (7 + i), rowNo);
							//					XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255,(short) 1, rowNo, (short) 1, rowNo);
							//					anchor.setAnchorType(3);
							//					//插入图片
							//					patriarch.createPicture(anchor, writeXssfWorkbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));
							//					// 写入excel文件
							//					writeXssfWorkbook.write(fos);
						}
					}
				}


//				System.out.println(rowNo + "\t" + rowValue);
				if (rowNo == 15) {
					break;
				}
			}

			writeXssfWorkbook.write(fos);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
