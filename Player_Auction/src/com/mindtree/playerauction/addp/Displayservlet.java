package com.mindtree.playerauction.addp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.playerauctionweb.manager.PlayerAuctionManager;


@WebServlet(name = "displayplayerServlet", urlPatterns = { "/Displayservlet" })
public class Displayservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ResultSet teamlist=null;
       PlayerAuctionManager manager= new PlayerAuctionManager();
    public Displayservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			teamlist = manager.displayteams();
			System.out.println("do get");
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("TeamList", teamlist);
		RequestDispatcher dispatch1 = request.getRequestDispatcher("/Displayplayer.jsp");
		dispatch1.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PlayerAuctionManager manager= new PlayerAuctionManager();
		String teamname=request.getParameter("team");
		ResultSet disp_res =null;
		try {
			disp_res=manager.display(teamname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			teamlist = manager.displayteams();
			System.out.println("do get");
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("Disp_player", disp_res);
		request.setAttribute("TeamList", teamlist);
		RequestDispatcher dispatch=request.getRequestDispatcher("/Displayplayer.jsp");
		dispatch.forward(request,response);
	}

}
