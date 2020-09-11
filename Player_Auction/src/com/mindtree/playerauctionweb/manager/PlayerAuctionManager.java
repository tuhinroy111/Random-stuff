package com.mindtree.playerauctionweb.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


import com.mindtree.playerauctionweb.JDBCImpl.PlayerAuctionJDBCImpl;
import com.mindtree.playerauctionweb.entity.Player;
import com.mindtree.playerauctionweb.exceptions.DuplicateEntryExceptions;
import com.mindtree.playerauctionweb.exceptions.InvalidCategoryException;
import com.mindtree.playerauctionweb.exceptions.InvalidPlayerNameException;
import com.mindtree.playerauctionweb.exceptions.InvalidTeamNameException;
import com.mindtree.playerauctionweb.exceptions.NotABowlerException;
import com.mindtree.playerauctionweb.exceptions.NotABatsmanException;

public class PlayerAuctionManager {
	Scanner scan = new Scanner(System.in);
	Player player = new Player();
	boolean bool = false;
	PlayerAuctionJDBCImpl dao = new PlayerAuctionJDBCImpl();

	public boolean validation(Player player) throws InvalidPlayerNameException, InvalidCategoryException, InvalidTeamNameException, NotABatsmanException,
	NotABowlerException, SQLException, ClassNotFoundException,DuplicateEntryExceptions, Exception {
if(!Pattern.matches("([a-zA-Z ]+)", player.getPlayername()))
{
	throw new InvalidPlayerNameException("...Please Enter a Valid Player Name!!!");
}
if (!(player.getCategory().equalsIgnoreCase("batsman") || player.getCategory().equalsIgnoreCase("bowler")
		|| player.getCategory().equalsIgnoreCase("all rounder"))) {
	throw new InvalidCategoryException("...Invalid category name, please check your input!!!");
}

if (player.getCategory().equalsIgnoreCase("Bowler")
		&& (!Pattern.matches("([0-9]*(/)[0-9]*)", player.getBestfigure())
				|| (player.getBestfigure() == null) || player.getHighscore()<0)) {
	throw new NotABowlerException("...Invalid bowler, please check your input!!!");
}
if (player.getCategory().equalsIgnoreCase("Batsman")
		&& (player.getHighscore() < 50 || player.getHighscore() > 200)) {
	throw new NotABatsmanException("...Invalid batsman, please check your input!!!");
}

		if (dao.matchPLayer(player)) {
			try {
				bool = dao.addPlayer(player);
			} catch (SQLException e) {
				throw new SQLException("...Connection with Database Failed!!!");
			} catch (ClassNotFoundException e) {
				throw new ClassNotFoundException();
			} catch (Exception e) {
				throw new Exception();
			}

		} else {
			throw new DuplicateEntryExceptions("Player already exists in the team!!!");
		}

		return bool;

	}

	public ResultSet display(String team) throws ClassNotFoundException, SQLException, NullPointerException {
		try
		{
		ResultSet teamarr = null;
		teamarr = dao.display(team);
		return teamarr;
		}
		catch (SQLException e)
		{
			throw new SQLException("...Connection with Database Failed!!!");
		}
		catch(NullPointerException e)
		{
			throw new NullPointerException("...No Player added to this team till now");
		}
		
	}

	public boolean AddNewTeam(String teamname) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		bool=dao.AddNewTeam(teamname);
		return bool;
      }
	
	public ResultSet displayteams() throws ClassNotFoundException, SQLException, NullPointerException {
		try
		{
		ResultSet teamlist = null;
		teamlist = dao.displayteams();
		return teamlist;
		}
		catch (SQLException e)
		{
			throw new SQLException("...Connection with Database Failed!!!");
		}
		catch(NullPointerException e)
		{
			throw new NullPointerException("...No Player added to this team till now");
		}
		
	}
	public ArrayList<Player> details() throws SQLException, ClassNotFoundException
	{
		ArrayList<Player> detail=new ArrayList<Player>();
		detail=dao.details();
		
		return detail;
		
	}
}


