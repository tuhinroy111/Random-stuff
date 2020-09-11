package com.mindtree.swingapp.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mindtree.swingapp.daoimpl.DaoImpl;
import com.mindtree.swingapp.entity.Studentdto;

public class CoursewiseStudent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @param selectedcourse
	 * @throws Exception
	 */
	public CoursewiseStudent(String selectedcourse) throws Exception {
		displayStudents(selectedcourse);

	}

	public void displayStudents(String selectedcourse) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Studentdto> students = new ArrayList<Studentdto>();
		DaoImpl dao = new DaoImpl();
		students = dao.coursewiseStudent(selectedcourse);

		if (students.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No student opted for this course");
		} else {
			setVisible(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setTitle("Students of " + selectedcourse + ":");

			setBounds(100, 100, 779, 410);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			String col[] = { "Slno", "Student Name", "College", "Course", "Phn No.", "Address" };
			DefaultTableModel tablemodel = new DefaultTableModel(col, 0);
			table = new JTable(tablemodel);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(15, 16, 727, 260);
			contentPane.add(scrollPane);
			scrollPane.setViewportView(table);

			for (Studentdto studentscoursewise : students) {
				int slno = studentscoursewise.getSlno();
				String name = studentscoursewise.getStudentname();
				String college = studentscoursewise.getCollegename();
				String course = studentscoursewise.getCoursename();
				String phn = studentscoursewise.getPhno();
				String address = studentscoursewise.getAddress();
				Object[] data = { slno, name, college, course, phn, address };
				tablemodel.addRow(data);
			}
			JButton btnNewButton = new JButton("Back");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnNewButton.setBounds(323, 298, 115, 29);
			contentPane.add(btnNewButton);
		}

	}

}
