package com.mindtree.swingapp.client;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.mindtree.swingapp.daoimpl.DaoImpl;

public class CourseList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public CourseList() throws Exception {
		operationCourseList();
	}

	public void operationCourseList() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> courses = new ArrayList<String>();
		setTitle("List of Courses");
		DaoImpl dao = new DaoImpl();
		final ButtonGroup bg1 = new ButtonGroup();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 553);
		contentPane = new JPanel(new GridLayout(0, 1, 0, 5));
		setContentPane(contentPane);
		JLabel header = new JLabel("Select a Course");
		header.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(header);
		courses = dao.courseList();
		for (String course : courses) {
			final JRadioButton rdbtncourse = new JRadioButton(course);
			rdbtncourse.setActionCommand(course);
			rdbtncourse.setFont(new Font("Tahoma", Font.BOLD, 18));
			contentPane.add(rdbtncourse);
			bg1.add(rdbtncourse);
		}
		JButton btnOK = new JButton("OK");
		btnOK.setToolTipText("Click to see Students");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bg1.getSelection() == null) {
					JOptionPane.showMessageDialog(null, "No Course selected");
				} else {
					String selectedcourse = bg1.getSelection().getActionCommand();
					try {
						new CoursewiseStudent(selectedcourse);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		contentPane.add(btnOK);

		JButton btnBack = new JButton("Back");
		btnBack.setToolTipText("Go back to main");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnBack);
	}

}
