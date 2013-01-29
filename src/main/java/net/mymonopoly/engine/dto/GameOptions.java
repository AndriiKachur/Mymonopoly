package net.mymonopoly.engine.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Represents options of game.
 * 
 * @author Andrey K.
 * 
 */
public class GameOptions {

	private String theme = "standard"; // theme name
	private boolean isNotBuyAuction; // if player is not buying estate then
										// auction it?
	@Min(0)
	@Max(90)
	private int timeLimit; // time limit in minutes
	@Min(2)
	@Max(6)
	private byte playersAmount; // players amount in the game at beginning
	@NotBlank
	private String gameName;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public boolean isNotBuyAuction() {
		return isNotBuyAuction;
	}

	public void setNotBuyAuction(boolean isNotBuyAuction) {
		this.isNotBuyAuction = isNotBuyAuction;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public byte getPlayersAmount() {
		return playersAmount;
	}

	public void setPlayersAmount(byte playersAmount) {
		this.playersAmount = playersAmount;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

}
