package com.mindtree.playerauctionweb.entity;

import com.mindtree.playerauctionweb.exceptions.InvalidCategoryException;
import com.mindtree.playerauctionweb.exceptions.InvalidTeamNameException;
import com.mindtree.playerauctionweb.exceptions.NotABowlerException;
import com.mindtree.playerauctionweb.exceptions.NotABatsmanException;

public class Player {
	private int highscore;
	private String playername, category, bestfigure, teamname;

	public Player() {

	}

	public Player(int highscore, String playername, String category, String bestfigure, String teamname) {
		this.highscore = highscore;
		this.playername = playername;
		this.category = category;
		this.bestfigure = bestfigure;
		this.teamname = teamname;
	}
		
	public Player(String playername,String category,int highscore,String bestfigure)
	{
		this.highscore = highscore;
		this.playername = playername;
		this.category = category;
		this.bestfigure = bestfigure;
	}
	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) throws NotABatsmanException {
		this.highscore = highscore;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) throws InvalidCategoryException {
		this.category = category;
	}

	public String getBestfigure() {
		return bestfigure;
	}

	public void setBestfigure(String bestfigure) throws NotABowlerException {
		this.bestfigure = bestfigure;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) throws InvalidTeamNameException {
		this.teamname = teamname;
	}
}

