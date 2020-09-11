package com.mindtree.swingapp.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DBConnector {
	Connection con = null;

	public static Connection dbConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwcoetuhin?useSSL=false", "root",
					"Statebank1@");
			JOptionPane.showMessageDialog(null, "Connection with Database successfull");
			return con;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection with Database couldn't be established-" + e.getMessage());
			return null;
		}
	}
}
