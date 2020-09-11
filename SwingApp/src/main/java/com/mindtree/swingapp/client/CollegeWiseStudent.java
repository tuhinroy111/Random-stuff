package com.mindtree.swingapp.client;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mindtree.swingapp.daoimpl.DaoImpl;
import com.mindtree.swingapp.entity.Studentdto;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CollegeWiseStudent extends JFrame {

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
	 * @param selectedcollege
	 * @throws Exception
	 */
	public CollegeWiseStudent(String selectedcollege) throws Exception {

		displayStudents(selectedcollege);
	}

	public void displayStudents(String selectedcollege) throws Exception {
		// TODO Auto-generated method stub

		ArrayList<Studentdto> students = new ArrayList<Studentdto>();
		DaoImpl dao = new DaoImpl();
		students = dao.collegewiseStudent(selectedcollege);

		if (students.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No student enrolled in this college");
		} else {
			setVisible(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setTitle("Students of " + selectedcollege + ":");

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
