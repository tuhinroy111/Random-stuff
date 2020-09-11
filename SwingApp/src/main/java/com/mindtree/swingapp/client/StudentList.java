package com.mindtree.swingapp.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mindtree.swingapp.daoimpl.DaoImpl;
import com.mindtree.swingapp.entity.Studentdto;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StudentList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblListOfStudents;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public StudentList() throws Exception {

		setTitle("Display Students");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final DaoImpl dao = new DaoImpl();
		ArrayList<String> colleges = new ArrayList<String>();
		colleges = dao.collegeList();

		table = new JTable();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(226, 52, 532, 298);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("Click to select a college");
		for (String college : colleges) {
			comboBox.addItem(college);
			comboBox.setActionCommand(college);
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String selectedcollege = comboBox.getSelectedItem().toString();
				ArrayList<Studentdto> students = new ArrayList<Studentdto>();
				try {
					students = dao.collegewiseStudent(selectedcollege);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (students.isEmpty()) {
					table = new JTable();

					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(226, 52, 532, 298);
					contentPane.add(scrollPane);
					scrollPane.setViewportView(table);
					JOptionPane.showMessageDialog(null, "No student enrolled in this college");

				} else {

					lblListOfStudents.setText("List of Students in " + selectedcollege);

					String col[] = { "Slno", "Student Name", "College", "Course", "Phn No.", "Address" };
					DefaultTableModel tablemodel = new DefaultTableModel(col, 0);
					table = new JTable(tablemodel);

					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(226, 52, 532, 298);
					contentPane.add(scrollPane);
					scrollPane.setViewportView(table);

					for (Studentdto studentscollegewise : students) {
						int slno = studentscollegewise.getSlno();
						String name = studentscollegewise.getStudentname();
						String college = studentscollegewise.getCollegename();
						String course = studentscollegewise.getCoursename();
						String phn = studentscollegewise.getPhno();
						String address = studentscollegewise.getAddress();
						Object[] data = { slno, name, college, course, phn, address };
						tablemodel.addRow(data);
					}
				}
			}
		});
		comboBox.setBounds(15, 58, 196, 26);
		contentPane.add(comboBox);

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(49, 275, 115, 29);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Select a college:");
		lblNewLabel.setBounds(15, 16, 173, 20);
		contentPane.add(lblNewLabel);

		lblListOfStudents = new JLabel("List of Students");
		lblListOfStudents.setBounds(421, 16, 173, 20);
		contentPane.add(lblListOfStudents);

		JButton btnDisplayAllStudents = new JButton("Display all Students");
		btnDisplayAllStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				lblListOfStudents.setText("List of all Students");
				ArrayList<Studentdto> students = new ArrayList<Studentdto>();
				try {
					students = dao.studentList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (students.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No student in the database");
				} else {
					String col[] = { "Slno", "Student Name", "College", "Course", "Phn No.", "Address" };
					DefaultTableModel tablemodel = new DefaultTableModel(col, 0);
					table = new JTable(tablemodel);

					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(226, 52, 532, 298);
					contentPane.add(scrollPane);
					scrollPane.setViewportView(table);

					for (Studentdto studentscollegewise : students) {
						int slno = studentscollegewise.getSlno();
						String name = studentscollegewise.getStudentname();
						String college = studentscollegewise.getCollegename();
						String course = studentscollegewise.getCoursename();
						String phn = studentscollegewise.getPhno();
						String address = studentscollegewise.getAddress();
						Object[] data = { slno, name, college, course, phn, address };
						tablemodel.addRow(data);
					}
				}
			}

		});
		btnDisplayAllStudents.setBounds(15, 205, 201, 29);
		contentPane.add(btnDisplayAllStudents);
	}
}
