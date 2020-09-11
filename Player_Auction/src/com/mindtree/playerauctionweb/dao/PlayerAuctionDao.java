package com.mindtree.playerauctionweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mindtree.playerauctionweb.entity.Player;

public interface PlayerAuctionDao {
	public boolean addPlayer(Player player) throws SQLException, ClassNotFoundException;

	public boolean matchPLayer(Player plyer) throws SQLException, ClassNotFoundException;

	public ResultSet display(String team) throws SQLException, ClassNotFoundException;
}

