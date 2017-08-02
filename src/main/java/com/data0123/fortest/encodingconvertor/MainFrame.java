package com.data0123.fortest.encodingconvertor;

import java.awt.EventQueue;





import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 * @author xiaohuqi E-mail:xiaohuqi@126.com
 * @version 创建时间：2010-3-17 下午09:20:56
 * 说明
 */

@SuppressWarnings("rawtypes")
public class MainFrame  extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9192838979269000940L;
	private JFrame frame;
	private JTextField sourceFilePathTextField;
	JComboBox sourceFileEncodeComboBox;
	JComboBox targetFileEncodeComboBox;
	JTextArea fileSuffixTextArea;
	JFileChooser fileChooser;
	JFileChooser targetPathChooser;
	JButton selectFileButton;
	JButton targetPathButton;
	private JTextField targetPathTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel sourceFilePathLabel = new JLabel("源文件（夹）：");
		sourceFilePathLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		sourceFilePathLabel.setBounds(132, 71, 102, 21);
		frame.getContentPane().add(sourceFilePathLabel);

		sourceFilePathTextField = new JTextField();
		sourceFilePathTextField.setBounds(238, 71, 188, 21);
		frame.getContentPane().add(sourceFilePathTextField);
		sourceFilePathTextField.setColumns(10);

		JLabel sourceFileCodingLabel = new JLabel("源文件编码：");
		sourceFileCodingLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		sourceFileCodingLabel.setBounds(132, 135, 102, 21);
		frame.getContentPane().add(sourceFileCodingLabel);

		sourceFileEncodeComboBox = new JComboBox();
		sourceFileEncodeComboBox.setModel(new DefaultComboBoxModel(new String[] {" UTF-8", " GBK", " GB2312", " ISO-8859-1"}));
		sourceFileEncodeComboBox.setBounds(238, 135, 127, 21);
		frame.getContentPane().add(sourceFileEncodeComboBox);

		JLabel targetFileCodingLabel = new JLabel("目标文件编码：");
		targetFileCodingLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		targetFileCodingLabel.setBounds(132, 175, 102, 21);
		frame.getContentPane().add(targetFileCodingLabel);

		targetFileEncodeComboBox = new JComboBox();
		targetFileEncodeComboBox.setModel(new DefaultComboBoxModel(new String[] {" UTF-8", " GBK", " GB2312", " ISO-8859-1"}));
		targetFileEncodeComboBox.setBounds(238, 175, 127, 21);
		frame.getContentPane().add(targetFileEncodeComboBox);

		JButton okButton = new JButton("开始转换");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sourceFilePath = sourceFilePathTextField.getText();
				String targetPath  = targetPathTextField.getText();
				String sourceFileCoding = sourceFileEncodeComboBox.getSelectedItem().toString();
				String targetFileCoding = targetFileEncodeComboBox.getSelectedItem().toString();
				String fileSuffix = fileSuffixTextArea.getText();
				
				EncodingConvertorUtil.convertEncoding(sourceFilePath, targetPath, sourceFileCoding, targetFileCoding, fileSuffix);
			}
		});
		okButton.setFont(new Font("宋体", Font.PLAIN, 14));
		okButton.setBounds(182, 364, 90, 23);
		frame.getContentPane().add(okButton);

		JButton resetButton = new JButton("重新输入");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileSuffixTextArea.setText("");
			}
		});
		resetButton.setFont(new Font("宋体", Font.PLAIN, 14));
		resetButton.setBounds(278, 364, 90, 23);
		frame.getContentPane().add(resetButton);

		JLabel fileSuffixLabel = new JLabel("转换文件类型：");
		fileSuffixLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		fileSuffixLabel.setBounds(132, 215, 102, 21);
		frame.getContentPane().add(fileSuffixLabel);
		
		selectFileButton = new JButton("选择");
		selectFileButton.addActionListener(this);
//		selectFileButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				fileChooser = new JFileChooser("d:/");
//				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//				fileChooser.showDialog(MainFrame.this, "确定");
//			}
//		});
		selectFileButton.setBounds(432, 71, 63, 23);
		frame.getContentPane().add(selectFileButton);
		
		fileChooser = new JFileChooser("d:/");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		targetPathChooser = new JFileChooser("d:/save");
		targetPathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		final JComboBox fileFieldComboBox = new JComboBox();
		fileFieldComboBox.setModel(new DefaultComboBoxModel(
				new String[] {" .java", " .xml", " .html", " .htm", " .jsp", " .js", " .css", " .properties", " .drl", " 所有类型"}));
		fileFieldComboBox.setBounds(238, 215, 127, 21);
		frame.getContentPane().add(fileFieldComboBox);
		
		JButton addTypeButton = new JButton("添加");
		addTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedFileTypes = fileSuffixTextArea.getText();
				String newFileType = fileFieldComboBox.getSelectedItem().toString();
				boolean addedFlag = false;
				String[] fileTypeArray = selectedFileTypes.split("\n");
				for(String aType : fileTypeArray){
					if(aType.equals(newFileType)){
						addedFlag = true;
						break;
					}
				}
				if(!addedFlag){	//文件类型尚未添加
					if(!selectedFileTypes.equals("")){
						selectedFileTypes += "\n";
					}
					selectedFileTypes += fileFieldComboBox.getSelectedItem().toString();
					fileSuffixTextArea.setText(selectedFileTypes);
				}
			}
		});
		addTypeButton.setBounds(432, 215, 63, 23);
		frame.getContentPane().add(addTypeButton);		
		
		JLabel targetPathLabel = new JLabel("目标文件夹：");
		targetPathLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		targetPathLabel.setBounds(132, 103, 102, 21);
		frame.getContentPane().add(targetPathLabel);
		
		targetPathTextField = new JTextField();
		targetPathTextField.setColumns(10);
		targetPathTextField.setBounds(238, 103, 188, 21);
		frame.getContentPane().add(targetPathTextField);
		
		targetPathButton = new JButton("选择");
		targetPathButton.addActionListener(this);
		targetPathButton.setBounds(432, 103, 63, 23);
		frame.getContentPane().add(targetPathButton);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(238, 242, 188, 116);
			frame.getContentPane().add(scrollPane);
			
			fileSuffixTextArea = new JTextArea();
			scrollPane.setViewportView(fileSuffixTextArea);
			fileSuffixTextArea.setRows(4);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectFileButton) {
			int returnVal = fileChooser.showDialog(MainFrame.this, "确定");

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				sourceFilePathTextField.setText(file.getAbsolutePath());				
			} 
		}
		else if(e.getSource() == targetPathButton){
			int returnVal = targetPathChooser.showDialog(MainFrame.this, "确定");

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = targetPathChooser.getSelectedFile();
				targetPathTextField.setText(file.getAbsolutePath());				
			} 
		}
	}
}
