package com.mindtree.playerauctionweb.JDBCImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mindtree.playerauctionweb.dao.PlayerAuctionDao;
import com.mindtree.playerauctionweb.entity.Player;
import com.mindtree.playerauctionweb.exceptions.InvalidCategoryException;
import com.mindtree.playerauctionweb.exceptions.NotABatsmanException;
import com.mindtree.playerauctionweb.exceptions.NotABowlerException;

public class PlayerAuctionJDBCImpl implements PlayerAuctionDao {
	Connection con = null;
	int temp = 0;
	int i = 0;
	int j=0;
	int teamId=0;
	int no=0;

	@Override
	public boolean addPlayer(Player player) throws SQLException, ClassNotFoundException {
		try {
			System.out.println("dddd");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Player_DB", "root", "Welcome123");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select MAX(Player_No) from Player");
			while (rs.next()) {
				temp = rs.getInt(1);
			}
			temp++;
			PreparedStatement ps = con.prepareStatement(
					"insert into Player(Player_Name,Category,HighestScore,BestFigure) values(?,?,?,?)");
			ps.setString(1, player.getPlayername());
			ps.setString(2, player.getCategory());
			ps.setInt(3, player.getHighscore());
			ps.setString(4, player.getBestfigure());

			PreparedStatement st1=con.prepareStatement("select Team_Id from team where team_name=?");
			st1.setString(1, player.getTeamname());
			ResultSet rs3=st1.executeQuery();
			while(rs3.next())
			{
				teamId=rs3.getInt(1);
			}
					
			PreparedStatement pres = con.prepareStatement("insert into Team_Player values(?,?)");
			pres.setInt(1, temp);
			pres.setInt(2, teamId);
			i = ps.executeUpdate();
			j = pres.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (i != 0)
			return true;
		else
			return false;
	}
	@Override
	public boolean matchPLayer(Player player) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Player_DB", "root", "Welcome123");
			PreparedStatement pst1 = con
					.prepareStatement("select * from player p left join team_player tp on p.player_no=tp.player_no "
							+ "where tp.team_id=(select team_id from team where team_name=?) "
							+ "and p.player_name=? and p.category=?");
			pst1.setString(1, player.getTeamname());
			pst1.setString(2, player.getPlayername());
			pst1.setString(3, player.getCategory());
			ResultSet rs1 = pst1.executeQuery();
			if (rs1.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	public ResultSet display(String team) throws SQLException, ClassNotFoundException {
		ResultSet rs2=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Player_DB", "root", "Welcome123");
			PreparedStatement pst2 = con.prepareStatement("select player_name,category,highestscore,bestfigure from "
					+ "player p left join team_player tp on p.player_no=tp.player_no "
					+ "where tp.team_id=(select team_id from team where team_name=?) order by player_name");
			pst2.setString(1, team);
			rs2= pst2.executeQuery();
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
		
		return rs2;
	}
	
	public ResultSet displayteams() throws SQLException, ClassNotFoundException 
	{
		ResultSet teams=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Player_DB", "root", "Welcome123");
			Statement st1 = con.createStatement();
			teams=st1.executeQuery("Select Team_Name from Team");
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
		
		
		return teams;
	}
    public boolean AddNewTeam(String newteam)throws SQLException, ClassNotFoundException
    {
    	Connection con=null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Player_DB", "root", "Welcome123");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select MAX(Team_id) from Team");
		while (rs.next()) {
			no = rs.getInt(1);
		}
		no++;
		PreparedStatement ps = con.prepareStatement(
				"insert into team(Team_id,Team_Name) values(?,?)");
		ps.setInt(1, no);
		ps.setString(2, newteam);
		ps.executeUpdate();
		}
		catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return true;
    	
    }
    public ArrayList<Player> details() throws SQLException, ClassNotFoundException
    {
    	ArrayList<Player> details = new ArrayList<Player>();
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Player_DB", "root", "Welcome123");
			Statement st1 = con.createStatement();
			ResultSet rs3=st1.executeQuery("Select player_name,category,highestscore,bestfigure from Player");
			
			while(rs3.next())
			{
				Player player=new Player();
				player.setPlayername(rs3.getString(1));
				try {
					player.setCategory(rs3.getString(2));
				} catch (InvalidCategoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					player.setHighscore(rs3.getInt(3));
				} catch (NotABatsmanException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					player.setBestfigure(rs3.getString(4));
				} catch (NotABowlerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				details.add(player);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
    	return details;
    }
    
    }
