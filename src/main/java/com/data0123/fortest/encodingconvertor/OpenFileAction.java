package com.data0123.fortest.encodingconvertor;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * @author xiaohuqi E-mail:xiaohuqi@126.com
 * @version 创建时间：2010-3-17 下午10:01:16
 * 说明
 */
public class OpenFileAction extends AbstractAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1850552444298414125L;
	JFrame frame;
    JFileChooser chooser;

    OpenFileAction(JFrame frame, JFileChooser chooser) {
        super("Open...");
        this.chooser = chooser;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent evt) {
        // Show dialog; this method does not return until dialog is closed
        chooser.showOpenDialog(frame);

        // Get the selected file
        File file = chooser.getSelectedFile();
        System.out.println(file.getAbsolutePath());
    }
}
