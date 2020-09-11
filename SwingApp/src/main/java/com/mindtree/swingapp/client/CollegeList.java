package com.mindtree.swingapp.client;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.mindtree.swingapp.daoimpl.DaoImpl;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CollegeList extends JFrame {

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
	 * @return
	 * @throws Exception
	 */
	public CollegeList() throws Exception {

		operationCollegeList();
	}

	private void operationCollegeList() throws Exception {
		// TODO Auto-generated method stub
		setTitle("List of Colleges");
		ArrayList<String> colleges = new ArrayList<String>();
		DaoImpl dao = new DaoImpl();
		final ButtonGroup bg = new ButtonGroup();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 553);
		contentPane = new JPanel(new GridLayout(0, 1, 0, 5));
		setContentPane(contentPane);
		JLabel header = new JLabel("Select a College");
		header.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(header);
		colleges = dao.collegeList();
		for (String college : colleges) {

			JRadioButton rdbtncollege = new JRadioButton(college);
			rdbtncollege.setActionCommand(college);
			rdbtncollege.setFont(new Font("Tahoma", Font.BOLD, 18));
			contentPane.add(rdbtncollege);
			bg.add(rdbtncollege);
		}
		JButton btnOK = new JButton("OK");
		btnOK.setToolTipText("Click to see Students");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bg.getSelection() == null) {
					JOptionPane.showMessageDialog(null, "No College selected");
				} else {
					String selectedcollege = bg.getSelection().getActionCommand();

					try {
						new CollegeWiseStudent(selectedcollege);
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
