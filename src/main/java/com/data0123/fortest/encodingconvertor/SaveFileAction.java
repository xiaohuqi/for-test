package com.data0123.fortest.encodingconvertor;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * @author xiaohuqi E-mail:xiaohuqi@126.com
 * @version 创建时间：2010-3-17 下午10:06:10
 * 说明
 */

public class SaveFileAction extends AbstractAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2425873016931318793L;
	JFileChooser chooser;
    JFrame frame;

    SaveFileAction(JFrame frame, JFileChooser chooser) {
        super("Save As...");
        this.chooser = chooser;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent evt) {
        // Show dialog; this method does not return until dialog is closed
        chooser.showSaveDialog(frame);

        // Get the selected file
        File file = chooser.getSelectedFile();
        System.out.println(file.getAbsolutePath());
    }
}
