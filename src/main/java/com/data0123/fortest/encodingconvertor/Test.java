package com.data0123.fortest.encodingconvertor;

import javax.swing.*;
import java.awt.*;
import java.io.File;



/**
 * @author xiaohuqi E-mail:xiaohuqi@126.com
 * @version 创建时间：2010-3-17 下午03:21:36
 * 说明
 */

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		///EncodingConvertorUtil.convertEncoding("I:/sourcecode/lucene", "GBK", "UTF-8", ".java,.xml");
		//File f = new File("g:/ch2");
		//f.delete();
		JFrame frame = new JFrame();

//		 Create a file chooser
		String filename = File.separator+"tmp";
		JFileChooser fc = new JFileChooser(new File(filename));

//		 Create the actions
		Action openAction = new OpenFileAction(frame, fc);
		Action saveAction = new SaveFileAction(frame, fc);

//		 Create buttons for the actions
		JButton openButton = new JButton(openAction);
		JButton saveButton = new JButton(saveAction);

//		 Add the buttons to the frame and show the frame
		frame.getContentPane().add(openButton, BorderLayout.NORTH);
		frame.getContentPane().add(saveButton, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);

	}

}
