package com.mindtree.filetransfer.client;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class MainWindow {

	private JFrame frame;
	private JTextField txtSourcePath;
	private JTextField txtTargetPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 689, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblSourcePath = new JLabel("Source Path:");
		lblSourcePath.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSourcePath.setBounds(39, 54, 116, 20);
		frame.getContentPane().add(lblSourcePath);

		txtSourcePath = new JTextField();
		txtSourcePath.setBounds(156, 51, 300, 26);
		frame.getContentPane().add(txtSourcePath);
		txtSourcePath.setColumns(10);

		JEditorPane textPane = new JEditorPane();
		textPane.setEditable(false);
		textPane.setBorder(new LineBorder(Color.BLACK, 0));
		textPane.setForeground(Color.BLACK);
		JScrollPane spEditor = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spEditor.setBounds(205, 176, 251, 98);
		frame.getContentPane().add(spEditor);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(156, 114, 300, 26);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("--None--");
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String existing = textPane.getText();
					String selectedFile = comboBox.getSelectedItem().toString();
					if (existing.equals("")) {
						existing = existing.replace(existing, selectedFile);
						textPane.setText(existing);
					} else {
						selectedFile = existing + "\n" + comboBox.getSelectedItem().toString();
						existing = selectedFile;
						textPane.setText(existing);
					}
				}
			}
		});

		JLabel lblSourceFiles = new JLabel("Source Files:");
		lblSourceFiles.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSourceFiles.setBounds(39, 117, 116, 20);
		frame.getContentPane().add(lblSourceFiles);

		JLabel lblFilesToBe = new JLabel("Files to be Copied:");
		lblFilesToBe.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFilesToBe.setBounds(39, 176, 158, 20);
		frame.getContentPane().add(lblFilesToBe);

		JLabel lblTargetPath = new JLabel("Target Path:");
		lblTargetPath.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTargetPath.setBounds(39, 304, 116, 20);
		frame.getContentPane().add(lblTargetPath);

		txtTargetPath = new JTextField();
		txtTargetPath.setBounds(156, 301, 300, 26);
		frame.getContentPane().add(txtTargetPath);
		txtTargetPath.setColumns(10);

		JButton btnCopy = new JButton("Start Copying");
		btnCopy.setBounds(183, 360, 142, 29);
		frame.getContentPane().add(btnCopy);

		btnCopy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String source = txtSourcePath.getText();
				String target = txtTargetPath.getText();
				String[] listOfFiles = textPane.getText().split("\n");
				for (String file : listOfFiles) {
					File sourcefile = new File(source + "\\" + file);
					File targetfile = new File(target + "\\" + file);
					try {
						Files.copy(sourcefile.toPath(), targetfile.toPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		JButton btnImportFiles = new JButton("Import Files");
		btnImportFiles.setBounds(481, 50, 142, 29);
		frame.getContentPane().add(btnImportFiles);

		btnImportFiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				String source = txtSourcePath.getText();
				List<String> results = new ArrayList<String>();
				File[] files = new File(source).listFiles();
				for (File file : files) {
					if (file.isFile()) {
						results.add(file.getName());
					} else if (file.isDirectory()) {
						results.add(file.getName());
					}
				}
				for (String file : results) {
					comboBox.addItem(file);
				}
				comboBox.setSelectedIndex(-1);
			}

		});

	}
}
