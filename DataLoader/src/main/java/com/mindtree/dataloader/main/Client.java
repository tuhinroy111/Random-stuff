package com.mindtree.dataloader.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.mindtree.dataloader.dao.Dao;

public class Client {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Scanner scan = new Scanner(System.in);
		Dao dao = new Dao();
		System.out.println("Enter Database name:");
		String database = scan.nextLine();
		System.out.println("Enter table name");
		String tablename = scan.nextLine();
		dao.exportIntoExcel(database, tablename);
		scan.close();
	}
}
