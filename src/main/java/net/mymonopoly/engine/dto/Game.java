package net.mymonopoly.engine.dto;

import java.util.Date;
import java.util.List;

/**
 * Represents game object for processing.
 * 
 * @author Andrey K.
 * 
 */
public class Game {

	private String code;
	private Player currentPlayer;
	private List<Player> players; // players
	private List<?> board; // fields in an round array
	private Date startTime;
	private boolean isStarted = false;
	private long lastMoveTime;
	private int lastMove;
	private GameOptions options;

	public GameOptions getOptions() {
		return options;
	}

	public void setOptions(GameOptions options) {
		this.options = options;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<?> getBoard() {
		return board;
	}

	public void setBoard(List<?> board) {
		this.board = board;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public long getLastMoveTime() {
		return lastMoveTime;
	}

	public void setLastMoveTime(long lastMoveTime) {
		this.lastMoveTime = lastMoveTime;
	}

	public int getLastMove() {
		return lastMove;
	}

	public void setLastMove(int lastMove) {
		this.lastMove = lastMove;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
