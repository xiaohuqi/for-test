package com.data0123;

import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xiaohuqi@126.com 2017-11-21 10:56
 **/
public class ExcelImageTest {
	public static void main(String[] args) {
		FileOutputStream fileOut = null;
		BufferedImage bufferImg = null;
		//先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
		try {
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			bufferImg = ImageIO.read(new File("D:\\work\\data\\10\\0B3C668A59EA4EEE946AF6DB8113D9DA.jpg"));
			ImageIO.write(bufferImg, "jpg", byteArrayOut);

//			HSSFWorkbook wb = new HSSFWorkbook();
//			HSSFSheet sheet1 = wb.createSheet("test picture");
//			//画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
//			HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
//			//anchor主要用于设置图片的属性
//			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,(short) 1, 1, (short) 1, 1);
//			anchor.setAnchorType(3);
//			//插入图片
//			patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
//			fileOut = new FileOutputStream("D:/work/data/测试Excel.xls");
//			// 写入excel文件
//			wb.write(fileOut);
//			System.out.println("----Excle文件已生成------");

			FileOutputStream fos = new FileOutputStream(new File("D:/work/data/812.xlsx"));;
			XSSFWorkbook writeXssfWorkbook = new XSSFWorkbook();
			XSSFSheet writeSheet = writeXssfWorkbook.createSheet("8月");
			//画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
			XSSFDrawing patriarch = writeSheet.createDrawingPatriarch();
			XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255,(short) 1, 1, (short) 1, 1);
			anchor.setAnchorType(3);
			//插入图片
			patriarch.createPicture(anchor, writeXssfWorkbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));
			// 写入excel文件
			writeXssfWorkbook.write(fos);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fileOut != null){
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
