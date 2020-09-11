package com.mindtree.swingapp.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Select an Option");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(15, 39, 148, 20);
		contentPane.add(lblNewLabel);

		final ButtonGroup bg = new ButtonGroup();
		final JRadioButton rdbtnCollegelist = new JRadioButton("CollegeList");
		rdbtnCollegelist.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnCollegelist.setBounds(122, 86, 155, 29);
		contentPane.add(rdbtnCollegelist);
		bg.add(rdbtnCollegelist);

		final JRadioButton rdbtnCourselist = new JRadioButton("CourseList");
		rdbtnCourselist.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnCourselist.setBounds(122, 132, 155, 29);
		contentPane.add(rdbtnCourselist);
		bg.add(rdbtnCourselist);

		final JRadioButton rdbtnAddAStudent = new JRadioButton("Add a Student");
		rdbtnAddAStudent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnAddAStudent.setBounds(122, 182, 155, 29);
		contentPane.add(rdbtnAddAStudent);
		bg.add(rdbtnAddAStudent);

		final JRadioButton rdbtnDisplayStudents = new JRadioButton("Display Students");
		rdbtnDisplayStudents.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnDisplayStudents.setBounds(122, 230, 215, 29);
		contentPane.add(rdbtnDisplayStudents);
		bg.add(rdbtnDisplayStudents);

		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (bg.getSelection() == null) {
					JOptionPane.showMessageDialog(null, "No Option selected");

				}
				if (rdbtnCollegelist.isSelected()) {
					CollegeList collegeList = null;
					try {
						collegeList = new CollegeList();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					collegeList.setVisible(true);
				}
				if (rdbtnCourselist.isSelected()) {
					CourseList courselist = null;
					try {
						courselist = new CourseList();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					courselist.setVisible(true);
				}
				if (rdbtnAddAStudent.isSelected()) {
					AddStudent addstudent = null;
					try {
						addstudent = new AddStudent();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					addstudent.setVisible(true);
				}
				if (rdbtnDisplayStudents.isSelected()) {
					StudentList studentlist = null;
					try {
						studentlist = new StudentList();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					studentlist.setVisible(true);
				}
			}
		});
		btnOK.setBounds(122, 287, 115, 29);
		contentPane.add(btnOK);
		bg.add(btnOK);
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method
				dispose();
			}
		});
		btnExit.setBounds(252, 287, 115, 29);
		contentPane.add(btnExit);
	}
}
