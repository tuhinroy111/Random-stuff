package com.mindtree.swingapp.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mindtree.swingapp.daoimpl.DaoImpl;
import com.mindtree.swingapp.entity.Student;
import com.mindtree.swingapp.utilities.Validation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

public class AddStudent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField text_name;
	private JTextField text_phno;
	private JTextField text_address;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public AddStudent() throws Exception {
		setTitle("Add a Student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final DaoImpl dao = new DaoImpl();
		ArrayList<String> colleges = new ArrayList<String>();
		ArrayList<String> courses = new ArrayList<String>();
		final Student stu = new Student();
		colleges = dao.collegeList();
		courses = dao.courseList();

		JLabel lblNameOfThe = new JLabel("Name of the Student:");
		lblNameOfThe.setBounds(15, 30, 162, 20);
		contentPane.add(lblNameOfThe);

		text_name = new JTextField();
		text_name.setBounds(187, 27, 209, 26);
		contentPane.add(text_name);
		text_name.setColumns(10);

		JLabel lblSelectCollege = new JLabel("Select College:");
		lblSelectCollege.setBounds(15, 86, 119, 20);
		contentPane.add(lblSelectCollege);

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("Click to select a college");
		for (String college : colleges) {
			comboBox.addItem(college);
			comboBox.setActionCommand(college);
		}
		comboBox.setBounds(187, 83, 209, 26);
		contentPane.add(comboBox);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String collegename = comboBox.getSelectedItem().toString();
				stu.setCollegename(collegename);
			}
		});

		JLabel lblSelectCourse = new JLabel("Select Course:");
		lblSelectCourse.setBounds(15, 144, 119, 20);
		contentPane.add(lblSelectCourse);

		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setToolTipText("Click to select a course");
		for (String course : courses) {
			comboBox_1.addItem(course);
			comboBox_1.setActionCommand(course);
		}
		comboBox_1.setBounds(187, 141, 209, 26);
		contentPane.add(comboBox_1);

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String coursename = comboBox_1.getSelectedItem().toString();
				stu.setCoursename(coursename);
			}
		});

		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setBounds(15, 203, 162, 20);
		contentPane.add(lblContactNumber);

		text_phno = new JTextField();
		text_phno.setBounds(187, 197, 209, 26);
		contentPane.add(text_phno);
		text_phno.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(15, 268, 69, 20);
		contentPane.add(lblAddress);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 256, 209, 80);
		contentPane.add(scrollPane);

		text_address = new JTextField();
		scrollPane.setViewportView(text_address);
		text_address.setColumns(10);

		JLabel lblmaxCharacters = new JLabel("(max. 50 characters)");
		lblmaxCharacters.setBounds(15, 287, 162, 20);
		contentPane.add(lblmaxCharacters);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Validation validate = new Validation();

				String name = text_name.getText();
				if (validate.checkName(name)) {
					JOptionPane.showMessageDialog(null, "Please enter alphabets in the name field");
					text_name.requestFocusInWindow();
					text_name.setText("");
				} else {
					stu.setStudentname(name);
					String phno = text_phno.getText();
					if (validate.checkphnNo(phno)) {
						JOptionPane.showMessageDialog(null, "Please enter proper phone number");
						text_phno.requestFocusInWindow();
						text_phno.setText("");
					} else {
						stu.setPhno(phno);
						String address = text_address.getText();
						if (address.equals("") || address.trim().length() == 0) {
							JOptionPane.showMessageDialog(null, "Please enter address");
						} else {
							stu.setAddress(address);
							try {
								if (dao.addStudent(stu))
									JOptionPane.showMessageDialog(null, "Student enrolled successfully to "
											+ stu.getCollegename() + " college with course " + stu.getCoursename());
							} catch (HeadlessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
		btnSubmit.setBounds(92, 388, 115, 29);
		contentPane.add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				text_address.setText("");
				text_name.setText("");
				text_phno.setText("");
			}
		});
		btnClear.setBounds(223, 388, 115, 29);
		contentPane.add(btnClear);

		JLabel label = new JLabel("(10 digits)");
		label.setBounds(15, 226, 119, 20);
		contentPane.add(label);
	}
}
