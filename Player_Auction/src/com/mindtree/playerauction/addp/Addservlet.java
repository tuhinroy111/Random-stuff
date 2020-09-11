package com.mindtree.playerauction.addp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.playerauctionweb.JDBCImpl.PlayerAuctionJDBCImpl;
import com.mindtree.playerauctionweb.entity.Player;
import com.mindtree.playerauctionweb.exceptions.DuplicateEntryExceptions;
import com.mindtree.playerauctionweb.exceptions.InvalidCategoryException;
import com.mindtree.playerauctionweb.exceptions.InvalidPlayerNameException;
import com.mindtree.playerauctionweb.exceptions.InvalidTeamNameException;
import com.mindtree.playerauctionweb.exceptions.NotABatsmanException;
import com.mindtree.playerauctionweb.exceptions.NotABowlerException;
import com.mindtree.playerauctionweb.manager.PlayerAuctionManager;

/**
 * Servlet implementation class Addservlet
 */
@WebServlet(name = "addplayerServlet", urlPatterns = { "/Addservlet" })
public class Addservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor.
	 */
	String update_msg = "";
	int highscore = 0;
	ResultSet teamlist=null;
	Player player = new Player();
	PlayerAuctionManager manager = new PlayerAuctionManager();
	ArrayList<Player> playerdet= new ArrayList<Player>();

	public Addservlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			teamlist = manager.displayteams();
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("TeamList", teamlist);
		RequestDispatcher dispatch1 = request.getRequestDispatcher("/addPlayer.jsp");
		dispatch1.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String playername = request.getParameter("playername");
		String category = request.getParameter("category");
		String hs = request.getParameter("highscore");
		highscore = Integer.parseInt(hs);
		String bestfigure = request.getParameter("bestfigure");
		String teamname = request.getParameter("team");
	
		
		try {
			player.setPlayername(playername);
			player.setCategory(category);
			player.setHighscore(highscore);
			player.setBestfigure(bestfigure);
			player.setTeamname(teamname);
			try {
				if (manager.validation(player)) {
					update_msg = "Player added successfully";
					System.out.println("Added");
				} else {
					update_msg = "Player already exists in this team.";
					System.out.println("Not Added");
				}

			} catch (DuplicateEntryExceptions e) {
				update_msg = e.getMessage();
			} catch (InvalidCategoryException e) {
				// TODO Auto-generated catch block
				update_msg = e.getMessage();
			} catch (NotABatsmanException e) {
				// TODO Auto-generated catch block
				update_msg = e.getMessage();
			} catch (NotABowlerException e) {
				// TODO Auto-generated catch block
				update_msg = e.getMessage();
			} catch (InvalidTeamNameException e) {
				// TODO Auto-generated catch block
				update_msg = e.getMessage();
			} catch (InvalidPlayerNameException e) {
				update_msg = e.getMessage();
			}

		} catch (SQLException e) {
			update_msg = e.getMessage();
		} catch (NullPointerException e) {
			update_msg = e.getMessage();
		} catch (Exception e) {
			update_msg = e.getMessage();
		}
		
		try {
			playerdet=manager.details();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			teamlist = manager.displayteams();
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("Allplayer", playerdet);
		request.setAttribute("Player", update_msg);
		request.setAttribute("TeamList", teamlist);
		RequestDispatcher dispatch = request.getRequestDispatcher("/addPlayer.jsp");
		dispatch.forward(request, response);
		
	}

}
